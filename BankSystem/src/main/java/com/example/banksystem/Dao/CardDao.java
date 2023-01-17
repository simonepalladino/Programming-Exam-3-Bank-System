package com.example.banksystem.Dao;

import com.example.banksystem.model.Cards;
import java.sql.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardDao {
    Connection con;
    String classname = "org.sqlite.JDBC";
    Statement stmt ;
    PreparedStatement pstmt;

    public CardDao(){
    }

    public void Insert(Cards c){
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");

            Statement stmt;
            PreparedStatement pstmt;
            stmt = con.createStatement();


            pstmt = con.prepareStatement("INSERT INTO Cards (Number_card, CF_FK, CVV, Card_type, expiration_date) VALUES (?,?,?,?,?)");

            pstmt.setString(1, c.getNumber_Card());
            pstmt.setString(2, c.getCF_FK());
            pstmt.setInt(3, c.getCVV());
            pstmt.setString(4, c.getCard_type());
            pstmt.setDate(5, (Date) c.getDate());
            pstmt.executeUpdate();
            System.out.println("Inserimento in carta");

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
