package com.example.banksystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.*;
import java.sql.*;

public class Movimenti {
    private String num_carta;
    private String data_movimento;
    private String tipo_movimento;
    private int id_movimento;

    /*
    public Movimenti(String num_carta, String data_movimento, String tipo_movimento, int id_movimento) throws SQLException {

        this.
        Connection dbConnection = null;
        java.sql.PreparedStatement cmd = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data_movimento = new java.sql.Date(sdf.parse(this.data_movimento).getTime());
        }
        catch (Exception e) {
            System.out.println(e.toString());


        }




        try {
            String driver = "com.mysql.jdbc.Driver";

            Class.forName(driver);

            // Creiamo la stringa di connessione
            String url = "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            // Otteniamo una connessione con username e password
            dbConnection = DriverManager.getConnection(url, "root", "parthenope");
            //String updateTableSQL = "CREATE TABLE testdb.clienti( name varchar(100), age int, address varchar(100) )";

            String CreazioneDB = "CREATE TABLE IF NOT EXISTS  Banca.Correntista (Cf varchar(20) primary key, Nome varchar(20) not null,Cognome varchar(20) not null,Residenza varchar(20), Data_nascita date, TipoConto varchar(20) not null check (TipoConto IN ('standard','promozionale','prioritario'))) ";

            // cmd = dbConnection.prepareStatement(updateTableSQL);
            cmd = dbConnection.prepareStatement(CreazioneDB);
            cmd.executeUpdate();
            System.out.println("TABELLA CORRENTISTA CREATA!");
            String Inserimento = "INSERT INTO Banca.Correntista VALUES(?,?,?,?,?,?)";
            cmd = dbConnection.prepareStatement(Inserimento);
            cmd.setString(1,this.Cf);
            cmd.setString(2,this.nome);
            cmd.setString(3,this.cognome);
            cmd.setString(4,this.residenza);
            cmd.setDate(5,d);
            cmd.setString(6,this.tipoConto);
            cmd.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (cmd != null) {
                cmd.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
    }

     */

}
