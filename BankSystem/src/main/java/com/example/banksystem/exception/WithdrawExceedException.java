package com.example.banksystem.exception;

import com.example.banksystem.Actions;
import com.example.banksystem.Factory;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.model.Product;

import java.lang.annotation.*;

/**
 * @exception WithdrawExceedException Questa eccezione consente d'impostare delle limitazioni sui prelievi
 */

public class WithdrawExceedException extends Exception {
    public WithdrawExceedException() {
        super("An error occurred");
    }

    public String toString() {
        return getMessage() + ": withdraw exceeded!!";
    }

    /**
     * Controlla se il correntista può effettuare il prelievo o l'acquisto secondo alcune regole specifiche del sistema.
     * Una persona con contratto Basic può spendere massimo 2000€ per carta al giorno ed effettuare massimo tre operazioni
     * Una persona con contratto Premium può spenderne massimo 10.000€ per carta al giorno ed effettuare massimo dieci operazioni
     * Una persona con contratto Enterprise può spendere 100.000€ per carta al giorno con un numero indefinito di operazioni
     * @param holder correntista dal quale si vuole leggere la tipologia di contratto
     * @param card carta sul quale si vogliono effettuare controlli
     * @param movement movimento che si sta per aggiungere
     * @throws NoFundsException
     */
    public static void checkWithdrawLimit(Holder holder, Card card, Movement movement) throws WithdrawExceedException {
        //Capire quanti acquisti ha fatto la carta nella giornata
        Iterator<Movement> iterator = Factory.getMovementCardIterator(card.getCard_number(), false);
        double operationCount = -movement.getPrice(), limitWithdraw = 0;
        int count = 1, countLimit = 0;

        while (iterator.hasNext()) {
            Movement movementTemp = iterator.next();
            Product productTemp = Actions.getInstance().productOperation.get(movementTemp.getProduct_id());

            if (movementTemp.getMov_date().toString().equals(movement.getMov_date().toString()) && !productTemp.getType().equals("deposit") && !productTemp.getType().equals("upgrade")) {
                operationCount += -movementTemp.getPrice();
                count++;
            }
        }

        switch (holder.getContract_type()) {
            case "Basic":
                limitWithdraw = 2000;
                countLimit = 3;
                break;
            case "Premium":
                limitWithdraw = 10000;
                countLimit = 10;
                break;
            case "Enterprise":
                limitWithdraw = 100000;
                countLimit = -1;
                break;
        }

        if (limitWithdraw > 0 && operationCount > limitWithdraw)
            throw new WithdrawExceedException();

        if (countLimit > 0 && count > countLimit)
            throw new WithdrawExceedException();
    }
}
