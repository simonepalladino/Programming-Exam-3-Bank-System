package com.example.banksystem;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    String logintype;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        logintype = request.getParameter("logintype");
        request.setAttribute("logintype", logintype);

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.removeAttribute("logintype");

        if (logintype.equals("user"))
            request.getRequestDispatcher("user-dashboard.html").forward(request, response);
        else
            request.getRequestDispatcher("admin-dashboard.html").forward(request, response);
    }
}