package com.example.banksystem;

import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.Holder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "adminModifyAccount", value = "/admin-modifyaccount")
public class AdminModifyAccountServlet extends HttpServlet {
    String selectedAccount;
    Holder selectedHolder;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        selectedAccount = request.getParameter("selectedAccount");
        selectedHolder = LoginServlet.holderOperation.get(selectedAccount);
        request.setAttribute("selectedHolder", selectedHolder);

        request.getRequestDispatcher("admin-modifyaccount.jsp").forward(request, response);
    }
}