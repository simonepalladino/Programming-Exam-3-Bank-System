package com.example.banksystem.exception;

public class NoFundsException extends Exception {
    public NoFundsException() {
        super("An error occurred");
    }

    public String toString() {
        return getMessage() + ": funds exceeded!!";
    }

    public static void checkWithdrawLimit(String contract_type, String card_type, double money) {
        switch (contract_type) {
            case "Basic":
                if (money > 1000) {

                }
                break;
            case "Premium":
                break;
            case "Enterprise":
                break;
        }
    }
}
