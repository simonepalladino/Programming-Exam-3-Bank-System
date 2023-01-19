package com.example.banksystem.model;
import java.time.LocalDate;

public class Card {
    private String Number_Card;
    private String CF_FK;
    private int CVV;
    private String Card_type;
    LocalDate expiration_date;
    private String card_name;
    private double balance;

    public Card(String card_name, String number_Card, String CF_FK, int cvv, String card_type, LocalDate expiration_date, double balance) {
        this.card_name = card_name;
        this.Number_Card = number_Card;
        this.CF_FK = CF_FK;
        this.CVV = cvv;
        this.Card_type = card_type;
        this.expiration_date = expiration_date;
        this.balance = balance;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_number() {
        return Number_Card;
    }

    public void setCard_number(String number_Card) {
        Number_Card = number_Card;
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
}
