package com.example.banksystem;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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


        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            url = getClass().getResource("banksystem.sqlite").getPath();
            con = DriverManager.getConnection("jdbc:sqlite:"+url);

            Statement stmt;
            PreparedStatement pstmt;
            stmt = con.createStatement();


            pstmt = con.prepareStatement("INSERT INTO Users (CF, Name, Username, Surname, Date_of_birth, Contract_type, Residence) VALUES (?,?,?,?,?,?,?)");

            pstmt.setString(1, CF);
            pstmt.setString(2, Name);
            pstmt.setString(3, "matt");
            pstmt.setString(4, Surname);
            pstmt.setDate(5, null);
            pstmt.setString(6, Account_type);
            pstmt.setString(7, null);
            pstmt.executeUpdate();
            System.out.println("Inserito");

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


    }
}