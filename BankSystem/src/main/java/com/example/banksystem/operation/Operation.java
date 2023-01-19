package com.example.banksystem.operation;

import java.util.List;
import java.util.Optional;

//DAO API
public interface Operation<T> {
    Optional<T> get(String toFind);
    List<T> getAll();
    void add(T t);
    void delete(T t);
}
