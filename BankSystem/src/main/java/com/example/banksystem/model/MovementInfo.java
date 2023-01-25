package com.example.banksystem.model;

import java.time.LocalDate;

public class MovementInfo {
    private String mov_date_string;
    private LocalDate mov_date;
    private String card_number;
    private double price;
    private String product_name;
    private String quote;
    private String type;

    /**
     * Un miscuglio tra informazioni riguardo la carta e il movimento: utile per scopi informativi
     * Utilizzato per popolare le informazioni riguardo un movimento
     * @param mov_date data del movimento
     * @param card_number numero della carta
     * @param price prezzo pagato
     * @param product_name nome del prodotto
     * @param quote descrizione del prodotto
     * @param type tipologia del prodotto
     * @see Movement
     * @see Product
     */
    public MovementInfo(LocalDate mov_date, String card_number, double price, String product_name, String quote, String type) {
        this.mov_date_string = mov_date.toString();
        this.mov_date = mov_date;
        this.card_number = card_number;
        this.price = price;
        this.product_name = product_name;
        this.quote = quote;
        this.type = type;
    }

    /**
     * Facilita l'ottenimento della data del movimento nel file JSP; ridondante ma utile per quanto concerne la facilità d'implementazione
     * @return Restituisce la data di acquisto (o del movimento)
     */
    public String getMov_date_string() {
        return mov_date_string;
    }

    /**
     * @return Restituisce la data di acquisto (o del movimento)
     */
    public LocalDate getMov_date() {
        return mov_date;
    }

    /**
     * @return Restituisce il numero di carta
     */
    public String getCard_number() {
        return card_number;
    }

    /**
     * @param card_number Imposta il numero della carta
     */
    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    /**
     * @return Restitusce il prezzo del prodotto
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Imposta il prezzo del prodotto
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return Restituisce il nome del prodotto
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * @return Restituisce la descrizione del prodotto
     */
    public String getQuote() {
        return quote;
    }

    /**
     * @return Restituisce la tipologia del prodotto impostato
     */
    public String getType() {
        return type;
    }

    /**
     * @param type Imposta la tipologia del prodotto che si intende visualizzare
     */
    public void setType(String type) {
        this.type = type;
    }
}
