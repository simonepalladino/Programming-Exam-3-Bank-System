package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.MovementObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.banksystem.exception.*;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet per il deposito del saldo sulla/e carta/e del correntista
 */
@WebServlet(name = "holderDepositOnCard", value = "/holder-depositoncard")
public class HolderDepositOnCardServlet extends HttpServlet {
    Holder selectedHolder;
    String selectedCard;

    /**
     * Metodo per il recupero dell'utente su cui effettuare l'operazione di deposito con il reindirizzamento
     * alla pagina jsp associata
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        //Recupera i parametri dall'URL per scopi di visualizzazione grafica e futuri calcoli nella doPost
        selectedHolder = (Holder) session.getAttribute("selectedHolder");
        selectedCard = request.getParameter("card");

        request.setAttribute("card", selectedCard);

        request.getRequestDispatcher("holder-depositoncard.jsp").forward(request, response);
    }

    /**
     * Metodo per il deposito su una carta selezionata usando il parametro money inserito dall'utente
     * all'interno del form di deposito all'interno del file jsp associato.
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //Tentativo di recuperare il quantitativo di soldi. Se fallisce, allora imposta la variabile cancel a true
        String moneyString = "";
        double moneyDouble = 0;
        boolean cancel = false;
        try {
            moneyString = request.getParameter("money");
            moneyDouble = Double.parseDouble(moneyString);
        } catch (Exception e) {
            cancel = true;
        }

        if (!cancel) {
            if (moneyDouble > 0) {
                Movement deposit = new Movement("deposit", LocalDate.now(), selectedCard, moneyDouble);

                try {
                    //Controlla se l'utente può permettersi di effettuare il deposito
                    //secondo i limiti imposti dal suo piano
                    DepositExceedException.checkDepositLimit(selectedHolder, deposit);

                    MovementObserver.getInstance().add(deposit);
                } catch (DepositExceedException depositExceedException) {
                    //Se viene restituita questa eccezione, allora viene caricata la pagina di errore relativa al problema
                    System.out.println(depositExceedException.toString());
                    response.sendRedirect("errorpage.jsp?error=nodeposit&backurl=holder-deposit");
                    return;
                } catch (Exception e) {
                    //Altrimenti si carica una pagina di errore generica
                    response.sendRedirect("errorpage.jsp?backurl=holder-deposit");
                    return;
                }
            } else {
                //Se l'input non è valido, allora si ricarica la pagina mostrando un messaggio di errore
                response.sendRedirect("holder-depositoncard?error=input&card=" + selectedCard);
                return;
            }
        }

        response.sendRedirect("holder-deposit");
    }
}