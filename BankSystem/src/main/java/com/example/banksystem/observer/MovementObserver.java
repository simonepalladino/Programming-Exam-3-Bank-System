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

    public void setMovementOperation(MovementOperation movementOperation) {
        if (this.movementOperation != null)
            observers.clear();

        this.movementOperation = movementOperation;
    }

    public void addObserver(Card card) {
        observers.add(card);
    }

    public void removeObserver(Card card) {
        observers.remove(card);
    }

    public void notifyObservers(String card_number) {
        for (Card card : observers) {
            if (card_number.equals(card.getCard_number()))
                card.update();
        }
    }

    //Notifica l'Observer dopo l'aggiunta di una carta
    public void add(Movement movement) {
        movementOperation.add(movement);
        notifyObservers(movement.getCard_number_FK());
    }

    public void delete(Movement movement) {
        movementOperation.delete(movement);
        notifyObservers(movement.getCard_number_FK());
    }

    //Per fare le cose senza notificare l'Observer
    public MovementOperation getCardOperation() {
        return movementOperation;
    }
}