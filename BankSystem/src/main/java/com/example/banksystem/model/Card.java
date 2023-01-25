package com.example.banksystem.model;
import com.example.banksystem.observer.MovementObserver;
import com.example.banksystem.observer.Observer;
import com.example.banksystem.operation.MovementOperation;

import java.time.LocalDate;

//Classe carta che estende l'interfaccia Observer

public class Card implements Observer {
    private String card_number;
    private String CF_FK;
    private int CVV;
    private String Card_type;
    private LocalDate expiration_date;
    private String card_name;
    private double balance;
    private MovementOperation movements;

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


    /**
     * @return Restituisce il nome della carta
     */
    public String getCard_name() {
        return card_name;
    }

    /**
     * @return Restituisce il numero della carta
     */
    public String getCard_number() {
        return card_number;
    }

    /**
     * @param number_Card Imposta il numero della carta
     */
    public void setCard_number(String number_Card) {
        card_number = number_Card;
    }

    /**
     * @return Restituisce la chiave esterna CF_FK di Holder
     */
    public String getCF_FK() {
        return CF_FK;
    }

    /**
     * @return Restituisce il CVV della carta
     */
    public int getCVV() {
        return CVV;
    }

    /**
     * @return Restituisce il tipo della carta
     */
    public String getCard_type() {
        return Card_type;
    }

    /**
     * @return Restituisce la data di scadenza della carta
     */
    public LocalDate getDate() {
        return expiration_date;
    }

    /**
     * @return Restituisce il saldo della carta
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Metodo che serve per il prelievo dato il saldo specificato
     * @param balance Passa come parametro il saldo
     */
    public void withDraw(double balance){
        this.balance = this.balance - balance;
    }

    /**
     * Metodo che serve per il deposito dato il saldo specificato
     * @param balance Passa come parametro il saldo
     */
    public void deposit(double balance){
        this.balance = this.balance + balance;
    }

    /**
     * @return Restituisce l'implementazione dei movimenti della carta (DAO Pattern)
     */
    public MovementOperation getMovements() {
        return movements;
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
