package com.example.banksystem;

public class NoFundsException extends Exception {
    public NoFundsException() {
        super("An error occurred");
    }

    public String toString() {
        return getMessage() + ": no sufficient funds!";
    }
}
