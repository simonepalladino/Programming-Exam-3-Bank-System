package com.example.banksystem.model;

import com.example.banksystem.observer.CardObserver;
import com.example.banksystem.observer.Observer;
import com.example.banksystem.operation.CardOperation;

import java.util.Date;
import java.util.List;

public class Holder implements Observer {
    private String username;
    private String firstname;
    private String lastname;
    private String cf;
    private Date date_of_birth;
    private String contract_type;
    private String residence;
    private int contract_cost;
    private String password;
    private CardOperation cards;


    /**
     * Inizializza un correntista con gli stessi valori delle colonne della tabella "HOLDERS" presente nel database.
     * @see com.example.banksystem.operation.HolderOperation
     */
    public Holder(String username, String firstname, String lastname, String cf, Date date_of_birth, String contract_type, String residence, int contract_cost, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cf = cf;
        this.date_of_birth = date_of_birth;
        this.contract_type = contract_type;
        this.residence = residence;
        this.username = username;
        this.contract_cost = contract_cost;
        this.password = password;

        //Aggiunge l'Holder alla lista di Observers
        CardObserver.getInstance().addObserver(this);

        //Aggiorna le carte associate al conto all'interno di un CardOperation
        cards = new CardOperation(cf);
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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

    public String getContract_type() {
        return contract_type;
    }

    public void setContract_type(String contract_type) {
        this.contract_type = contract_type;
    }

    public String getResidence() {
        return residence;
    }

    public int getContract_cost(){
        return contract_cost;
    }

    public void setContract_cost(int contract_cost){
        this.contract_cost = contract_cost;
    }

    public List getCards() {
        return cards.getAll();
    }

    public CardOperation getCardOperation() {return cards;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setCards(CardOperation cards) {
        this.cards = cards;
    }

    /**
     * Aggiorna la lista dei movimenti del correntista quando richiesto dall'Observer
     @see com.example.banksystem.observer.CardObserver
     */
    @Override
    public void update() {
        System.out.println("?! Aggiorno la lista delle carte di " + cf);
        cards = new CardOperation(cf);
    }
}
