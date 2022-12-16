package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Correntista {
    private String Cf;
    private String nome ;
    private String cognome;
    private String residenza ;
    private String tipoConto;

    public Correntista(String cf, String nome) throws SQLException {
        this.Cf = cf;
        this.nome = nome;
        Connection dbConnection = null;
        java.sql.PreparedStatement cmd = null;

        try {
            String driver = "com.mysql.jdbc.Driver";

            Class.forName(driver);

            // Creiamo la stringa di connessione
            String url = "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            // Otteniamo una connessione con username e password
            dbConnection = DriverManager.getConnection(url, "root", "progettoprog3");
            //String updateTableSQL = "CREATE TABLE testdb.clienti( name varchar(100), age int, address varchar(100) )";

            String CreazioneDB = "CREATE TABLE IF NOT EXISTS  Banca.Correntista (Cf char(10) primary key, Nome varchar(20) not null) ";

            // cmd = dbConnection.prepareStatement(updateTableSQL);
            cmd = dbConnection.prepareStatement(CreazioneDB);
            cmd.executeUpdate();
            System.out.println("TABELLA CORRENTISTA CREATA!");
            String Inserimento = "INSERT INTO Banca.Correntista VALUES(?,?)";
            cmd = dbConnection.prepareStatement(Inserimento);
            cmd.setString(1,this.Cf);
            cmd.setString(2,this.nome);
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
}
