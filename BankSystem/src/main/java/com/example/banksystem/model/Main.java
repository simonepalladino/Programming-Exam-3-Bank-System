package com.example.banksystem.model;

import com.example.banksystem.model.Banca;
import com.example.banksystem.model.Correntista;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        Banca bancaprogetto = new Banca();
        Correntista correntista = new Correntista("12345", "marco", "carta", "via bernini 80", "20/10/2000", "standard");
        // Carta carta = new Carta("1000 2000 3000 4000", 345, "bancomat", "20/10/2030", "12345");
        correntista.getcarte();
    }
}
