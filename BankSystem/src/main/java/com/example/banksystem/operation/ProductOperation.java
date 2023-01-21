package com.example.banksystem.operation;

import com.example.banksystem.model.Movement;
import com.example.banksystem.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductOperation {
    private final String url = "jdbc:sqlite:banksystem.sqlite";
    private Connection con;
    private List<Product> products = new ArrayList<>();

    public ProductOperation() {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs;
            String product_name;
            String product_id;
            double price;
            String quote;
            String type;

            rs = stmt.executeQuery("SELECT * FROM Products");

            while (rs.next()) {
                product_name = rs.getString("product_name");
                product_id = rs.getString("product_id");
                price = rs.getDouble("price");
                quote = rs.getString("quote");
                type = rs.getString("type");

                System.out.println(" $ E' stato inizializzato il prodotto: " + product_id + " " + product_name);

                products.add(new Product(product_name, product_id, price, quote, type));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Product get(String toFind) {
        for (Product product : products) {
            if (product.getProduct_id().equals(toFind))
                return product;
        }

        return null;
    }

    public List getAll() {
        return products;
    }
}

