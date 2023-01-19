package com.example.banksystem.iterator;

public interface Iterator<T> {
    boolean hasNext(String key);
    T next(String key);
}
