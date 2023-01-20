package com.example.banksystem.operation;

import com.example.banksystem.model.Movement;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovementOperation implements Operation<Movement> {
    private final String url = "jdbc:sqlite:banksystem.sqlite";
    private Connection con;
    private List<Movement> movements = new ArrayList<>();

    public MovementOperation(){
        initialization("SELECT * FROM movements");
    }

    public MovementOperation(String card_number_fk){
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

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                id_mov = rs.getInt("Id_mov");
                mov_type = rs.getString("Mov_type");
                card_number = rs.getString("card_number_FK");
                mov_date = rs.getDate("Mov_date").toLocalDate();

                if (query.contains("WHERE"))
                    System.out.println(" @+ Movimento assegnato alla carta: " + card_number);
                else
                    System.out.println("@ Movimento caricato: " +  id_mov + " " + card_number + " " + mov_type + " " + mov_date);

                movements.add(new Movement(String.valueOf(id_mov), mov_type, mov_date, card_number));
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


            pstmt = con.prepareStatement("INSERT INTO movements (Id_mov, Mov_type, Mov_date, card_number_FK) VALUES (?,?,?,?)");

            pstmt.setString(1, m.getId_mov());
            pstmt.setString(2, m.getMov_type());
            pstmt.setDate(3, Date.valueOf(m.getMov_date()));
            pstmt.setString(4, m.getCard_number_FK());
            pstmt.executeUpdate();

            System.out.println("@ Movimento caricato: " +  m.getId_mov() + " " + m.getCard_number_FK() + " " + m.getMov_type() + " " + m.getMov_date());
            movements.add(m);
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
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM movements WHERE Id_Mov=(?)");

            stmt.setInt(1, Integer.parseInt(m.getId_mov()));
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

