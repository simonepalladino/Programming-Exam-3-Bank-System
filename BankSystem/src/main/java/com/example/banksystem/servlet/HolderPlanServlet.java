package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.exception.NoFundsException;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.MovementObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet per la gestione dei piani di abbonamento dei conticorenti
 */
@WebServlet(name = "holderPlan", value = "/holder-plan")
public class HolderPlanServlet extends HttpServlet {
    Holder selectedHolder;

    /**
     * Metodo per richiamare l'utente su cui si opera e che reindirizza alla pagina jsp associata per le varie operazioni
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedHolder = (Holder) session.getAttribute("selectedHolder");

        request.getRequestDispatcher("holder-plan.jsp").forward(request, response);
    }

    /**
     * Metodo per la gestione dei vari cambiamenti di piano del conto corrente, seguendo delle regole impostate dagli autori del progetto
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String selected = request.getParameter("selected");

        String prodID = null;
        double price = 0;

        /*  Decisioni riguardo i costi di cambio programma:
            Se l'utente è Basic allora pagherà 100€ per aggiornare al piano Premium o 1000€ per quello Enterprise;
            se l'utente è Premium allora pagherà 900€ per aggiornare al piano Enterprise.
            Il downgrade a qualsiasi piano non costa.
         */
        switch (selected) {
            case "Premium":
                if (selectedHolder.getContract_type().equals("Basic")) {
                    prodID = "basic-to-premium";
                    price = 100;
                }
                break;
            case "Enterprise":
                if (selectedHolder.getContract_type().equals("Basic")) {
                    prodID = "basic-to-enterprise";
                    price = 1000;
                } else if (selectedHolder.getContract_type().equals("Premium")) {
                    prodID = "premium-to-enterprise";
                    price = 900;
                }
                break;
        }

        //Se l'utente ha effettuato un upgrade
        if (price > 0) {
            Card selectedCard = null;

            //Ciclo che cerca di scegliere la prima carta con fondi disponibili
            for (Object cardObject : selectedHolder.getCards()) {
                try {
                    Card tempCard = (Card) cardObject;

                    //Verifica che la carta non sia scaduta
                    if (tempCard.getDate().compareTo(LocalDate.now()) > 0) {
                        Movement movement = new Movement(prodID, LocalDate.now(), tempCard.getCard_number(), -price);
                        NoFundsException.checkWithdrawLimit(selectedHolder, tempCard, movement);

                        selectedCard = tempCard;
                        break;
                    }
                } catch (NoFundsException nf) {
                    //Non è la carta giusta.
                }
            }

            //Se la carta giusta non è stata trovata, allora rimanda alla pagina di errore
            if (selectedCard == null) {
                response.sendRedirect("errorpage.jsp?error=nofund&backurl=dashboard?logintype=holder");
                return;
            }

            //Se la carta è stata trovata, allora aggiungi il prodotto alla lista dei movimenti
            MovementObserver.getInstance().add(new Movement(prodID, LocalDate.now(), selectedCard.getCard_number(), -price));
        }

        Actions.getInstance().holderOperation.updatePlan(selectedHolder, selected);
        response.sendRedirect("dashboard?logintype=holder");
    }
}