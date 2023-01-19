package com.example.banksystem.iterator;

import com.example.banksystem.model.Movement;

import java.util.List;

public class MovementIterator implements Iterator<Movement> {
    private List<Movement> movements;
    private int index = 0;

    //This method creates and returns an instance of the MovementsIterator class
    public Iterator<Movement> iterator() {
        return new MovementIterator(movements);
    }

    public MovementIterator(List<Movement> movements) {
        this.movements = movements;
    }
    @Override
    public boolean hasNext() {
        return index < movements.size();
    }
    @Override
    public Movement next() {
        return movements.get(index++);
    }
}