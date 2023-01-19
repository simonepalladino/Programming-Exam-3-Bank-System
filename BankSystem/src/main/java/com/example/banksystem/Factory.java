package com.example.banksystem;

import com.example.banksystem.iterator.CardIterator;
import com.example.banksystem.iterator.HolderIterator;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.iterator.MovementIterator;
import com.example.banksystem.operation.CardOperation;
import com.example.banksystem.operation.HolderOperation;
import com.example.banksystem.operation.MovementOperation;
import com.example.banksystem.operation.Operation;

public class Factory {
    public enum OperationType {
        HOLDER,
        CARD,
        MOVEMENTS
    }

    public Operation getOperation(OperationType type) {
        switch (type) {
            case HOLDER:
                return new HolderOperation();
            case CARD:
                return new CardOperation();
            case MOVEMENTS:
                return new MovementOperation();
        }
        return null;
    }

    public Iterator getIterator(OperationType type) {
        switch (type) {
            case HOLDER:
                return new HolderIterator(new HolderOperation().getAll());
            case CARD:
                return new CardIterator(new CardOperation().getAll());
            case MOVEMENTS:
                return new MovementIterator(new MovementOperation().getAll());
        }
        return null;
    }
}
