package com.example.banksystem.operation;

import com.example.banksystem.Actions;
import com.example.banksystem.LoginServlet;
import com.example.banksystem.model.Movement;
import com.example.banksystem.observer.CardObserver;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class MovementOperation implements Operation<Movement> {
    private final String url = "jdbc:sqlite:banksystem.sqlite";
    private Connection con;
    private List<Movement> movements = new ArrayList<>();
    private int lastMovementID = 0;

    public MovementOperation() {
        initialization("SELECT * FROM movements");
    }

    public MovementOperation(String card_number_fk) {
        //Se inizializzato con parametro di ricerca, allora non è possibile conoscere l'ID auto increment
        //siccome la relazione della tabella nel db è 1:N, non M:N
        lastMovementID = -1;
        initialization("SELECT * FROM movements WHERE card_number_FK = '" + card_number_fk + "'");
    }

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
            int id_mov;
            String mov_type;
            LocalDate mov_date;
            String card_number;
            double price;
            int count = 0;

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                id_mov = rs.getInt("Id_mov");
                mov_type = rs.getString("Mov_type");
                card_number = rs.getString("card_number_FK");
                mov_date = rs.getDate("Mov_date").toLocalDate();
                price = rs.getDouble("price");

                if (query.contains("WHERE")) {
                    System.out.println(" @+ Movimento assegnato alla carta: " + card_number);
                } else {
                    System.out.println("@ Movimento caricato: " +  id_mov + " " + card_number + " " + mov_type + " " + mov_date + " " + price);
                    lastMovementID++;
                }

                movements.add(new Movement(id_mov, mov_type, mov_date, card_number, price));
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


    @Override
    public Movement get(String toFind) {
        for (Movement move : movements) {
            if (String.valueOf(move.getId_mov()).equals(toFind))
                return move;
        }

        return null;
    }


    @Override
    public List getAll() {
        return movements;
    }

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


            pstmt = con.prepareStatement("INSERT INTO movements (Mov_type, Mov_date, card_number_FK, price) VALUES (?,?,?,?)");

            pstmt.setString(1, m.getMov_type());
            pstmt.setDate(2, Date.valueOf(m.getMov_date()));
            pstmt.setString(3, m.getCard_number_FK());
            pstmt.setDouble(4, m.getPrice());
            pstmt.executeUpdate();

            if (m.getId_mov() == -1 && lastMovementID > -1)
                m.setId_mov(lastMovementID);

            System.out.println("@+ Movimento aggiunto: " +  m.getId_mov() + " " + m.getCard_number_FK() + " " + m.getMov_type() + " " + m.getMov_date() + " " + m.getPrice());
            movements.add(m);

            //Codice per aggiungere l'operazione alla carta
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


    @Override
    public void delete(Movement m) {
        try {
            Properties properties = new Properties();
            properties.setProperty("foreign_keys", "true");

            con = DriverManager.getConnection(url, properties);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM movements WHERE Id_Mov=(?)");

            stmt.setInt(1, m.getId_mov());
            stmt.execute();

            System.out.println("@- Movimento eliminato: " +  m.getId_mov() + " " + m.getCard_number_FK() + " " + m.getMov_type() + " " + m.getMov_date());
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

