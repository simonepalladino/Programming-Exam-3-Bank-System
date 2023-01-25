package com.example.banksystem.observer;


import com.example.banksystem.model.Card;
import com.example.banksystem.model.Movement;
import com.example.banksystem.operation.MovementOperation;

import java.util.ArrayList;
import java.util.List;

public class MovementObserver {
    private static final MovementObserver instance = new MovementObserver();
    private List<Card> observers = new ArrayList<>();
    private MovementOperation movementOperation = null;

    //Singleton, previene l'istanziazione da parte di altre classi
    private MovementObserver() {}

    public static MovementObserver getInstance() {
        return instance;
    }

    /**
     * Imposta le operazioni da fare sulla lista dei movimenti
     * @param movementOperation lista di operazioni sui movimenti
     */
    public void setMovementOperation(MovementOperation movementOperation) {
        if (this.movementOperation != null)
            observers.clear();

        this.movementOperation = movementOperation;
    }

    /**
     * Aggiunge un observer alla lista di observers
     * @param card carta che si vuole osservare
     */
    public void addObserver(Card card) {
        observers.add(card);
    }

    /**
     * Rimuove un observer dalla lista di observers
     * @param card carta che non si vuole più osservare
     */
    public void removeObserver(Card card) {
        observers.remove(card);
    }

    /**
     * Rimuove più observer dalla lista di observers
     * @param cards carte che non si vogliono più osservare
     */
    public void removeObserver(List<Card> cards) {
        for (Card card : cards) {
            if (cards.indexOf(card) != -1) observers.remove(card);
        }
    }

    /**
     * Notifica a tutti gli observer che è avvenuta una modifica, per cui è necessario aggiornare i parametri
     * @param card_number card_number della carta che si vuole aggiornare
     */
    public void notifyObservers(String card_number) {
        for (Card card : observers) {
            if (card_number.equals(card.getCard_number()))
                card.update();
        }
    }

    /**
     * Aggiunge un movimento nella base di dati e notifica agli osservatori della modifica
     * @param movement movimento che si vuole aggiungere
     */
    //Notifica l'Observer dopo l'aggiunta di una carta
    public void add(Movement movement) {
        movementOperation.add(movement);
        notifyObservers(movement.getCard_number_FK());
    }

    /**
     * Rimuove un movimento nella base di dati e notifica agli osservatori della modifica
     * @param movement movimento che si vuole eliminare
     */
    public void delete(Movement movement) {
        movementOperation.delete(movement);
        notifyObservers(movement.getCard_number_FK());
    }
}