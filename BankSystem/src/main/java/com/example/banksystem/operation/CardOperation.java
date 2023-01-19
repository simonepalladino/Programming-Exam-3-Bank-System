package com.example.banksystem.operation;

import com.example.banksystem.model.Card;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardOperation implements Operation<Card> {
    private final String url = "jdbc:sqlite:banksystem.sqlite";
    private Connection con;
    private  List<Card> cards = new ArrayList<>();

    public CardOperation() {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs;

            String card_name;
            String card_number;
            String CF_FK;
            int cvv;
            String card_type;
            LocalDate expiration_date;
            double balance;

            rs = stmt.executeQuery("SELECT * FROM Cards");
            while (rs.next()) {

                card_name = rs.getString("card_name");
                card_number = rs.getString("card_number");
                CF_FK = rs.getString("CF_FK");
                cvv = rs.getInt("cvv");
                card_type = rs.getString("card_type");
                expiration_date = rs.getDate("expiration_date").toLocalDate();
                balance = rs.getDouble("balance");

                System.out.println("Carta caricata: " +  card_name + " " + card_number + " " + expiration_date + " balance:" + balance);

                cards.add(new Card(card_name, card_number, CF_FK, cvv, card_type, expiration_date, balance));
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

    public CardOperation(String cf) {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs;

            String card_name;
            String card_number;
            String CF_FK;
            int cvv;
            String card_type;
            LocalDate expiration_date;
            double balance;

            rs = stmt.executeQuery("SELECT * FROM Cards WHERE CF_FK = '" + cf + "'");
            while (rs.next()) {

                card_name = rs.getString("card_name");
                card_number = rs.getString("card_number");
                CF_FK = rs.getString("CF_FK");
                cvv = rs.getInt("cvv");
                card_type = rs.getString("card_type");
                expiration_date = rs.getDate("expiration_date").toLocalDate();
                balance = rs.getDouble("balance");

                System.out.println("Carta " + card_number + " assegnata all'utente " + cf);

                cards.add(new Card(card_name, card_number, CF_FK, cvv, card_type, expiration_date, balance));
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
        for (Card card : cards) {
            if (card.getCard_number().equals(toFind))
                return Optional.ofNullable(card);
        }

        return Optional.empty();
    }

    @Override
    public List getAll() {
        return cards;
    }

    @Override
    public void add(Card c) {
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


            pstmt = con.prepareStatement("INSERT INTO Cards (card_number, CF_FK, cvv, card_type, expiration_date, card_name, balance) VALUES (?,?,?,?,?,?,?)");

            pstmt.setString(1, c.getCard_number());
            pstmt.setString(2, c.getCF_FK());
            pstmt.setInt(3, c.getCVV());
            pstmt.setString(4, c.getCard_type());
            pstmt.setDate(5, Date.valueOf(c.getDate()));
            pstmt.setString(6, c.getCard_name());
            pstmt.setDouble(7, c.getBalance());
            pstmt.executeUpdate();

            System.out.println("Carta aggiunta: " +  c.getCard_name() + " " + c.getCard_number() + " " + c.getDate());
            cards.add(c);
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
    public void delete(Card c) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Cards WHERE card_number=(?)");

            stmt.setString(1, c.getCard_number());
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

    public void deposit(Card c, double money) {
        c.deposit(money);
        updateBalance(c, c.getBalance() + money);
    }

    public void withdraw(Card c, double money) {
        c.withDraw(money);
        updateBalance(c, c.getBalance() - money);
    }

    public void updateBalance(Card c, double newBalance) {
                try {
                    con = DriverManager.getConnection(url);
                    PreparedStatement stmt = con.prepareStatement("UPDATE Cards SET balance = (?) where card_number = (?)");

                    stmt.setDouble(1, newBalance);
                    stmt.setString(2, c.getCard_number());
                    stmt.execute();
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