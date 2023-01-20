package com.example.banksystem;

import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.*;
import com.example.banksystem.observer.CardObserver;
import com.example.banksystem.operation.*;

public class Tests {
    public static void main(String[] args) {
        Iterator<Holder> iterator = Factory.getIterator(Factory.OperationType.HOLDER);

        CardOperation cards = new CardOperation();

        //HolderOperation holderOperation = new HolderOperation();

        //Oppure senza il Factory:
        //MovementsOperation movementsOperation = new MovementsOperation();
        //Iterator<Movements> iterator = movementsOperation.iterator();

        /*
        while (iterator.hasNext()) {
            Holder holder = iterator.next();
        }
         */

        CardObserver.getInstance().add(new Card("Bancomat", "101010101018", "trtlcublabla7",300, "Bancomat", null, 0));
    }
}
