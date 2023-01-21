package com.example.banksystem.model;

import java.time.LocalDate;

public class Product {
    private String product_name;
    private String product_id;
    private double price;
    private String quote;
    private String type;

    public Product(String product_name, String product_id, double price, String quote, String type) {
        this.product_name = product_name;
        this.product_id = product_id;
        this.price = price;
        this.quote = quote;
        this.type = type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
