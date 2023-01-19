package com.example.banksystem.model;

import java.time.LocalDate;

public class Movement {
    private String Id_mov;
    private String Mov_type;
    private LocalDate Mov_date;
    private String Number_card;

    public Movement(String Id_mov, String Mov_type, LocalDate Mov_date, String Number_card){
        this.Id_mov = Id_mov;
        this.Mov_type = Mov_type;
        this.Mov_date = Mov_date;
        this.Number_card = Number_card;
    }

    public void setId_mov(String Id_mov){
        this.Id_mov = Id_mov;
    }

    public void setMov_type(String Move_type){
        this.Mov_type = Move_type;
    }

    public void setMov_date (LocalDate Mov_date){
        this.Mov_date = Mov_date;
    }

    public void setNumber_card(String Number_card){
        this.Number_card = Number_card;
    }

    public String getId_mov(){
        return this.Id_mov;
    }

    public String getMov_type(){
        return this.Mov_type;
    }

    public LocalDate getMov_date(){
        return this.Mov_date;
    }

    public String getNumber_card(){
        return this.Number_card;
    }
}
