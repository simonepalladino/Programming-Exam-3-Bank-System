package com.example.banksystem.testbankaccount;

import java.sql.*;

public class BankDB {
    String url = "jdbc:sqlite:banksystem.sqlite";
    Connection con;

    public void addAccount(String username, String password, String strunz, double cacca) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Admins values (?, ?)");

            stmt.setString(1, username);
            stmt.setString(2, password);
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

    public void updateBalance(String bank_number, double newBalance) {
        try {
            con = DriverManager.getConnection(url);
            //PreparedStatement stmt = con.prepareStatement("UPDATE Admins SET balance = (?) where bank_number = (?)");

            //stmt.setDouble(1, newBalance);
            //stmt.setString(2, bank_number);
            //stmt.execute();
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

    public String[] findAccount(String bank_number) {
        try {
            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs;
            String firstname = null;
            String lastname = null;
            String balance = null;

            rs = stmt.executeQuery("SELECT * FROM Account WHERE bank_number = " + bank_number);
            while ( rs.next() ) {
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                balance = rs.getString("balance");
                //System.out.println(firstname + " " + lastname + " " + balance);
            }
            con.close();

            return new String[] {firstname, lastname, balance};
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

        return null;
    }
}
