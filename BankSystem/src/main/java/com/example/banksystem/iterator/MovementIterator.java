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
     * @return crea e ritorna un'istanza della classe MovementsIterator
     */
    public Iterator<Movement> iterator() {
        return new MovementIterator(movements);
    }

    /**
     * @param movements imposta la lista di movimenti dell'iteratore
     */
    public MovementIterator(List<Movement> movements) {
        this.movements = movements;
    }

    /**
     * Inizializza MovementIterator
     * @param movements lista di movimenti dell'iteratore
     * @param reverse se true l'ordine di iterazione sarà inverso (dall'ultimo al primo)
     */
    public MovementIterator(List<Movement> movements, boolean reverse) {
        this.movements = movements;
        this.reverse = reverse;
        if (reverse) this.index = movements.size();
    }

    /**
     * @return restituisce vero se il prossimo (o precedente) movimento esiste
     */
    @Override
    public boolean hasNext() {
        if (reverse) {
            return index > 0;
        }

        return index < movements.size();
    }

    /**
     * Itera al prossimo (o al precedente) movimento
     * @return restituisce il prossimo (o precedente) movimento e aggiorna l'index
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