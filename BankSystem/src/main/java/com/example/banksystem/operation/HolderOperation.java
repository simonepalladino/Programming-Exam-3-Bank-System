package com.example.banksystem.Dao;

import com.example.banksystem.model.Cards;
import com.example.banksystem.model.Holder;

import java.security.SecureRandom;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class HolderOperation implements Operation<Holder> {
    String url = "jdbc:sqlite:banksystem.sqlite";
    Connection con;
    private List<Holder> holders = new ArrayList<>();

    public HolderOperation() {
        //Carica tutti gli utenti salvati
        try {

            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");
            Statement stmt = con.createStatement();
            ResultSet rs;
            String username;
            String firstname;
            String lastname;
            String cf;
            Date date_of_birth;
            String contract_type;
            String residence;
            rs = stmt.executeQuery("SELECT * FROM Users");
            while ( rs.next() ) {

                username = rs.getString("Username");
                firstname = rs.getString("Name");
                lastname = rs.getString("Surname");
                cf = rs.getString("CF");
                date_of_birth = rs.getDate("Date_of_birth");
                contract_type = rs.getString("Contract_type");
                residence = rs.getString("Residence");

                holders.add(new Holder(username, firstname, lastname, cf, date_of_birth, contract_type, residence, contract_cost));
                System.out.println("Caricato utente: " + firstname + " " + lastname + " " + cf);
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
    }

    @Override
    public Optional<Holder> get(String toFind) {
        for (Holder holder : holders) {
            if (holder.getCf().equals(toFind))
                return Optional.ofNullable(holder);
        }

        return Optional.empty();
    }

    @Override
    public List<Holder> getAll() {
        return holders;
    }

    @Override
    public void add(Holder holder) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Users values (?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, holder.getCf());
            stmt.setString(2, holder.getFirstname());
            stmt.setString(3, holder.getUsername());
            stmt.setString(4, holder.getLastname());
            stmt.setDate(5, (java.sql.Date) holder.getDate_of_birth());
            stmt.setString(6, holder.getContract_type());
            stmt.setString(7, holder.getResidence());
            stmt.setInt(8, holder.getContract_cost());
            stmt.execute();

            holders.add(holder);
            CardDao c = new CardDao();
            String theAlphaNumericS;
            StringBuilder builder;
            String numeri = "0123456789";
            String perRandom = numeri;
            SecureRandom sr = new SecureRandom();
            StringBuilder cartnumber = new StringBuilder(12);
            int cvv;
            Random r = new Random();

            for (int i = 0; i< 12; i++) {
                int randomInt = sr.nextInt(perRandom.length());
                char randomChar = perRandom.charAt(randomInt);
                cartnumber.append(randomChar);
            }
           cvv = r.nextInt(999)+100;
            LocalDate x = LocalDate.now();
            DateTimeFormatter y = DateTimeFormatter.ofPattern("dd/MM/yy");
           LocalDate scadenza_carta = x.plusYears(10);
            x.format(y).toString();
            System.out.println(x.format(y));
            //String Number_Card, String CF_FK, int CVV, String Card_type, String expiration_date
            c.add(new Cards(cartnumber.toString(),holder.getCf(),cvv,"Bancomat","21/21/21"));

            System.out.println("inserito user e carta");
            //connessione al database
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
    public void update(Holder holder, String[] params) {

    }

    @Override
    public void delete(Holder holder) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Users WHERE CF=(?)");

            stmt.setString(1, holder.getCf());
            stmt.execute();

            holders.remove(holder);
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
