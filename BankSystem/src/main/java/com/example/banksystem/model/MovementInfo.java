package com.example.banksystem.model;

import java.time.LocalDate;


/* Un miscuglio tra informazioni riguardo la carta e il movimento:
    utile per scopi informativi
 */
public class MovementInfo {
    private String mov_date_string;
    private LocalDate mov_date;
    private String card_number;
    private double price;
    private String product_name;
    private String quote;
    private String type;

    public MovementInfo(LocalDate mov_date, String card_number, double price, String product_name, String quote, String type) {
        this.mov_date_string = mov_date.toString();
        this.mov_date = mov_date;
        this.card_number = card_number;
        this.price = price;
        this.product_name = product_name;
        this.quote = quote;
        this.type = type;
    }

    public String getMov_date_string() {
        return mov_date_string;
    }

    public LocalDate getMov_date() {
        return mov_date;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

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
     * @return Ottiene la tipologia del prodotto impostato
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
