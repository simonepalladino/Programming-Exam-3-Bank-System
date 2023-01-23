package com.example.banksystem;

import com.example.banksystem.iterator.CardIterator;
import com.example.banksystem.iterator.HolderIterator;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.iterator.MovementIterator;
import com.example.banksystem.model.Product;
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

    public static Operation getOperation(OperationType type) {
        switch (type) {
            case HOLDER:
                return new HolderOperation();
            case CARD:
                return new CardOperation();
            case MOVEMENT:
                return new MovementOperation();
        }

        return null;
    }

    public static Operation getOperation(OperationType type, String toFind) {
        switch (type) {
            case HOLDER:
                return new HolderOperation(toFind);
            case CARD:
                return new CardOperation(toFind);
            case MOVEMENT:
                return new MovementOperation(toFind, true);
        }

        return null;
    }

    public static Iterator getIterator(OperationType type) {
        switch (type) {
            case HOLDER:
                return new HolderIterator(new HolderOperation().getAll());
            case CARD:
                return new CardIterator(new CardOperation().getAll());
            case MOVEMENT:
                return new MovementIterator(new MovementOperation().getAll());
        }
        return null;
    }

    public static Iterator getIterator(OperationType type, String toFind) {
        switch (type) {
            case HOLDER:
                return new HolderIterator(new HolderOperation(toFind).getAll());
            case CARD:
                return new CardIterator(new CardOperation(toFind).getAll());
            case MOVEMENT:
                return new MovementIterator(new MovementOperation(toFind, true).getAll());
        }
        return null;
    }

    public static Operation getMovementUserOperation(String toFind) {
        return new MovementOperation(toFind, false);
    }

    public static Iterator getMovementUserIterator(String toFind, boolean reverse) {
        return new MovementIterator(new MovementOperation(toFind, false).getAll(), reverse);
    }
}
