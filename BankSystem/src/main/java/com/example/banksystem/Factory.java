package com.example.banksystem;

import com.example.banksystem.iterator.CardIterator;
import com.example.banksystem.iterator.HolderIterator;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.iterator.MovementIterator;
import com.example.banksystem.observer.CardObserver;
import com.example.banksystem.observer.MovementObserver;
import com.example.banksystem.operation.*;

public class Factory {
    public enum OperationType {
        HOLDER,
        CARD,
        MOVEMENT
    }

    public static Operation getNewOperation(OperationType type) {
        switch (type) {
            case HOLDER:
                return new HolderOperation();
            case CARD:
                CardOperation cardOperation = new CardOperation();
                //Ogni volta che si inizializza senza parametro, aggiorna l'Observer Singleton
                CardObserver.getInstance().setCardOperation(cardOperation);
                return cardOperation;
            case MOVEMENT:
                MovementOperation movementOperation = new MovementOperation();
                //Ogni volta che si inizializza senza parametro, aggiorna l'Observer Singleton
                MovementObserver.getInstance().setMovementOperation(movementOperation);
                return movementOperation;
        }

        return null;
    }

    public static Iterator getIterator(OperationType type) {
        switch (type) {
            case HOLDER:
                HolderOperation tempHolder = new HolderOperation();

                //Rimuove dalla lista di Observers per evitare inutili aggiornamenti
                CardObserver.getInstance().removeObserver(tempHolder.getAll());

                return new HolderIterator(tempHolder.getAll());
            case CARD:
                CardOperation tempCard = new CardOperation();

                //Rimuove dalla lista di Observers per evitare inutili aggiornamenti
                MovementObserver.getInstance().removeObserver(tempCard.getAll());

                return new CardIterator(tempCard.getAll());
            case MOVEMENT:
                return new MovementIterator(new MovementOperation().getAll());
        }
        return null;
    }

    public static Iterator getIterator(OperationType type, String toFind) {
        switch (type) {
            case HOLDER:
                HolderOperation tempHolder = new HolderOperation(toFind);

                //Rimuove dalla lista di Observers per evitare inutili aggiornamenti
                CardObserver.getInstance().removeObserver(tempHolder.getAll());

                return new HolderIterator(tempHolder.getAll());
            case CARD:
                CardOperation tempCard = new CardOperation(toFind);

                //Rimuove dalla lista di Observers per evitare inutili aggiornamenti
                MovementObserver.getInstance().removeObserver(tempCard.getAll());

                return new CardIterator(tempCard.getAll());
            case MOVEMENT:
                return new MovementIterator(new MovementOperation(toFind, true).getAll());
        }
        return null;
    }

    public static Iterator getMovementCardIterator(String toFind, boolean reverse) {
        return new MovementIterator(new MovementOperation(toFind, true).getAll(), reverse);
    }

    public static Iterator getMovementUserIterator(String toFind, boolean reverse) {
        return new MovementIterator(new MovementOperation(toFind, false).getAll(), reverse);
    }
}
