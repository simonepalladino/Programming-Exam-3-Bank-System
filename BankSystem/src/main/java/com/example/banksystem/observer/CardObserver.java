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

    //Singleton, previene l'istanziazione da parte di altre classi
    private CardObserver() {}

    public static CardObserver getInstance() {
        return instance;
    }

    public void setCardOperation(CardOperation cardOperation) {
        if (this.cardOperation != null)
            observers.clear();

        this.cardOperation = cardOperation;
    }

    public void addObserver(Holder holder) {
        observers.add(holder);
    }

    public void removeObserver(Holder holder) {
        observers.remove(holder);
    }

    public void notifyObservers(String cf) {
        for (Holder holder : observers) {
            if (cf.equals(holder.getCf()))
                holder.update();
        }
    }

    //Notifica l'Observer dopo l'aggiunta di una carta
    public void add(Card card) {
        cardOperation.add(card);
        notifyObservers(card.getCF_FK());
    }

    public void delete(Card card) {
        cardOperation.delete(card);
        notifyObservers(card.getCF_FK());
    }

    public void deposit(Card card, double money) {
        cardOperation.deposit(card, money);
        notifyObservers(card.getCF_FK());
    }

    public void withdraw(Card card, double money) {
        cardOperation.withdraw(card, money);
        notifyObservers(card.getCF_FK());
    }

    //Per fare le cose senza notificare l'Observer
    public CardOperation getCardOperation() {
        return cardOperation;
    }
}