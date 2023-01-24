package com.example.banksystem.exception;

import com.example.banksystem.Actions;
import com.example.banksystem.Factory;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.model.Product;

public class NoFundsException extends Exception {
    public NoFundsException() {
        super("An error occurred");
    }

    public String toString() {
        return getMessage() + ": funds exceeded!!";
    }

    /*
        Una carta Bancomat non può andare sotto lo zero
        Una persona con contratto Basic e carta di credito non può scendere sotto i -10€
        Una persona con contratto Basic e carta di credito non può scendere sotto i -100€
        Una persona con contratto Basic e carta di credito non può scendere sotto i -1000€
     */
    public static void checkWithdrawLimit(Holder holder, Card card, Movement movement) throws NoFundsException {
        if (card.getBalance() + movement.getPrice() < 0) {
            if (card.getCard_type().equals("Bancomat"))
                throw new NoFundsException();
            else if (card.getCard_type().equals("Credit Card")) {
                switch (holder.getContract_type()) {
                    case "Basic":
                        if (card.getBalance() + movement.getPrice() < 10)
                            throw new NoFundsException();
                    case "Premium":
                        if (card.getBalance() + movement.getPrice() < 100)
                            throw new NoFundsException();
                    case "Enterprise":
                        if (card.getBalance() + movement.getPrice() < 1000)
                            throw new NoFundsException();
                }
            }
        }
    }
}
