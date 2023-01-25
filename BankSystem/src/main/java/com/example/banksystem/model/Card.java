package com.example.banksystem.model;
import com.example.banksystem.observer.MovementObserver;
import com.example.banksystem.observer.Observer;
import com.example.banksystem.operation.MovementOperation;

import java.time.LocalDate;

/**
 * Classe carta che estende l'interfaccia Observer
 */
public class Card implements Observer {
    private String card_number;
    private String CF_FK;
    private int CVV;
    private String Card_type;
    private LocalDate expiration_date;
    private String card_name;
    private double balance;
    private MovementOperation movements;
    private int lastMovementID;

    /**
     * Inizializza una carta con gli stessi valori delle colonne della tabella "CARDS" presente nel database.
     * Una volta fatto, verifica se aggiungere l'oggetto alla lista di osservatori da dover aggiornare.
     * @param card_name Nome della carta che si vuole inizializzare
     * @param cardnumber Numero della carta che si vuole inizializzare
     * @param CF_FK Chiave esterna del correntista che si vuole inizializzare
     * @param cvv CVV della carta che si vuole inizializzare
     * @param card_type Tipo della carta che si vuole inizializzare
     * @param expiration_date Data di scadenza della carta che si vuole inizializzare
     * @param balance Saldo della carta che si vuole inizializzare
     */
    public Card(String card_name, String cardnumber, String CF_FK, int cvv, String card_type, LocalDate expiration_date, double balance) {
        this.card_name = card_name;
        this.card_number = cardnumber;
        this.CF_FK = CF_FK;
        this.CVV = cvv;
        this.Card_type = card_type;
        this.expiration_date = expiration_date;
        this.balance = balance;

        //Aggiunge l'Holder alla lista di Movements
        MovementObserver.getInstance().addObserver(this);

        //Aggiorna le carte associate al conto all'interno di un CardOperation
        movements = new MovementOperation(card_number, true);
    }


    public String getCard_name() {
        return card_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String number_Card) {
        card_number = number_Card;
    }

    public String getCF_FK() {
        return CF_FK;
    }

    public int getCVV() {
        return CVV;
    }

    public String getCard_type() {
        return Card_type;
    }

    public LocalDate getDate() {
        return expiration_date;
    }

    public double getBalance() {
        return balance;
    }

    public void withDraw(double balance){
        this.balance = this.balance - balance;
    }

    public void deposit(double balance){
        this.balance = this.balance + balance;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public MovementOperation getMovements() {
        return movements;
    }

    public int getLastMovementID() {
        return lastMovementID;
    }

    /**
     * Aggiorna la lista dei movimenti della carta quando richiesto dall'Observer
      @see com.example.banksystem.observer.MovementObserver
     */
    @Override
    public void update() {
        System.out.println("?! Aggiorno la lista dei movimenti della carta " + card_number);
        movements = new MovementOperation(card_number, true);
    }
}
