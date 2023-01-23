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

    public void setMov_date_string(String mov_date_string) {
        this.mov_date_string = mov_date_string;
    }

    public LocalDate getMov_date() {
        return mov_date;
    }

    public void setMov_date(LocalDate mov_date) {
        this.mov_date = mov_date;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
