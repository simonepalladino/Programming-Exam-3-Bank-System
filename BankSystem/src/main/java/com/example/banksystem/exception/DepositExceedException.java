package com.example.banksystem.exception;

import com.example.banksystem.Actions;
import com.example.banksystem.Factory;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;
import com.example.banksystem.model.Product;

/**
 * @exception DepositExceedException Questa eccezione permette di impostare delle limitazioni sui depositi.
 */
public class DepositExceedException extends Exception {
    public DepositExceedException() {
        super("An error occurred");
    }

    public String toString() {
        return getMessage() + ": no sufficient funds!";
    }

    /**
     * Controlla se il correntista può effettuare il deposito secondo alcune regole specifiche del sistema.
     * Una persona con contratto Basic può depositare massimo 5000€ al giorno.
     * Una persona con contratto Premium può depositarne massimo 50.000€ al giorno.
     * Una persona con contratto Enterprise può depositare 1.000.000€ al giorno.
     * @param holder correntista sul quale si vogliono effettuare controlli
     * @param movement movimento che si sta per aggiungere
     * @throws DepositExceedException
    */
    public static void checkDepositLimit(Holder holder, Movement movement) throws DepositExceedException {
        //Capire quanti acquisti ha fatto la carta nella giornata
        Iterator<Movement> iterator = Factory.getMovementUserIterator(holder.getCf(), false);
        double operationCount = movement.getPrice(), limitDeposit = 0;

        while (iterator.hasNext()) {
            Movement movementTemp = iterator.next();
            Product productTemp = Actions.getInstance().productOperation.get(movementTemp.getProduct_id());

            if (movementTemp.getMov_date().toString().equals(movement.getMov_date().toString()) && productTemp.getType().equals("deposit")) {
                operationCount += movementTemp.getPrice();
            }
        }

        switch (holder.getContract_type()) {
            case "Basic":
                limitDeposit = 5000;
                break;
            case "Premium":
                limitDeposit = 50000;
                break;
            case "Enterprise":
                limitDeposit = 1000000;
                break;
        }

        if (limitDeposit > 0 && operationCount > limitDeposit)
            throw new DepositExceedException();
    }


}
