package com.example.banksystem;

import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Random;

@WebServlet(name = "adminAddAccount", value = "/admin-addaccount")
public class AdminAddAccount extends HttpServlet {
    String url;
    Connection con;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        request.getRequestDispatcher("admin-addaccount.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String cf = request.getParameter("cf");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String account_type = request.getParameter("accounttype");
        String residence = request.getParameter("address");
        String username =  request.getParameter("username");

        LoginServlet.holderOperation.add(new Holder(username, firstname, lastname, cf, null, account_type, residence, 0));

        System.out.println("prova commit");
        //Aggiungiamo la carta in modo autonomo
        String numeri = "0123456789";
        String perRandom = numeri;
        SecureRandom sr = new SecureRandom();
        StringBuilder card_number = new StringBuilder(12);
        int cvv;
        Random r = new Random();

        for (int i = 0; i < 12; i++) {
            int randomInt = sr.nextInt(perRandom.length());
            char randomChar = perRandom.charAt(randomInt);
            card_number.append(randomChar);
        }

        cvv = r.nextInt(999) + 100;
        LocalDate x = LocalDate.now().plusYears(10);
        LoginServlet.cardOperation.add(new Card("Bancomat primario", card_number.toString(), cf, cvv,"Bancomat", x));


        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }

}