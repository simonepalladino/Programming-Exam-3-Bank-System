package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.observer.CardObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Servlet per l'aggiunta di una o più carte a un correntista
 */
@WebServlet(name = "adminAddCard", value = "/admin-addcard")
public class AdminAddCardServlet extends HttpServlet {
    String selectedAccount;

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //Carica l'Holder passato per parametro URL e genera randomicamente una carta
        selectedAccount = request.getParameter("selectedAccount");
        Holder selectedHolder = Actions.getInstance().holderOperation.get(selectedAccount);
        String[] randomCard = AdminAddAccountServlet.generateCard();

        //Imposta i parametri necessari per la visualizzazione su pagina
        request.setAttribute("random_card", randomCard[0]);
        request.setAttribute("random_cvv", randomCard[1]);
        request.setAttribute("card_name", selectedHolder.getFirstname() + "'s Card");

        request.getRequestDispatcher("admin-addcard.jsp").forward(request, response);
    }

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            //Ottiene i valori immessi nei campi della pagina
            String cardname = request.getParameter("cardname");
            String cardnumber = request.getParameter("cardnumber");
            String cardtype = request.getParameter("cardtype");
            int cardcvv = Integer.parseInt(request.getParameter("cardcvv"));
            LocalDate carddeadline = LocalDate.now().plusYears(10);

            //Se il parsing non va a buon fine (ad esempio se l'utente non ha compilato i campi), allora la data impostata
            //resterà quella di 10 anni dopo la data corrente
            try {
                Date carddeadline1 = new SimpleDateFormat("yyyy-MM").parse(request.getParameter("carddeadline"));
                carddeadline = LocalDate.ofInstant(carddeadline1.toInstant(), ZoneId.systemDefault());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Aggiunge la nuova carta generata nell'istanza observer, informando anche al singolo utente di aggiornare le proprie carte
            CardObserver.getInstance().add(new Card(cardname, cardnumber, selectedAccount, cardcvv, cardtype, carddeadline, 0));
        } catch (Exception e) {
            System.out.println("Si è verificato un errore durante l'inserimento della carta!");
            e.printStackTrace();
        }

        response.sendRedirect("admin-modifyaccount?selectedAccount=" + selectedAccount);
    }
}