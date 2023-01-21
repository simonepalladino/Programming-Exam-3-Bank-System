package com.example.banksystem.operation;

import com.example.banksystem.model.Holder;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class HolderOperation implements Operation<Holder>{
    String url = "jdbc:sqlite:banksystem.sqlite";
    Connection con;
    private List<Holder> holders = new ArrayList<>();


    public HolderOperation() {
        //Carica tutti gli utenti salvati
        initialization("SELECT * FROM Holders");
    }

    public HolderOperation(String toFind) {
        //Carica alcuni utenti salvati
        initialization("SELECT * FROM Holders WHERE (firstname || ' ' || lastname) LIKE '" + toFind + "%' OR username LIKE '" + toFind + "%'");
    }

    private void initialization(String query) {
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
            String password;
            int contract_cost;
            rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                username = rs.getString("username");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                cf = rs.getString("cf");
                date_of_birth = rs.getDate("date_of_birth");
                contract_type = rs.getString("contract_type");
                residence = rs.getString("residence");
                contract_cost = rs.getInt("contract_cost");
                password = rs.getString("password");

                if (query.contains("WHERE"))
                    System.out.println(" ! Trovato utente: " + firstname + " " + lastname + " " + cf);
                else
                    System.out.println("! Aggiunto utente: " + firstname + " " + lastname + " " + cf);
                holders.add(new Holder(username, firstname, lastname, cf, date_of_birth, contract_type, residence, contract_cost, password));
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
    public Holder get(String toFind) {
        for (Holder holder : holders) {
            if (holder.getCf().equals(toFind))
                return holder;
        }

        return null;
    }

    @Override
    public List<Holder> getAll() {
        return holders;
    }

    @Override
    public void add(Holder holder) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Holders values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, holder.getCf());
            stmt.setString(2, holder.getFirstname());
            stmt.setString(3, holder.getUsername());
            stmt.setString(4, holder.getLastname());
            stmt.setDate(5, (java.sql.Date) holder.getDate_of_birth());
            stmt.setString(6, holder.getContract_type());
            stmt.setString(7, holder.getResidence());
            stmt.setInt(8, holder.getContract_cost());
            stmt.setString(9, holder.getPassword());
            stmt.execute();

            System.out.println("!+ Aggiunto utente: " + holder.getFirstname() + " " + holder.getLastname() + " " + holder.getCf());
            holders.add(holder);

            switch (holder.getContract_type()){
                case ("Basic"):
                    holder.setContract_cost(0);
                    break;
                case ("Premium"):
                    holder.setContract_cost(100);
                    break;
                case("Enterprise"):
                    holder.setContract_cost(1000);
                    break;
            }

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
            Properties properties = new Properties();
            properties.setProperty("foreign_keys", "true");

            con = DriverManager.getConnection(url, properties);
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

    public void updatePassword(Holder holder, String newPassword) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("UPDATE Holders SET password=(?) WHERE CF=(?)");

            stmt.setString(1, newPassword);
            stmt.setString(2, holder.getCf());
            stmt.execute();

            System.out.println("!# Aggiornata password utente: " + holder.getCf());
            holder.setPassword(newPassword);
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
