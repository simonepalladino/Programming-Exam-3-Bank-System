package com.example.banksystem.model;

import com.example.banksystem.Actions;

public class Product {
    private String product_name;
    private String product_id;
    private double price;
    private double discountPrice = 0;
    private String quote;
    private String type;
    private String image;

    /**
     * Inizializza un prodotto con gli stessi valori delle colonne della tabella "PRODUCTS" presente nel database
     * @param product_name nome del prodotto
     * @param product_id id del prodotto
     * @param price prezzo del prodotto
     * @param quote descrizione del prodotto
     * @param type tipo del prodotto
     * @param image immagine presa dalla cartella assets/img
     */
    public Product(String product_name, String product_id, double price, String quote, String type, String image) {
        this.product_name = product_name;
        this.product_id = product_id;
        this.price = price;
        this.quote = quote;
        this.type = type;
        this.image = "assets/img/" + image;
    }

    /**
     * Inizializza un prodotto con gli stessi valori delle colonne della tabella "PRODUCTS" presente nel database
     * @param product_name nome del prodotto
     * @param product_id id del prodotto
     * @param price prezzo del prodotto
     * @param quote descrizione del prodotto
     * @param type tipo del prodotto
     */
    public Product(String product_name, String product_id, double price, String quote, String type) {
        this.product_name = product_name;
        this.product_id = product_id;
        this.price = price;
        this.quote = quote;
        this.type = type;
        this.image = null;
    }

    /**
     * @return restituisce il nome del prodotto
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * @return restituisce l'id del prodotto
     */
    public String getProduct_id() {
        return product_id;
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

    /**
     * @return restituisce la descrizione del prodotto
     */
    public String getQuote() {
        return quote;
    }

    /**
     * @return restituisce il tipo del prodotto
     */
    public String getType() {
        return type;
    }

    /**
     * @param type imposta il tipo del prodotto
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return restituisce l'immagine del prodotto
     */
    public String getImage() {
        return image;
    }

    /**
     * @return restituisce il prezzo scontato del prodotto
     */
    public double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * @param discountPrice imposta il prezzo scontato del prodotto
     */
    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
