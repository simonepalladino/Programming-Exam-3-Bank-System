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
     * @return crea e ritorna un'istanza della classe CardIterator
     */
    public Iterator<Card> iterator() {
        return new CardIterator(cards);
    }

    /**
     * Inizializza MovementIterator
     * @param cards lista di carte dell'iteratore
     */
    public CardIterator(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * @return restituisce vero se la carta successiva esiste
     */
    @Override
    public boolean hasNext() {
        return index < cards.size();
    }

    /**
     * Itera alla carta successiva
     * @return restituisce la carta successiva e aggiorna l'index
     */
    @Override
    public Card next() {
        return cards.get(index++);
    }
}