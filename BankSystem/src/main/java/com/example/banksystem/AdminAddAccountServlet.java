package com.example.banksystem;

import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.CardObserver;
import com.example.banksystem.observer.MovementObserver;
import com.example.banksystem.operation.HolderOperation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@WebServlet(name = "adminAddAccount", value = "/admin-addaccount")
public class AdminAddAccountServlet extends HttpServlet {
    String url;
    Connection con;

    public static String[] generateCard() {
        String[] results = new String[2];

        //Genera automaticamente il numero di carta e il CVV
        String numbers = "0123456789";
        String FRandom = numbers;
        SecureRandom sr = new SecureRandom();
        StringBuilder card_number = new StringBuilder(12);
        int cvv;
        Random r = new Random();

        for (int i = 0; i < 12; i++) {
            int randomInt = sr.nextInt(FRandom.length());
            char randomChar = FRandom.charAt(randomInt);
            card_number.append(randomChar);
        }

        results[0] = String.valueOf(card_number);
        results[1] = String.valueOf(r.nextInt(999) + 100);

        return results;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        request.getRequestDispatcher("admin-addaccount.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        HolderOperation holderOperation = (HolderOperation) session.getAttribute("holderOperation");

        String cf = request.getParameter("cf");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String account_type = request.getParameter("accounttype");
        String residence = request.getParameter("address");
        String username =  request.getParameter("username");
        String card_type = request.getParameter("cardtype");

        holderOperation.add(new Holder(username, firstname, lastname, cf, null, account_type, residence, 0,cf));

        String[] randomCard = generateCard();
        LocalDate carddeadline = LocalDate.now().plusYears(10);
        CardObserver.getInstance().add(new Card(card_type + " of " + firstname, randomCard[0], cf, Integer.valueOf(randomCard[1]),card_type, carddeadline, 0));

        switch (account_type) {
            case "Premium" :
                MovementObserver.getInstance().add(new Movement("welcomebonus", LocalDate.now(), randomCard[0], 10));
                break;
            case "Enterprise":
                MovementObserver.getInstance().add(new Movement("welcomebonus", LocalDate.now(), randomCard[0], 20));
                break;
        }

        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }

}