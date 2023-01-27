package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.model.Product;
import com.example.banksystem.observer.MovementObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.banksystem.exception.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet per l'acquisto dei prodotti da parte del correntista
 */
@WebServlet(name = "holderBuy", value = "/holder-buy")
public class HolderBuyServlet extends HttpServlet {
    Holder selectedHolder;

    /**
     * Metodo l'inizializzazione del holder su cui operare e i vari prodotti da visualizzare
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedHolder = (Holder) session.getAttribute("selectedHolder");

        //Se l'utente non ha carte, esci!
        if (selectedHolder.getCards().size() == 0) {
            response.sendRedirect("errorpage.jsp?error=nocards");
            return;
        }

        //Definisce la lista di prodotti da visualizzare
        List<Product> productList = new ArrayList<>();
        for (Object prodObject : Actions.getInstance().productOperation.getAll()) {
            Product prod = (Product) prodObject;

            //Aggiunge solo i "prodotti" che non sono corrispondenti alla descrizione del deposito, del ritiro e del cambio piano
            if (!prod.getType().equals("deposit") && !prod.getType().equals("withdraw") && !prod.getType().equals("upgrade")) {
                //Imposta il prezzo di sconto (resettandolo prima a zero nel caso sia già stato impostato)
                prod.setDiscountPrice(0);
                double discountPrice = getDiscountPrice(prod.getPrice(), selectedHolder.getContract_type(), prod.getType());

                //Verifica se effettivamente il prezzo è scontato
                if (discountPrice < prod.getPrice())
                    prod.setDiscountPrice(getDiscountPrice(prod.getPrice(), selectedHolder.getContract_type(), prod.getType()));
                productList.add(prod);
            }
        }

        request.setAttribute("products", productList);
        request.setAttribute("cards", selectedHolder.getCards());

        request.getRequestDispatcher("holder-buy.jsp").forward(request, response);
    }

    /**
     * Metodo per la gestione delle action sugli acquisti e prelievi
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action = request.getParameter("buy");
        String selectedcard = request.getParameter("selectedcard");

        //Ottiene la carta selezionata e definisce un movimento nullo che dovrà essere inizializzato
        Card selectedCard = selectedHolder.getCardOperation().get(selectedcard);
        Movement movement = null;

        //Se si tratta di un prelievo, allora esegui questo blocco di codice
        if (action.equals("withdraw") || action == null) {
            double money = 0;

            //Cerca di convertire dai parametri l'importo immesso; se errato, allora rimanda alla pagina di errore
            try {
                money = Double.parseDouble(request.getParameter("withdraw"));

                movement = new Movement("withdraw", LocalDate.now(), selectedcard, -money);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Si è verificato un errore durante la lettura del prelievo!");

                response.sendRedirect("errorpage.jsp?error=withdrawmoney&backurl=holder-buy");
                return;
            }
        } else {
            //Se si tratta dell'acquisto di uno degli oggetti, allora esegue questo blocco di codice

            //Controlla anzitutto se la carta è scaduta
            if (selectedCard.getDate().compareTo(LocalDate.now()) <= 0) {
                response.sendRedirect("errorpage.jsp?error=expiredcard&backurl=holder-buy");
                return;
            }

            //Il calcolo automatico del prezzo viene fatto in getDiscountPrice
            Product selectedProduct = Actions.getInstance().productOperation.get(action);
            double price = getDiscountPrice(selectedProduct.getPrice(), selectedHolder.getContract_type(), selectedProduct.getType());

            movement = new Movement(action, LocalDate.now(), selectedcard, -price);
        }

        try {
            //Controlla se l'utente può permettersi di effettuare il prelievo o l'acquisto
            NoFundsException.checkWithdrawLimit(selectedHolder, selectedCard, movement);
            WithdrawExceedException.checkWithdrawLimit(selectedHolder, selectedCard, movement);

            MovementObserver.getInstance().add(movement);
        } catch (NoFundsException noFundsException) {
            //Gestione dell'eccezione nel caso non ci sono fondi a sufficienza
            System.out.println(noFundsException.toString());
            response.sendRedirect("errorpage.jsp?error=nofund&backurl=holder-deposit");
            return;
        } catch (WithdrawExceedException withdrawExceedException) {
            //Gestione dell'eccezione nel caso l'importo superi i limiti previsti dal piano
            System.out.println(withdrawExceedException.toString());
            response.sendRedirect("errorpage.jsp?error=nowithdraw&backurl=holder-deposit");
            return;
        } catch (Exception e) {
            //Gestione dell'eccezione nel caso si sia verificato un errore generico
            response.sendRedirect("errorpage.jsp?backurl=holder-deposit");
            return;
        }

        response.sendRedirect("dashboard?logintype=holder");
    }

    /**
     * Questo metodo è utile per il calcolo del prezzo scontato con un semplice switch. Se l'utente possiede un piano Basic non ha diritto a nessuno sconto
     * mentre se l'utente ha un piano Premium oppure un piano Enterprise ha diritto a sconti che si differenziano tra di loro in base alla tipologia di prodotto.
     * @param price passo come parametro il prezzo del prodotto
     * @param contract_type passo come parametro il tipo di contratto
     * @param product_type passo come parametro il tipo di prodotto
     * @return restituisce il prezzo del prodotto scontato
     */
    public static double getDiscountPrice(double price, String contract_type, String product_type) {
        switch (contract_type) {
            case "Premium":
                if (product_type.equals("phone")){
                    return price / 100 * 90;
                }
                else if (product_type.equals("tv")){
                    return price / 100 * 70;
                }
                else if (product_type.equals("coffee")) {
                    return price / 100 * 95;
                }
                else if (product_type.equals("headphones")) {
                    return price / 100 * 70;
                }
                else if (product_type.equals("food")) {
                    return price / 100 * 60;
                }
                else
                return price / 100 * 90;
            case "Enterprise":
                if (product_type.equals("phone")){
                    return price / 100 * 70;
                }
                else if (product_type.equals("tv")){
                    return price / 100 * 60;
                }
                else if (product_type.equals("coffee")) {
                    return price / 100 * 85;
                }
                else if (product_type.equals("headphones")) {
                    return price / 100 * 60;
                }
                else if (product_type.equals("food")) {
                    return price / 100 * 50;
                }
                else
                    return price / 100 * 80;
        }

        return price;
    }
}