package com.example.banksystem.exception;

import com.example.banksystem.model.Card;
import com.example.banksystem.model.Holder;
import com.example.banksystem.model.Movement;

/**
 * @exception NoFundsException Questa eccezione consente di impostare delle limitazioni in base ai tipi di contratti
 */

public class NoFundsException extends Exception {
    public NoFundsException() {
        super("An error occurred");
    }

    public String toString() {
        return getMessage() + ": funds exceeded!!";
    }

    /**
     * Controlla se il correntista può effettuare il prelievo o l'acquisto secondo alcune regole specifiche del sistema:
     * non è possibile scendere al di sotto di una determinata soglia sulla tipologia di pagamento specificata.
     * Una carta Bancomat non può andare sotto lo zero.
     * Una persona con contratto Basic e carta di credito non può scendere sotto i -10€.
     * Una persona con contratto Premium e carta di credito non può scendere sotto i -100€.
     * Una persona con contratto Enterprise e carta di credito non può scendere sotto i -1000€.
     * @param holder correntista dal quale si vuole leggere la tipologia di contratto
     * @param card carta sul quale si vogliono effettuare controlli
     * @param movement movimento che si sta per aggiungere
     * @throws NoFundsException
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
