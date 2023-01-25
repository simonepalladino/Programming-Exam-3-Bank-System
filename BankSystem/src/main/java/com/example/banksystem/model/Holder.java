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
     * @param username username del correntista
     * @param firstname nome del correntista
     * @param lastname cognome del correntista
     * @param cf cf del correntista
     * @param date_of_birth data di nascita del correntista
     * @param contract_type tipo del contratto del correntista
     * @param residence residenza del correntista
     * @param contract_cost costo del contratto del correntista
     * @param password password del correntista
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

    /**
     * @return restituisce l'username del correntista
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return restituisce il nome del correntista
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @return restituisce il cognome del correntista
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @return restituisce il cf del correntista
     */
    public String getCf() {
        return cf;
    }

    /**
     * @param cf imposta il cf del correntista
     */
    public void setCf(String cf) {
        this.cf = cf;
    }

    /**
     * @return restituisce la data di nascita del correntista
     */
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    /**
     * @return restituisce il tipo contratto del correntista
     */
    public String getContract_type() {
        return contract_type;
    }

    /**
     * @param contract_type imposta il tipo contratto del correntista
     */
    public void setContract_type(String contract_type) {
        this.contract_type = contract_type;
    }

    /**
     * @return restituisce la residenza del correntista
     */
    public String getResidence() {
        return residence;
    }

    /**
     * @return restituisce il costo del contratto del correntista
     */
    public int getContract_cost(){
        return contract_cost;
    }

    /**
     * @param contract_cost imposta il costo del contratto del correntista
     */
    public void setContract_cost(int contract_cost){
        this.contract_cost = contract_cost;
    }

    /**
     * @return restituisce una lista di carte di tutti i correntisti
     */
    public List getCards() {
        return cards.getAll();
    }

    /**
     * @return restituisce le carte
     */
    public CardOperation getCardOperation() {return cards;}

    /**
     * @return restituisce la password del correntista
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password imposta la password del correntista
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param cards imposta le carta al correntista
     */
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
