package com.example.banksystem;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    String url;
    Connection con;
    String logintype;
    String error;

    public void init() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        url = this.getClass().getResource("banksystem.sqlite").getPath();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        logintype = request.getParameter("logintype");
        //request.setAttribute("logintype", logintype);
        error = request.getParameter("error");
        //request.setAttribute("error", error);

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Boolean done = false;

        if (logintype.equals("user"))
            request.getRequestDispatcher("user-dashboard.html").forward(request, response);
        else {
            try {
                System.out.println(url);
                con = DriverManager.getConnection("jdbc:sqlite:"+url);
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
                response.sendRedirect("admin-dashboard.html");
            } else {
                response.sendRedirect("login?logintype=admin&error=errore");
            }
        }
    }
}