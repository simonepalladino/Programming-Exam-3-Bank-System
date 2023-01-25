package com.example.banksystem.iterator;

import com.example.banksystem.model.Holder;

import java.util.List;

/**
 * La classe HolderIterator implementa l'interfaccia Iterator
 */
public class HolderIterator implements Iterator<Holder> {
    private List<Holder> holders;
    private int index = 0;

    /**
     * @return crea e ritorna un'istanza della classe HolderIterator
     */
    public Iterator<Holder> iterator() {
        return new HolderIterator(holders);
    }

    /**
     * @param holders imposta la lista di correntisti dell'iteratore
     */
    public HolderIterator(List<Holder> holders) {
        this.holders = holders;
    }

    /**
     * Controlla se esiste il prossimo correntista
     * @return restituisce vero se il prossimo correntista esiste
     */
    @Override
    public boolean hasNext() {
        return index < holders.size();
    }

    /**
     * Itera al prossimo correntista
     * @return restituisce il prossimo correntista e aggiorna l'index
     */
    @Override
    public Holder next() {
        return holders.get(index++);
    }
}