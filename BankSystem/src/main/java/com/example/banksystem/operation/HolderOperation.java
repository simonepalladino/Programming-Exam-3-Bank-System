package com.example.banksystem.operation;

import com.example.banksystem.LoginServlet;
import com.example.banksystem.model.Holder;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import java.util.ArrayList;
import java.util.List;



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
            int contract_cost;
            rs = stmt.executeQuery("SELECT * FROM Holders");
            while ( rs.next() ) {

                username = rs.getString("username");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                cf = rs.getString("cf");
                date_of_birth = rs.getDate("date_of_birth");
                contract_type = rs.getString("contract_type");
                residence = rs.getString("residence");
                contract_cost = rs.getInt("contract_cost");

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
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Holders values (?, ?, ?, ?, ?, ?, ?, ?)");

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
    public void delete(Holder holder) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Holders WHERE CF=(?)");

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
