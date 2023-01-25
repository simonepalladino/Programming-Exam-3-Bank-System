package com.example.banksystem;

import com.example.banksystem.iterator.CardIterator;
import com.example.banksystem.iterator.HolderIterator;
import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.iterator.MovementIterator;
import com.example.banksystem.observer.CardObserver;
import com.example.banksystem.observer.MovementObserver;
import com.example.banksystem.operation.*;

public class Factory {
    /**
     * Tipi enumerativi utilizzati per specificare l'iterazione o l'operazione che si vuole effettuare
     */
    public enum OperationType {
        HOLDER,
        CARD,
        MOVEMENT
    }

    /**
     * Ritorna un nuovo oggetto definito dall'implementazione del pattern DAO (Operation) e imposta l'istanza dell'Observer a tale.
     * Siccome è possibile istanziare un solo Observer per volta (Singleton), allora viene resettato con il metodo "setCardOperation".
     * @param type tipo enumerativo Factory.OperationType
     * @return restituisce la nuova istanza di operazione definita
     */
    public static Operation getNewOperation(OperationType type) {
        switch (type) {
            case HOLDER:
                return new HolderOperation();
            case CARD:
                CardOperation cardOperation = new CardOperation();
                //Ogni volta che si inizializza senza parametro, aggiorna l'Observer Singleton
                CardObserver.getInstance().setCardOperation(cardOperation);
                return cardOperation;
            case MOVEMENT:
                MovementOperation movementOperation = new MovementOperation();
                //Ogni volta che si inizializza senza parametro, aggiorna l'Observer Singleton
                MovementObserver.getInstance().setMovementOperation(movementOperation);
                return movementOperation;
        }

        return null;
    }

    /**
     * Ottiene l'iteratore richiesto (Pattern Iterator)
     * @param type tipo enumerativo Factory.OperationType
     * @return restituisce l'istanza temporanea di iterazione
     */
    public static Iterator getIterator(OperationType type) {
        switch (type) {
            case HOLDER:
                HolderOperation tempHolder = new HolderOperation();

                //Rimuove dalla lista di Observers per evitare inutili aggiornamenti
                CardObserver.getInstance().removeObserver(tempHolder.getAll());

                return new HolderIterator(tempHolder.getAll());
            case CARD:
                CardOperation tempCard = new CardOperation();

                //Rimuove dalla lista di Observers per evitare inutili aggiornamenti
                MovementObserver.getInstance().removeObserver(tempCard.getAll());

                return new CardIterator(tempCard.getAll());
            case MOVEMENT:
                return new MovementIterator(new MovementOperation().getAll());
        }
        return null;
    }

    /**
     * Ottiene l'iteratore richiesto (Pattern Iterator)
     * @param type tipo enumerativo Factory.OperationType
     * @param toFind parametro da ricercare in caso di iterazione su movimento
     * @return restituisce l'istanza temporanea di iterazione
     */
    public static Iterator getIterator(OperationType type, String toFind) {
        switch (type) {
            case HOLDER:
                HolderOperation tempHolder = new HolderOperation(toFind);

                //Rimuove dalla lista di Observers per evitare inutili aggiornamenti
                CardObserver.getInstance().removeObserver(tempHolder.getAll());

                return new HolderIterator(tempHolder.getAll());
            case CARD:
                CardOperation tempCard = new CardOperation(toFind);

                //Rimuove dalla lista di Observers per evitare inutili aggiornamenti
                MovementObserver.getInstance().removeObserver(tempCard.getAll());

                return new CardIterator(tempCard.getAll());
            case MOVEMENT:
                return new MovementIterator(new MovementOperation(toFind, true).getAll());
        }
        return null;
    }

    /**
     * @param toFind carta da cercare
     * @param reverse true se iterare dall'ultimo al primo elemento, false se dal primo all'ultimo
     * @return ritorna un iteratore di movimenti su una carta specificata
     */
    public static Iterator getMovementCardIterator(String toFind, boolean reverse) {
        return new MovementIterator(new MovementOperation(toFind, true).getAll(), reverse);
    }

    /**
     * @param toFind carta da cercare
     * @param reverse true se iterare dall'ultimo al primo elemento, false se dal primo all'ultimo
     * @return ritorna un iteratore di movimenti di una persona specificata
     */
    public static Iterator getMovementUserIterator(String toFind, boolean reverse) {
        return new MovementIterator(new MovementOperation(toFind, false).getAll(), reverse);
    }
}
