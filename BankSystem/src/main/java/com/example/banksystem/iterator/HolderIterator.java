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
     * @return
     */
    //This method creates and returns an instance of the MovementsIterator class
    public Iterator<Holder> iterator() {
        return new HolderIterator(holders);
    }

    /**
     * @param holders
     */
    public HolderIterator(List<Holder> holders) {
        this.holders = holders;
    }

    /**
     * @return
     */
    @Override
    public boolean hasNext() {
        return index < holders.size();
    }

    /**
     * @return
     */
    @Override
    public Holder next() {
        return holders.get(index++);
    }
}