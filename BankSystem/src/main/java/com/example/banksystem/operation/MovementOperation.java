package com.example.banksystem.operation;

import com.example.banksystem.Actions;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.CardObserver;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class MovementOperation implements Operation<Movement> {
    private final String url = "jdbc:sqlite:banksystem.sqlite";
    private Connection con;
    private List<Movement> movements = new ArrayList<>();
    private int lastMovementID = 0;

    /**
     * Inizializza la lista di tutti i movimenti
     */
    public MovementOperation() {
        initialization("SELECT * FROM movements ORDER BY Id_mov");
    }

    /**
     * Inizializza la lista di movimenti con opzioni
     * @param toFind stringa che indica il parametro da voler cercare
     * @param ifCard se impostato a vero inizializza la ricerca mostrando tutti i movimenti relativi al parametro "card_number_FK";
     *               se impostato a falso inizializza la ricerca mostrando tutti i movimenti relativi al CF dell'utente immesso
     */
    public MovementOperation(String toFind, boolean ifCard) {
        //Se inizializzato con parametro di ricerca, allora non è possibile conoscere l'ID auto increment
        //siccome la relazione della tabella nel db è 1:N, non M:N
        lastMovementID = -1;

        //ifCard indica se si sta cercando i movimenti relativi a una carta specifica
        // oppure tutti i movimenti di un utente
        if (ifCard)
            initialization("SELECT * FROM movements WHERE card_number_FK = '" + toFind + "' ORDER BY Id_mov");
        else
            initialization("SELECT * FROM movements JOIN Cards ON movements.card_number_FK = Cards.card_number WHERE Cards.CF_FK = '" + toFind + "' ORDER BY Id_mov");
    }


    /**
     * Aggiunge alla lista di movimenti "movements" i movimenti trovati
     * @param query query che sarà effettuata
     */
    private void initialization(String query) {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs;
            int id_mov = 0;
            String product_id;
            LocalDate mov_date;
            String card_number;
            double price;
            int count = 0;

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                id_mov = rs.getInt("Id_mov");
                product_id = rs.getString("product_id");
                card_number = rs.getString("card_number_FK");
                mov_date = rs.getDate("Mov_date").toLocalDate();
                price = rs.getDouble("price");

                if (query.contains("WHERE")) {
                    //System.out.println(" @+ Movimento assegnato alla carta: " + card_number);
                } else {
                    System.out.println("@ Movimento caricato: " +  id_mov + " " + card_number + " " + product_id + " " + mov_date + " " + price);
                }

                movements.add(new Movement(id_mov, product_id, mov_date, card_number, price));
            }

            if (!query.contains("WHERE"))
                lastMovementID = id_mov;
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
     * Ricerca all'interno della lista di movimenti "movements" se è presente il movimento che si sta cercando
     * @param toFind id del movimento che si vuole cercare
     * @return il movimento trovato, altrimenti null
     */
    @Override
    public Movement get(String toFind) {
        for (Movement move : movements) {
            if (String.valueOf(move.getId_mov()).equals(toFind))
                return move;
        }

        return null;
    }

    /**
     * Ricerca la chiave primaria id movimento nella lista di movimenti (movements)
     * @param toFind id del movimento che si vuole cercare
     * @return il movimento trovato, altrimenti null
     */
    public Movement get(int toFind) {
        for (Movement move : movements) {
            if (move.getId_mov() == toFind)
                return move;
        }

        return null;
    }


    /**
     * @return la lista dei movimenti
     */
    @Override
    public List getAll() {
        return movements;
    }


    /**
     * Aggiunge alla lista dei movimenti "movements" e al database il movimento specificato
     * @param m movimento che si vuole aggiungere
     */
    @Override
    public void add(Movement m) {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            con = DriverManager.getConnection("jdbc:sqlite:banksystem.sqlite");

            Statement stmt;
            PreparedStatement pstmt;
            stmt = con.createStatement();




            if (m.getId_mov() == -1 && lastMovementID > -1) {
                pstmt = con.prepareStatement("INSERT INTO movements (Id_mov, product_id, Mov_date, card_number_FK, price) VALUES (?,?,?,?,?)");

                m.setId_mov(++lastMovementID);
                pstmt.setInt(1, m.getId_mov());
                pstmt.setString(2, m.getProduct_id());
                pstmt.setDate(3, Date.valueOf(m.getMov_date()));
                pstmt.setString(4, m.getCard_number_FK());
                pstmt.setDouble(5, m.getPrice());
            } else {
                pstmt = con.prepareStatement("INSERT INTO movements (product_id, Mov_date, card_number_FK, price) VALUES (?,?,?,?)");
                pstmt.setString(1, m.getProduct_id());
                pstmt.setDate(2, Date.valueOf(m.getMov_date()));
                pstmt.setString(3, m.getCard_number_FK());
                pstmt.setDouble(4, m.getPrice());
            }

            pstmt.executeUpdate();

            System.out.println("@+ Movimento aggiunto: " +  m.getId_mov() + " " + m.getCard_number_FK() + " " + m.getProduct_id() + " " + m.getMov_date() + " " + m.getPrice());
            movements.add(m);

            //Modifica il saldo della carta associata
            if (m.getPrice() >= 0)
                CardObserver.getInstance().deposit(Actions.getInstance().cardOperation.get(m.getCard_number_FK()), m.getPrice());
            else
                CardObserver.getInstance().withdraw(Actions.getInstance().cardOperation.get(m.getCard_number_FK()), -m.getPrice());
        } catch (SQLException e) {
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
     * Elimina della lista dei movimenti e dal database il movimento specificato
     * @param m movimento che si vuole eliminare
     */
    @Override
    public void delete(Movement m) {
        try {
            Properties properties = new Properties();
            properties.setProperty("foreign_keys", "true");

            con = DriverManager.getConnection(url, properties);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM movements WHERE Id_Mov=(?)");

            stmt.setInt(1, m.getId_mov());
            stmt.execute();

            System.out.println("@- Movimento eliminato: " +  m.getId_mov() + " " + m.getCard_number_FK() + " " + m.getProduct_id() + " " + m.getMov_date());

            if (lastMovementID == m.getId_mov())
                lastMovementID--;
            movements.remove(m);
        } catch (SQLException e) {
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
}

