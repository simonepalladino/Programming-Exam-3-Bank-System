package com.example.banksystem;

import java.io.*;
import java.sql.*;

import com.example.banksystem.operation.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    public static HolderOperation holderOperation = null;
    public static CardOperation cardOperation = null;
    public static MovementOperation movementOperation = null;
    Connection con;
    String logintype;
    String error;

    public void init() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        logintype = request.getParameter("logintype");
        error = request.getParameter("error");

        if (holderOperation == null) {
            holderOperation = (HolderOperation) Factory.getNewOperation(Factory.OperationType.HOLDER);
            session.setAttribute("holderOperation", holderOperation);
        }

        if (cardOperation == null) {
            cardOperation = (CardOperation) Factory.getNewOperation(Factory.OperationType.CARD);
            session.setAttribute("cardOperation", cardOperation);
        }

        if (movementOperation == null) {
            movementOperation = (MovementOperation) Factory.getNewOperation(Factory.OperationType.MOVEMENTS);
            session.setAttribute("movementOperation", movementOperation);
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Boolean done = false;

        HttpSession session = request.getSession();
        session.setAttribute("usertext", request.getParameter("username").toString());

        if (logintype.equals("user")) {
            response.sendRedirect("dashboard?logintype=user");
        } else {
            try {
                    con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");
                    Statement stmt = con.createStatement();
                    ResultSet rs;

                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    rs = stmt.executeQuery("SELECT * FROM Admins WHERE username='" + username + "' AND password='" + password + "'");

                    if (rs.next()) {
                        done = true;
                    }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null)
                        con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (done) {
                response.sendRedirect("dashboard?logintype=admin");
            } else {
                response.sendRedirect("login?logintype=admin&error=errore");
            }
        }
    }
}