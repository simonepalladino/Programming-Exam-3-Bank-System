package com.example.banksystem.model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cards {
    private String Number_Card;
    private String CF_FK;
    private int CVV;
    private String Card_type;
    private String expiration_date;
    private java.sql.Date date;

    public Cards(String Number_Card, String CF_FK, int CVV, String Card_type, String expiration_date){
        this.Number_Card = Number_Card;
        this.CF_FK = CF_FK;
        this.CVV = CVV;
        this.Card_type = Card_type;
        this.expiration_date = expiration_date;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = new java.sql.Date(sdf.parse(this.expiration_date).getTime());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public String getNumber_Card() {
        return Number_Card;
    }

    public void setNumber_Card(String number_Card) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = new java.sql.Date(sdf.parse(this.expiration_date).getTime());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
