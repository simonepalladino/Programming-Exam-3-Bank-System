package com.example.banksystem.iterator;

import com.example.banksystem.model.Holder;

import java.util.List;

public class HolderIterator implements Iterator<Holder> {
    private List<Holder> holders;
    private int index = 0;

    //This method creates and returns an instance of the MovementsIterator class
    public Iterator<Holder> iterator() {
        return new HolderIterator(holders);
    }

    public HolderIterator(List<Holder> holders) {
        this.holders = holders;
    }
    @Override
    public boolean hasNext(String key) {
        return index < holders.size();
    }
    @Override
    public Holder next(String key) {
        return holders.get(index++);
    }
}