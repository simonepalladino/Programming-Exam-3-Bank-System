package com.example.banksystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banca {
    private Correntista[] correntista;


    //COSTRUTTORE DELLA BANCA CHE CREA IL DATABASE.
    public Banca() throws SQLException {

        Connection dbConnection = null;
        java.sql.PreparedStatement cmd = null;

        try {
            String driver = "com.mysql.jdbc.Driver";

            Class.forName(driver);

            // Creiamo la stringa di connessione
            String url = "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            // Otteniamo una connessione con username e password
            dbConnection = DriverManager.getConnection(url, "root", "parthenope");
            //String updateTableSQL = "CREATE TABLE testdb.clienti( name varchar(100), age int, address varchar(100) )";
            String CreazioneDB = "CREATE DATABASE IF NOT EXISTS Banca";

            // cmd = dbConnection.prepareStatement(updateTableSQL);
            cmd = dbConnection.prepareStatement(CreazioneDB);
            cmd.executeUpdate();

            System.out.println("DATABASE BANCA CREATO!");

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
}
