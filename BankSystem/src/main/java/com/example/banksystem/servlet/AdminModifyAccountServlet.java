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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedAccount = request.getParameter("selectedAccount");
        selectedHolder = Actions.getInstance().holderOperation.get(selectedAccount);
        request.setAttribute("selectedHolder", selectedHolder);

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
                if (movement.getPrice() >= 0)
                    totalDeposits++;
                else
                    totalWithdrawals++;
            }
        }

        request.setAttribute("cardList", cardArrayList);
        request.setAttribute("balance", totalBalance);
        request.setAttribute("withdrawals", totalWithdrawals);
        request.setAttribute("deposits", totalDeposits);

        request.getRequestDispatcher("admin-modifyaccount.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String toDelete = request.getParameter("delete");
        if (toDelete.equals("account")) {
            //Elimina l'account visualizzato
            Actions.getInstance().holderOperation.delete(selectedHolder);
            response.sendRedirect("admin-accountchooser");
        } else {
            //Elimina la carta selezionata
            CardObserver.getInstance().delete(Actions.getInstance().cardOperation.get(toDelete));
            doGet(request, response);
        }
    }
}