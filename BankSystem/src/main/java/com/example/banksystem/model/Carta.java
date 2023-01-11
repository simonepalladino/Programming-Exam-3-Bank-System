package com.example.banksystem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class Carta {
    private String Numero_carta;
    private int CVV;
    private String Tipo;
    private String Data_scadenza;
    private java.sql.Date date;
    private double saldo;
    private String CF;

    public String geTipo() {
        return Tipo;
    }

    public Carta(String Numero_carta, int CVV, String Tipo, String Data_scadenza, String CF) throws SQLException {
        this.Numero_carta = Numero_carta;
        this.CVV = CVV;
        this.Tipo = Tipo;
        this.Data_scadenza = Data_scadenza;
        this.CF = CF;
        this.saldo = 0;
        Connection dbConnection = null;
        java.sql.PreparedStatement cmd = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = new java.sql.Date(sdf.parse(this.Data_scadenza).getTime());
        } catch (Exception e) {
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

            String CreazioneDB = "CREATE TABLE IF NOT EXISTS  Banca.Carta (Numero_carta varchar(30) primary key, CVV int(3) not null, Tipo varchar(20) not null check (Tipo IN ('carta di credito','bancomat')), Data_Scadenza date not null, Cf varchar(20),CONSTRAINT cf_fk FOREIGN KEY (Cf) REFERENCES Correntista(Cf))";

            // cmd = dbConnection.prepareStatement(updateTableSQL);
            cmd = dbConnection.prepareStatement(CreazioneDB);
            cmd.executeUpdate();
            System.out.println("TABELLA CARTA CREATA!");
            String Inserimento = "INSERT INTO Banca.Carta VALUES(?,?,?,?,?)";
            cmd = dbConnection.prepareStatement(Inserimento);
            cmd.setString(1, this.Numero_carta);
            cmd.setInt(2, this.CVV);
            cmd.setString(3, this.Tipo);
            cmd.setDate(4, date);
            cmd.setString(5, (this.CF));
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

    @Override
    public String toString() {
        return "Carta{" +
                "Numero_carta='" + Numero_carta + '\'' +
                ", CVV=" + CVV +
                ", Tipo='" + Tipo + '\'' +
                ", Data_scadenza='" + Data_scadenza + '\'' +
                ", date=" + date +
                ", Saldo=" + saldo +
                ", CF='" + CF + '\'' +
                '}';
    }
}
