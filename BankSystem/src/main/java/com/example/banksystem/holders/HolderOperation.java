package com.example.banksystem.holders;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class HolderOperation implements Operation<Holder> {
    String url = "jdbc:sqlite:banksystem.sqlite";
    Connection con;
    private List<Holder> holders = new ArrayList<>();

    public HolderOperation() {
        //Carica tutti gli utenti salvati
        try {
            con = DriverManager.getConnection(url);
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

                holders.add(new Holder(username, firstname, lastname, cf, date_of_birth, contract_type, residence));
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
    public void update(Holder holder, String[] params) {

    }

    @Override
    public void delete(Holder holder) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Users WHERE CF=(?)");

            stmt.setString(1, holder.getCf());
            stmt.execute();

            holders.remove(get(holder.getCf()));
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
