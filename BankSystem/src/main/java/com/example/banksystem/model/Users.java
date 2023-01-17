package com.example.banksystem.model;

import com.example.banksystem.Dao.UsersDao;

import java.util.Date;

public class Users {
    private String cf;
    private String name;
    private String surname;
    private Date date_of_birth;
    private String contract_type;
    private String residence;
    private static Users usersIstance;
    private UsersDao usersDao;

    public Users(String cf, String name, Date date_of_birth, String contract_type, String residence){
        this.cf = cf;
        this.name = name;
        this.contract_type = contract_type;
        this.residence = residence;
        this.date_of_birth = date_of_birth;

        usersDao.Insert(usersIstance);
    }


    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getContract_type() {
        return contract_type;
    }

    public void setContract_type(String contract_type) {
        this.contract_type = contract_type;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
