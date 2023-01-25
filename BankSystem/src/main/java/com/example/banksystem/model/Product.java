package com.example.banksystem.model;

import java.time.LocalDate;

public class Product {
    private String product_name;
    private String product_id;
    private double price;
    private double discountPrice = 0;
    private String quote;
    private String type;
    private String image;

    public Product(String product_name, String product_id, double price, String quote, String type, String image) {
        this.product_name = product_name;
        this.product_id = product_id;
        this.price = price;
        this.quote = quote;
        this.type = type;
        this.image = "assets/img/" + image;
    }

    public Product(String product_name, String product_id, double price, String quote, String type) {
        this.product_name = product_name;
        this.product_id = product_id;
        this.price = price;
        this.quote = quote;
        this.type = type;
        this.image = null;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_id() {
        return product_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
