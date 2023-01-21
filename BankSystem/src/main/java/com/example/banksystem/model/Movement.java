package com.example.banksystem.model;

import com.example.banksystem.Actions;

import java.time.LocalDate;

public class Movement {
    private int id_mov;
    private String product_id;
    private LocalDate mov_date;
    private String card_number_FK;
    private double price;

    //Inizializzazione di tutti i parametri
    public Movement(int Id_mov, String product_id, LocalDate Mov_date, String card_number_FK, double price) {
        this.id_mov = Id_mov;
        this.product_id = product_id;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;
        this.price = price;
    }

    //Inizializzazione di tutti i parametri senza alcun prezzo specificato
    public Movement(int Id_mov, String product_id, LocalDate Mov_date, String card_number_FK) {
        this.id_mov = Id_mov;
        this.product_id = product_id;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;

        this.price = Actions.getInstance().productOperation.get(product_id).getPrice();
    }

    //Inizializzazione del movimento senza specificarne l'ID (per iterare i movimenti di una singola carta)
    public Movement(String product_id, LocalDate Mov_date, String card_number_FK, double price) {
        this.id_mov = -1;
        this.product_id = product_id;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;
        this.price = price;
    }

    //Inizializzazione del movimento senza specifica dell'ID e con reperimento automatico del prezzo
    public Movement(String product_id, LocalDate Mov_date, String card_number_FK) {
        this.id_mov = -1;
        this.product_id = product_id;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;

        this.price = Actions.getInstance().productOperation.get(product_id).getPrice();
    }

    public void setId_mov(int Id_mov){
        this.id_mov = Id_mov;
    }

    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }

    public void setMov_date (LocalDate Mov_date){
        this.mov_date = Mov_date;
    }

    public void setCard_number_FK(String Number_card){
        this.card_number_FK = Number_card;
    }

    public int getId_mov(){
        return this.id_mov;
    }

    public String getProduct_id(){
        return this.product_id;
    }

    public LocalDate getMov_date(){
        return this.mov_date;
    }

    public String getCard_number_FK(){
        return this.card_number_FK;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
