package com.example.banksystem.model;
import com.example.banksystem.observer.MovementObserver;
import com.example.banksystem.observer.Observer;
import com.example.banksystem.operation.MovementOperation;

import java.time.LocalDate;

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

    public void setCard_name(String card_name) {
        this.card_name = card_name;
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

    public void setCF_FK(String CF_FK) {
        this.CF_FK = CF_FK;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getCard_type() {
        return Card_type;
    }

    public void setCard_type(String card_type) {
        Card_type = card_type;
    }

    public LocalDate getDate() {
        return expiration_date;
    }

    public void setDate(LocalDate date) {
        expiration_date = date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void withDraw(double balance){
        this.balance = this.balance - balance;
    }

    public void deposit(double balance){
        this.balance = this.balance + balance;
    }

    public void update() {
        System.out.println("?! Aggiorno la lista dei movimenti della carta " + card_number);
        movements = new MovementOperation(card_number, true);
    }
}
