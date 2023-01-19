package com.example.banksystem;

import java.io.*;
import java.sql.*;

import com.example.banksystem.operation.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    public static Factory factory = new Factory();
    public static HolderOperation holderOperation = null;
    public static CardOperation cardOperation = null;
    public static MovementOperation movementOperation = null;
    //public static Iterator<Movements> movementsIterator = null;

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

        logintype = request.getParameter("logintype");
        error = request.getParameter("error");

        if (holderOperation == null)
            holderOperation = (HolderOperation) factory.getOperation(Factory.OperationType.HOLDER);
        if (cardOperation == null)
            cardOperation = (CardOperation) factory.getOperation(Factory.OperationType.CARD);
        if (movementOperation == null)
            movementOperation = (MovementOperation) factory.getOperation(Factory.OperationType.MOVEMENTS);
        //if (movementsIterator == null)
          //  movementsIterator = operationFactory.getIterator(OperationFactory.OperationType.MOVEMENTS);
        //Iterator<Movements> iterator = operationFactory.getIterator(OperationFactory.OperationType.MOVEMENTS);

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Boolean done = false;

        if (logintype.equals("user"))
            request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);
        else {
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
                //request.getRequestDispatcher("admin-dashboard.html").forward(request, response);
                response.sendRedirect("admin-dashboard.jsp");
            } else {
                response.sendRedirect("login?logintype=admin&error=errore");
            }
        }
    }
}