package com.example.banksystem.operation;

import java.util.List;

//DAO API
public interface Operation<T> {
    T get(String toFind);
    List<T> getAll();
    void add(T t);
    void delete(T t);
}
