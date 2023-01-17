package com.example.banksystem.holders;

import java.util.Date;

public class Holder {
    private String username;
    private String firstname;
    private String lastname;
    private String cf;
    private Date date_of_birth;
    private String contract_type;
    private String residence;

    public Holder(String username, String firstname, String lastname, String cf, Date date_of_birth, String contract_type, String residence) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cf = cf;
        this.date_of_birth = date_of_birth;
        this.contract_type = contract_type;
        this.residence = residence;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
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
