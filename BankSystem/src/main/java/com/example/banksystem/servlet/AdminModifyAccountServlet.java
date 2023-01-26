package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.Factory;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.CardObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet per la modifica degli account dei correntisti
 */
@WebServlet(name = "adminModifyAccount", value = "/admin-modifyaccount")
public class AdminModifyAccountServlet extends HttpServlet {
    String selectedAccount;
    Holder selectedHolder;

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //Recupera l'account selezionato dai parametri
        selectedAccount = request.getParameter("selectedAccount");
        selectedHolder = Actions.getInstance().holderOperation.get(selectedAccount);
        request.setAttribute("selectedHolder", selectedHolder);

        //Definisce un iteratore per calcolare informazioni da visualizzare:
        // bilancio totale, numero di depositi totali e numero di spese totali
        List<Card> cardArrayList = new ArrayList<>();
        Iterator<Card> iterator = Factory.getIterator(Factory.OperationType.CARD, selectedAccount);
        int totalWithdrawals = 0, totalDeposits = 0;
        double totalBalance = 0;

        while (iterator.hasNext()) {
            Card card = iterator.next();
            cardArrayList.add(card);
            totalBalance += card.getBalance();

            Iterator<Movement> iterator2 = Factory.getIterator(Factory.OperationType.MOVEMENT, card.getCard_number());
            while (iterator2.hasNext()) {
                Movement movement = iterator2.next();

                //Se il costo del movimento è >0 allora si tratterà di un deposito, altrimenti di un prelievo/acquisto
                if (movement.getPrice() >= 0)
                    totalDeposits++;
                else
                    totalWithdrawals++;
            }
        }

        //Imposta gli attributi nel requestScope da visualizzare
        request.setAttribute("cardList", cardArrayList);
        request.setAttribute("balance", totalBalance);
        request.setAttribute("withdrawals", totalWithdrawals);
        request.setAttribute("deposits", totalDeposits);

        request.getRequestDispatcher("admin-modifyaccount.jsp").forward(request, response);
    }


    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String toDelete = request.getParameter("delete");
        if (toDelete.equals("account")) {
            //Elimina l'account visualizzato dall'istanza singleton e ritorna alla pagina precedente
            Actions.getInstance().holderOperation.delete(selectedHolder);
            response.sendRedirect("admin-accountchooser");
        } else {
            //Elimina la carta selezionata dall'istanza singleton e ricarica la pagina
            CardObserver.getInstance().delete(Actions.getInstance().cardOperation.get(toDelete));
            doGet(request, response);
        }
    }
}