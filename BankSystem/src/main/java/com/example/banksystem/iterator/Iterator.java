package com.example.banksystem.iterator;

/**
 * Implementazione del pattern Iterator
 * @param <T> modello che si vuole utilizzare
 */
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
