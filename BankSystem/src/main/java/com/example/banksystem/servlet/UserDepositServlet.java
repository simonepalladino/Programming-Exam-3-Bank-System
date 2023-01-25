package com.example.banksystem.servlet;

import com.example.banksystem.Actions;
import com.example.banksystem.Factory;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userDeposit", value = "/user-deposit")
public class UserDepositServlet extends HttpServlet {
    Holder selectedHolder;
    String view;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedHolder = (Holder) session.getAttribute("selectedHolder");
        view = request.getParameter("view");
        if (view == null) view = "cards";

        request.setAttribute("view", view);

        //Imposta la lista di carte dell'utente
        List<Card> cardArrayList = new ArrayList<>();
        Iterator<Card> iterator = Factory.getIterator(Factory.OperationType.CARD, selectedHolder.getCf());
        int totalWithdrawals = 0, totalDeposits = 0;
        double totalBalance = 0;

        //Itera tra le carte dell'utente per reperire le informazioni necessarie
        while (iterator.hasNext()) {
            Card card = iterator.next();
            cardArrayList.add(card);
            totalBalance += card.getBalance();

            //Itera tra i movimenti della carta
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

        //Se l'utente visualizza i movimenti di tutte le carte
        if (view.equals("all")) {
            Iterator<Movement> recentMovIterator = Factory.getMovementUserIterator(selectedHolder.getCf(), true);
            List<MovementInfo> recentMovements = new ArrayList<>();

            while (recentMovIterator.hasNext()) {
                Movement rec = recentMovIterator.next();
                Product temp = Actions.getInstance().productOperation.get(rec.getProduct_id());
                String productName = temp.getProduct_name();
                String quote = temp.getQuote();
                String type = temp.getType();

                recentMovements.add(new MovementInfo(rec.getMov_date(), rec.getCard_number_FK(), rec.getPrice(), productName, quote, type));
            }

            request.setAttribute("recentMovements", recentMovements);
        } else {
            //Se l'utente visualizza i movimenti separati per ciascuna carta
            iterator = Factory.getIterator(Factory.OperationType.CARD, selectedHolder.getCf());
            List<CardMovementInfo> cardRecentMovements = new ArrayList<>();

            //Itera per ciascuna carta
            while (iterator.hasNext()) {
                Card card = iterator.next();
                Iterator<Movement> iterator2 = Factory.getMovementCardIterator(card.getCard_number(), true);
                List<MovementInfo> recentMovements = new ArrayList<>();

                //Itera per ciascun movimento relativo alla carta
                while (iterator2.hasNext()) {
                    Movement rec = iterator2.next();
                    Product temp = Actions.getInstance().productOperation.get(rec.getProduct_id());
                    String productName = temp.getProduct_name();
                    String quote = temp.getQuote();
                    String type = temp.getType();

                    //Aggiungi alla lista di movimenti
                    recentMovements.add(new MovementInfo(rec.getMov_date(), rec.getCard_number_FK(), rec.getPrice(), productName, quote, type));
                }

                //Aggiungi alla lista di movimenti-carta (necessario alla View per visualizzare il nome della carta)
                cardRecentMovements.add(new CardMovementInfo(card.getCard_name(), recentMovements));
            }

            request.setAttribute("cardRecentMovements", cardRecentMovements);
        }

        request.getRequestDispatcher("user-deposit.jsp").forward(request, response);
    }


    public class CardMovementInfo {
        private String card_name;
        private List<MovementInfo> movementsInfo;


        /**
         * Classe necessaria a puro scopo visivo per il file "user-deposit.jsp"
         * quando si desidera visualizzare i movimenti per carta
         * @param card_name nome della carta che si vuole visualizzare
         * @param movementsInfo lista dei movimenti della carta associata
         */
        CardMovementInfo(String card_name, List<MovementInfo> movementsInfo) {
            this.card_name = card_name;
            this.movementsInfo = movementsInfo;
        }

        public String getCard_name() {
            return card_name;
        }

        public List<MovementInfo> getMovementsInfo() {
            return movementsInfo;
        }
    }
}