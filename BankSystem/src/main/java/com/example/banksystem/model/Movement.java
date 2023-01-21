package com.example.banksystem.model;

import java.time.LocalDate;

public class Movement {
    private int id_mov;
    private String mov_type;
    private LocalDate mov_date;
    private String card_number_FK;
    private double price;


    public Movement(int Id_mov, String Mov_type, LocalDate Mov_date, String card_number_FK, double price) {
        this.id_mov = Id_mov;
        this.mov_type = Mov_type;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;
        this.price = price;
    }

    public Movement(String Mov_type, LocalDate Mov_date, String card_number_FK, double price) {
        this.id_mov = -1;
        this.mov_type = Mov_type;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;
        this.price = price;
    }

    public void setId_mov(int Id_mov){
        this.id_mov = Id_mov;
    }

    public void setMov_type(String Move_type){
        this.mov_type = Move_type;
    }

    public void setMov_date (LocalDate Mov_date){
        this.mov_date = Mov_date;
    }

    public void setCard_number_FK(String Number_card){
        this.card_number_FK = Number_card;
    }

    public int getId_mov(){
        return this.id_mov;
    }

    public String getMov_type(){
        return this.mov_type;
    }

    public LocalDate getMov_date(){
        return this.mov_date;
    }

    public String getCard_number_FK(){
        return this.card_number_FK;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
