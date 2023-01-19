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
    private  List<Movement> movements = new ArrayList<>();

    public MovementOperation(){
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs;


            int Id_mov;
            String Mov_type;
            LocalDate Mov_date;
            String Number_card;

            rs = stmt.executeQuery("SELECT * FROM movements");
            while (rs.next()) {

                Id_mov = rs.getInt("Id_mov");
                Mov_type = rs.getString("Mov_type");
                Number_card = rs.getString("Number_card");
                Mov_date = rs.getDate("Mov_date").toLocalDate();

                movements.add(new Movement(String.valueOf(Id_mov), Mov_type, Mov_date, Number_card));
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
    public Optional get(String toFind) {
        for (Movement move : movements) {
            if (String.valueOf(move.getId_mov()).equals(toFind))
                return Optional.ofNullable(movements);
        }

        return Optional.empty();
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
            pstmt.setString(4, m.getNumber_card());
            pstmt.executeUpdate();

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
    public void update(Movement m, String[] params) {

    }

    @Override
    public void delete(Movement m) {
        try {
            con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM movements WHERE Id_Mov=(?)");

            stmt.setInt(1, Integer.parseInt(m.getId_mov()));
            stmt.execute();

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

