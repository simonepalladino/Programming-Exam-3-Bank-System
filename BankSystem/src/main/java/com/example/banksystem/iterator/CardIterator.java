package com.example.banksystem.iterator;

import com.example.banksystem.model.Card;

import java.util.List;

public class CardIterator implements Iterator<Card> {
    private List<Card> cards;
    private int index = 0;

    //This method creates and returns an instance of the MovementsIterator class
    public Iterator<Card> iterator() {
        return new CardIterator(cards);
    }

    public CardIterator(List<Card> cards) {
        this.cards = cards;
    }
    @Override
    public boolean hasNext() {
        return index < cards.size();
    }
    @Override
    public Card next() {
        return cards.get(index++);
    }
}