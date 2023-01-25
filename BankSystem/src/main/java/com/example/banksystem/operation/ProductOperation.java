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

    /**
     * Inizializza la lista di tutti i prodotti
     */
    public ProductOperation() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Inizializza i valori predefiniti
        initializeDefaults();

        try {
            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs;
            String product_name;
            String product_id;
            double price;
            String quote;
            String type;
            String image;

            rs = stmt.executeQuery("SELECT * FROM Products");

            while (rs.next()) {
                product_name = rs.getString("product_name");
                product_id = rs.getString("product_id");
                price = rs.getDouble("price");
                quote = rs.getString("quote");
                type = rs.getString("type");
                image = rs.getString("image");

                System.out.println(" $ E' stato inizializzato il prodotto: " + product_id + " " + product_name);

                if (image != null)
                    products.add(new Product(product_name, product_id, price, quote, type, image));
                else
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

    /**
     * Ricerca all'intero della lista di prodotti "products" se è presente il prodotto che si sta cercando
     * @param toFind id del prodotto che si vuole cercare
     * @return il prodotto trovato, altrimenti null
     */
    public Product get(String toFind) {
        for (Product product : products) {
            if (product.getProduct_id().equals(toFind))
                return product;
        }

        return null;
    }

    /**
     * @return la lista dei prodotti
     */
    public List getAll() {
        return products;
    }

    /**
     * Inserisce all'interno della tabella "Products" del database alcuni elementi chiave necessari per il funzionamento
     * base del software
     */
    private void initializeDefaults() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT OR REPLACE INTO Products (product_name, product_id, price, quote, type) VALUES (?, ?, ?, ?, ?)");

            // Aggiunge prodotti predefiniti necessari per il funzionamento corretto
            stmt.setString(1, "Welcome Bonus");
            stmt.setString(2, "welcomepremium");
            stmt.setInt(3, 10);
            stmt.setString(4, "A \"Premium\" welcome!");
            stmt.setString(5, "deposit");
            stmt.addBatch();

            stmt.setString(1, "Welcome Bonus");
            stmt.setString(2, "welcomeenterprise");
            stmt.setInt(3, 20);
            stmt.setString(4, "An \"Enterprise\" welcome!");
            stmt.setString(5, "deposit");
            stmt.addBatch();

            stmt.setString(1, "Deposit");
            stmt.setString(2, "deposit");
            stmt.setInt(3, 0);
            stmt.setString(4, "Account deposit");
            stmt.setString(5, "deposit");
            stmt.addBatch();

            stmt.setString(1, "Withdraw");
            stmt.setString(2, "withdraw");
            stmt.setInt(3, 0);
            stmt.setString(4, "Account withdraw");
            stmt.setString(5, "withdraw");
            stmt.addBatch();

            stmt.setString(1, "Refund");
            stmt.setString(2, "refund");
            stmt.setInt(3, 0);
            stmt.setString(4, "Refund made on latest operation");
            stmt.setString(5, "deposit");
            stmt.addBatch();

            stmt.setString(1, "Upgrade to Premium");
            stmt.setString(2, "basic-to-premium");
            stmt.setInt(3, 0);
            stmt.setString(4, "From a basic person to a premium person");
            stmt.setString(5, "upgrade");
            stmt.addBatch();

            stmt.setString(1, "Upgrade to Enterprise");
            stmt.setString(2, "basic-to-enterprise");
            stmt.setInt(3, 0);
            stmt.setString(4, "From a basic person to an enterprise person");
            stmt.setString(5, "upgrade");
            stmt.addBatch();

            stmt.setString(1, "Upgrade to Enterprise");
            stmt.setString(2, "premium-to-enterprise");
            stmt.setInt(3, 0);
            stmt.setString(4, "From a premium person to an enterprise person");
            stmt.setString(5, "upgrade");
            stmt.addBatch();

            // Execute the batch
            stmt.executeBatch();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the statement and connection
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

