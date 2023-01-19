package com.example.banksystem.Dao;

import com.example.banksystem.model.Cards;
import com.example.banksystem.model.Holder;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDao implements Operation<Cards>{

   private final String url = "jdbc:sqlite:banksystem.sqlite";
    private Connection con;
    private  List<Cards> cards = new ArrayList<>();


    public CardDao(){
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs;

            String Number_card;
            String cf_fk;
            int  CVV;
            String card_type;
            String expiration_date;

            rs = stmt.executeQuery("SELECT * FROM Cards");
            while (rs.next()) {

                card_name = rs.getString("card_name");
                card_number = rs.getString("card_number");
                CF_FK = rs.getString("CF_FK");
                cvv = rs.getInt("cvv");
                card_type = rs.getString("card_type");
                expiration_date = rs.getDate("expiration_date").toLocalDate();
                System.out.println("Carta caricata: " +  card_name + " " + card_number + " " + expiration_date);

                cards.add(new Card(card_name, card_number, CF_FK, cvv, card_type, expiration_date));
            }

        }catch (SQLException e) {
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


    @Override
    public Optional get(String toFind) {
        for (Cards card : cards) {
            if (card.getNumber_Card().equals(toFind))
                return Optional.ofNullable(card);
        }

        return Optional.empty();
    }

    @Override
    public List getAll() {
        return cards;
    }

    @Override
    public void add(Cards c) {
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


            pstmt = con.prepareStatement("INSERT INTO Cards (card_number, CF_FK, cvv, card_type, expiration_date, card_name) VALUES (?,?,?,?,?,?)");

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

    @Override
    public void update(Cards c, String[] params) {

    }

    @Override
    public void delete(Cards c) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Cards WHERE card_number=(?)");

            stmt.setString(1, c.getNumber_Card());
            stmt.execute();

            cards.remove(c);
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