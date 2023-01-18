package com.example.banksystem.Dao;

import java.security.SecureRandom;

import com.example.banksystem.model.Cards;
import com.example.banksystem.model.Users;

import java.sql.*;

//classe
public class UsersDao {
    private String  url ;
   private Connection con;
   private String classname= "org.sqlite.JDBC";
   private Statement stmt ;
   private PreparedStatement pstmt;



    public UsersDao(){
    }

    public void Insert(Users u){
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //url = getClass().getResource("banksystem.sqlite").getPath();
            con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");

            Statement stmt ;
            PreparedStatement pstmt;
            ResultSet rs;

            stmt = con.createStatement();


            //String inserting = "INSERT INTO Users (CF, Name, Username, Surname, Date_of_birth, Contract_type, Residence) VALUES (?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement("INSERT INTO Users (CF, Name, Username, Surname, Date_of_birth, Contract_type, Residence) VALUES (?,?,?,?,?,?,?)");

            pstmt.setString(1, u.getCf());
            pstmt.setString(2, u.getName());
            pstmt.setString(3, "matt");
            pstmt.setString(4, u.getSurname());
            pstmt.setDate(5, null);
            pstmt.setString(6, u.getContract_type());
            pstmt.setString(7, u.getResidence());
            pstmt.executeUpdate();
            System.out.println("Inserimento");

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

        String theAlphaNumericS;
        StringBuilder builder;
        String numeri = "0123456789";
        String perRandom = numeri;
        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder(12);

        for (int i = 0; i< 12; i++) {
                int randomInt = sr.nextInt(perRandom.length());
                char randomChar = perRandom.charAt(randomInt);
                sb.append(randomChar);
            }
            System.out.println(sb.toString());

       // Cards c = new Cards(sb.toString(), u.getCf(), 322, "Bancomat", "2033");


        }



    }

