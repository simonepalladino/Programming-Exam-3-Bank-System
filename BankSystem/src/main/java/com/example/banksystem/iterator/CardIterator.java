package com.example.banksystem.iterator;

import com.example.banksystem.model.Card;

import java.util.List;

/**
 * La classe CardIterator implementa l'interfaccia Iterator
 */
public class CardIterator implements Iterator<Card> {
    private List<Card> cards;
    private int index = 0;

    /**
     * @return
     */
    //This method creates and returns an instance of the MovementsIterator class
    public Iterator<Card> iterator() {
        return new CardIterator(cards);
    }

    /**
     * @param cards
     */
    public CardIterator(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * @return
     */
    @Override
    public boolean hasNext() {
        return index < cards.size();
    }

    /**
     * @return
     */
    @Override
    public Card next() {
        return cards.get(index++);
    }
}