package com.example.banksystem.servlet;

import java.io.*;
import java.sql.*;

import com.example.banksystem.Actions;
import com.example.banksystem.operation.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


/**
 * Servlet per il login sia per i correntisti che per gli admin
 */
@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
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

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        logintype = request.getParameter("logintype");
        error = request.getParameter("error");

        session.setAttribute("holderOperation", Actions.getInstance().holderOperation);
        session.setAttribute("cardOperation", Actions.getInstance().cardOperation);
        session.setAttribute("movementOperation", Actions.getInstance().movementOperation);

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * @param request  an {@link HttpServletRequest} oggetto che contiene la richiesta che il client ha fatto alla servlet
     * @param response an {@link HttpServletResponse} oggetto che contiene la risposta che la servlet invia al client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Boolean done = false;

        HttpSession session = request.getSession();
        session.setAttribute("usertext", request.getParameter("username").toString());
        String username = null, password = null, cf = null;

        if (logintype.equals("user")) {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");
                Statement stmt = con.createStatement();
                ResultSet rs;
                username = request.getParameter("username");
                password = request.getParameter("password");
                rs = stmt.executeQuery("SELECT * FROM Holders WHERE username='" + username + "' AND password='" + password + "'");

                if (rs.next()) {
                    cf = rs.getString("cf");
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
                session.setAttribute("selectedHolder", Actions.getInstance().holderOperation.get(cf));

                if (password.equals(cf))
                    response.sendRedirect("user-setpassword?cf=" + cf);
                else
                    response.sendRedirect("dashboard?logintype=user");
            } else {
                response.sendRedirect("login?logintype=user&error=errore");
            }
        } else {
            try {
                    con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");
                    Statement stmt = con.createStatement();
                    ResultSet rs;

                    username = request.getParameter("username");
                    password = request.getParameter("password");
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