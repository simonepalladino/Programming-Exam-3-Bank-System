package com.example.banksystem.model;

public class Products {
    private String product_name;
    private int product_id;
    private int price;
    private String quote;

    public Products(String product_name, int product_id, int price, String quote){
        this.product_name = product_name;
        this.product_id = product_id;
        this.price = price;
        this.quote = quote;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
