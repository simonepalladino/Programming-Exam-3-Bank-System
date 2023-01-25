package com.example.banksystem.observer;


import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.operation.CardOperation;

import java.util.ArrayList;
import java.util.List;

public class CardObserver {
    private static final CardObserver instance = new CardObserver();
    private List<Holder> observers = new ArrayList<>();
    private CardOperation cardOperation = null;

    /**
     * Inizializza la classe una sola volta secondo la dichiarazione iniziale,
     * prevenendo l'istanziazione da parte di altre classi (Singleton)
     */
    private CardObserver() {}

    /**
     * @return ritorna l'istanza CardObserver sul quale si vogliono effettuare le operazioni
     */
    public static CardObserver getInstance() {
        return instance;
    }

    /**
     * Imposta le operazioni da fare sulla lista di carte
     * @param cardOperation lista di operazioni sulle carte
     */
    public void setCardOperation(CardOperation cardOperation) {
        if (this.cardOperation != null)
            observers.clear();

        this.cardOperation = cardOperation;
    }

    /**
     * Aggiunge un observer alla lista di observers
     * @param holder correntista che si vuole osservare
     */
    public void addObserver(Holder holder) {
        observers.add(holder);
    }

    /**
     * Rimuove un observer dalla lista di observers
     * @param holder correntista che non si vuole più osservare
     */
    public void removeObserver(Holder holder) {
        observers.remove(holder);
    }

    /**
     * Rimuove più observer dalla lista di observers
     * @param holders correntisti che non si vogliono più osservare
     */
    public void removeObserver(List<Holder> holders) {
        for (Holder holder : holders) {
            if (holders.indexOf(holder) != -1) observers.remove(holder);
        }
    }

    /**
     * Notifica a tutti gli observer che è avvenuta una modifica, per cui è necessario aggiornare i parametri
     * @param cf CF del correntista che si vuole aggiornare
     */
    public void notifyObservers(String cf) {
        for (Holder holder : observers) {
            if (cf.equals(holder.getCf()))
                holder.update();
        }
    }

    /**
     * Aggiunge una carta nella base di dati e notifica agli osservatori della modifica
     * @param card carta che si vuole aggiungere
     * @see CardOperation
     */
    public void add(Card card) {
        cardOperation.add(card);
        notifyObservers(card.getCF_FK());
    }

    /**
     * Rimuove una carta nella base di dati e notifica gli osservatori della rimozione
     * @param card carta che si vuole eliminare
     * @see CardOperation
     */
    public void delete(Card card) {
        cardOperation.delete(card);
        notifyObservers(card.getCF_FK());
    }

    /**
     * Deposita il denaro specificato sulla carta passata per parametro
     * @param card carta sul quale si vuole depositare
     * @param money quantità di denaro da depositare
     */
    public void deposit(Card card, double money) {
        cardOperation.deposit(card, money);
        notifyObservers(card.getCF_FK());
    }

    /**
     * Preleva il saldo specificato sulla carta
     * @param card carta sul quale si vuole prelevare
     * @param money saldo che si vuole prelevare dalla carta specificata
     */
    public void withdraw(Card card, double money) {
        cardOperation.withdraw(card, money);
        notifyObservers(card.getCF_FK());
    }
}