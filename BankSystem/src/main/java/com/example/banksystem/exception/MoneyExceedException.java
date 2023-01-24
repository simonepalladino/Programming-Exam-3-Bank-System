package com.example.banksystem.exception;

public class MoneyExceedException extends Exception {
    public MoneyExceedException() {
        super("An error occurred");
    }

    public String toString() {
        return getMessage() + ": no sufficient funds!";
    }

    public static void checkDepositLimit(String contract_type, String card_type, double money) {
        switch (contract_type) {
            case "Basic":
                break;
            case "Premium":
                break;
            case "Enterprise":
                break;
        }
    }
}
