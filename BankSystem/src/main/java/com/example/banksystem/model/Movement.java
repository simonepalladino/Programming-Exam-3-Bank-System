package com.example.banksystem.model;

import java.time.LocalDate;

public class Movement {
    private String id_mov;
    private String mov_type;
    private LocalDate mov_date;
    private String card_number_FK;

    public Movement(String Id_mov, String Mov_type, LocalDate Mov_date, String card_number_FK){
        this.id_mov = Id_mov;
        this.mov_type = Mov_type;
        this.mov_date = Mov_date;
        this.card_number_FK = card_number_FK;
    }

    public void setId_mov(String Id_mov){
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

    public String getId_mov(){
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
}
