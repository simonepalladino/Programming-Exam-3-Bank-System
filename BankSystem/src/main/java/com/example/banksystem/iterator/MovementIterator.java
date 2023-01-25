package com.example.banksystem.iterator;

import com.example.banksystem.model.Movement;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * la classe MovementIterator implementa l'interfaccia Iterator
 */
public class MovementIterator implements Iterator<Movement> {
    private List<Movement> movements;
    private int index = 0;
    private boolean reverse = false;

    /**
     * @return
     */
    //This method creates and returns an instance of the MovementsIterator class
    public Iterator<Movement> iterator() {
        return new MovementIterator(movements);
    }

    /**
     * @param movements
     */
    public MovementIterator(List<Movement> movements) {
        this.movements = movements;
    }

    /**
     * @param movements
     * @param reverse
     */
    public MovementIterator(List<Movement> movements, boolean reverse) {
        this.movements = movements;
        this.reverse = reverse;
        if (reverse) this.index = movements.size();
    }

    /**
     * @return
     */
    @Override
    public boolean hasNext() {
        if (reverse) {
            return index > 0;
        }

        return index < movements.size();
    }

    /**
     * @return
     */
    @Override
    public Movement next() {
        if (reverse) {
            if (index <= 0) {
                throw new NoSuchElementException("No previous movement");
            }

            return movements.get(--index);
        }

        return movements.get(index++);
    }
}