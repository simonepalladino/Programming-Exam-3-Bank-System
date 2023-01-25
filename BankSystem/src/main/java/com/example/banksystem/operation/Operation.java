package com.example.banksystem.operation;

import java.util.List;

/**
 * Implementazione del Pattern DAO
 * @param <T> modello che si vuole utilizzare
 */
public interface Operation<T> {
    T get(String toFind);
    List<T> getAll();
    void add(T t);
    void delete(T t);
}
