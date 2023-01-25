package com.example.banksystem.model;

import com.example.banksystem.Actions;

import java.time.LocalDate;

public class Movement {
    private int id_mov;
    private String product_id;
    private LocalDate mov_date;
    private String card_number_FK;
    private double price;

    /**
     * Inizializzazione di tutti i parametri
     */
    public Movement(int Id_mov, String product_id, LocalDate Mov_date, String card_number_FK, double price) {
        this.id_mov = Id_mov;
        this.product_id = product_id;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;
        this.price = price;
    }

    /**
     * Inizializzazione dei parametri senza alcun prezzo specificato
     * @param Id_mov id del movimento (chiave primaria)
     * @param product_id id del prodotto (chiave esterna di Product)
     * @param Mov_date data dell'operazione
     * @param card_number_FK  chiave esterna della tabella CARDS
     */
    public Movement(int Id_mov, String product_id, LocalDate Mov_date, String card_number_FK) {
        this.id_mov = Id_mov;
        this.product_id = product_id;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;

        this.price = Actions.getInstance().productOperation.get(product_id).getPrice();
    }

    /**
     * Inizializzazione del movimento senza specificarne l'ID (per iterare i movimenti di una singola carta)
     * @param product_id id del prodotto da inserire
     * @param Mov_date data movimento
     * @param card_number_FK chiave esterna della tabella CARDS
     * @param price prezzo del prodotto
     */
    public Movement(String product_id, LocalDate Mov_date, String card_number_FK, double price) {
        this.id_mov = -1;
        this.product_id = product_id;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;
        this.price = price;
    }

    /**
     * Inizializzazione del movimento senza specifica dell'ID e con reperimento automatico del prezzo
     * @param product_id id del prodotto
     * @param Mov_date data movimento
     * @param card_number_FK chiave esterna della tabella CARDS
     */
    public Movement(String product_id, LocalDate Mov_date, String card_number_FK) {
        this.id_mov = -1;
        this.product_id = product_id;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;

        this.price = Actions.getInstance().productOperation.get(product_id).getPrice();
    }

    /**
     * @param Id_mov imposta l'id del movimento
     */
    public void setId_mov(int Id_mov){
        this.id_mov = Id_mov;
    }

    /**
     * @param product_id imposta l'id del prodotto
     */
    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }

    /**
     * @param Mov_date imposta la data del movimento
     */
    public void setMov_date (LocalDate Mov_date){
        this.mov_date = Mov_date;
    }

    /**
     * @param Number_card imposta il numero della carta
     */
    public void setCard_number_FK(String Number_card){
        this.card_number_FK = Number_card;
    }

    /**
     * @return restituisce l'id del movimento
     */
    public int getId_mov(){
        return this.id_mov;
    }

    /**
     * @return restituisce l'id del prodotto
     */
    public String getProduct_id(){
        return this.product_id;
    }

    /**
     * @return restituisce la data del movimento
     */
    public LocalDate getMov_date(){
        return this.mov_date;
    }

    /**
     * @return restituisce la chiave esterna della tabella CARDS presente nel database
     */
    public String getCard_number_FK(){
        return this.card_number_FK;
    }

    /**
     * @return restituisce il prezzo del prodotto
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price imposta il prezzo del prodotto
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
