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

@WebServlet(name = "userBuy", value = "/user-buy")
public class UserBuyServlet extends HttpServlet {
    Holder selectedHolder;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedHolder = (Holder) session.getAttribute("selectedHolder");

        //Se l'utente non ha carte, esci!
        if (selectedHolder.getCards().size() == 0) {
            response.sendRedirect("user-errorpage.jsp?error=nocards");
            return;
        }

        //Definisce la lista di prodotti da visualizzare
        List<Product> productList = new ArrayList<>();
        for (Object prodObject : Actions.getInstance().productOperation.getAll()) {
            Product prod = (Product) prodObject;

            //Aggiunge solo i "prodotti" che non sono corrispondenti alla descrizione del deposito e del ritiro
            if (!prod.getType().equals("deposit") && !prod.getType().equals("withdraw") && !prod.getType().equals("upgrade")) {
                //imposta il prezzo di sconto
                prod.setDiscountPrice(0);
                double discountPrice = getDiscountPrice(prod.getPrice(), selectedHolder.getContract_type(), prod.getType());

                if (discountPrice < prod.getPrice())
                    prod.setDiscountPrice(getDiscountPrice(prod.getPrice(), selectedHolder.getContract_type(), prod.getType()));
                productList.add(prod);
            }
        }

        request.setAttribute("products", productList);
        request.setAttribute("cards", selectedHolder.getCards());

        request.getRequestDispatcher("user-buy.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action = request.getParameter("buy");
        String selectedcard = request.getParameter("selectedcard");

        Card selectedCard = selectedHolder.getCardOperation().get(selectedcard);
        Movement movement = null;

        if (action.equals("withdraw") || action == null) {
            double money = 0;

            try {
                money = Double.parseDouble(request.getParameter("withdraw"));

                movement = new Movement("withdraw", LocalDate.now(), selectedcard, -money);
            } //catch di un'altra espressione (riguardo i fondi non disponibili)
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Si è verificato un errore durante la lettura del prelievo!");

                response.sendRedirect("user-errorpage.jsp?error=withdrawmoney&backurl=user-buy");
                return;
            }
        } else {
            //Calcolo automatico del prezzo fatto in movements
            Product selectedProduct = Actions.getInstance().productOperation.get(action);
            double price = getDiscountPrice(selectedProduct.getPrice(), selectedHolder.getContract_type(), selectedProduct.getType());

            movement = new Movement(action, LocalDate.now(), selectedcard, -price);
        }

        try {
            //Controlla se l'utente può permettersi di effettuare il deposito
            NoFundsException.checkWithdrawLimit(selectedHolder, selectedCard, movement);
            WithdrawExceedException.checkWithdrawLimit(selectedHolder, selectedCard, movement);

            MovementObserver.getInstance().add(movement);
        } catch (NoFundsException noFundsException) {
            System.out.println(noFundsException.toString());
            response.sendRedirect("user-errorpage.jsp?error=nofund&backurl=user-deposit");
            return;
        } catch (WithdrawExceedException withdrawExceedException) {
            System.out.println(withdrawExceedException.toString());
            response.sendRedirect("user-errorpage.jsp?error=nowithdraw&backurl=user-deposit");
            return;
        } catch (Exception e) {
            response.sendRedirect("user-errorpage.jsp?backurl=user-deposit");
            return;
        }

        response.sendRedirect("dashboard?logintype=user");
    }

    /**
     * Questo metodo è utile per il calcolo del prezzo scontato con un semplice switch
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