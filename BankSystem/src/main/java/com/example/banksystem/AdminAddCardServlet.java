package com.example.banksystem;

import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.observer.CardObserver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet(name = "adminAddCard", value = "/admin-addcard")
public class AdminAddCardServlet extends HttpServlet {
    String selectedAccount;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        selectedAccount = request.getParameter("selectedAccount");
        Holder selectedHolder = Actions.getInstance().holderOperation.get(selectedAccount);
        String[] randomCard = AdminAddAccountServlet.generateCard();

        request.setAttribute("random_card", randomCard[0]);
        request.setAttribute("random_cvv", randomCard[1]);
        request.setAttribute("card_name", selectedHolder.getFirstname() + "'s Card");

        request.getRequestDispatcher("admin-addcard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            String cardname = request.getParameter("cardname");
            String cardnumber = request.getParameter("cardnumber");
            String cardtype = request.getParameter("cardtype");
            int cardcvv = Integer.parseInt(request.getParameter("cardcvv"));
            LocalDate carddeadline = LocalDate.now().plusYears(10);

            try {
                Date carddeadline1 = new SimpleDateFormat("yyyy-MM").parse(request.getParameter("carddeadline"));
                carddeadline = LocalDate.ofInstant(carddeadline1.toInstant(), ZoneId.systemDefault());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            CardObserver.getInstance().add(new Card(cardname, cardnumber, selectedAccount, cardcvv, cardtype, carddeadline, 0));
        } catch (Exception e) {
            System.out.println("Si è verificato un errore durante l'inserimento della carta!");
            e.printStackTrace();
        }

        response.sendRedirect("admin-modifyaccount?selectedAccount=" + selectedAccount);
    }
}