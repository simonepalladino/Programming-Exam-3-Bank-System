package com.example.banksystem;

import com.example.banksystem.model.Cards;
import com.example.banksystem.model.Holder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        String CF = request.getParameter("cf");
        String Name = request.getParameter("firstname");
        String Surname = request.getParameter("lastname");
        String Account_type = request.getParameter("accounttype");
        String Residence = request.getParameter("address");

       // Users utente = new Users(CF,Name,null,Account_type,Residence);

        LoginServlet.holderOperation.add(new Holder("dwfdwf", Name, Surname, CF, null, Account_type, Residence));

        Cards c = new Cards("dd","cc",2,"cc","21/03/89");

        System.out.println(c.getDate());
    }

}