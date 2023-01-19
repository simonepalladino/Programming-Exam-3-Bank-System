package com.example.banksystem.observer;


import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.operation.CardOperation;

import java.util.ArrayList;
import java.util.List;

public class CardObserver {
    private static final CardObserver instance = new CardObserver();
    private static List<Holder> observers = new ArrayList<>();
    private static CardOperation cardOperation = null;

    //Singleton, previene l'istanziazione da parte di altre classi
    private CardObserver() {}

    public static CardObserver getInstance() {
        return instance;
    }

    public void setCardOperation(CardOperation cardOperation) {
        if (CardObserver.cardOperation != null)
            observers.clear();

        CardObserver.cardOperation = cardOperation;
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

    //Per fare le cose senza notificare l'Observer
    public CardOperation getCardOperation() {
        return cardOperation;
    }
}