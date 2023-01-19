package com.example.banksystem;

import com.example.banksystem.iterator.Iterator;
import com.example.banksystem.model.*;
import com.example.banksystem.observer.CardObserver;
import com.example.banksystem.operation.*;

public class Tests {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Iterator<Holder> iterator = factory.getIterator(Factory.OperationType.HOLDER);
        //HolderOperation holderOperation = new HolderOperation();

        //Oppure senza il Factory:
        //MovementsOperation movementsOperation = new MovementsOperation();
        //Iterator<Movements> iterator = movementsOperation.iterator();

        while (iterator.hasNext()) {
            Holder holder = iterator.next();
            //Fa qualcosa...
            //System.out.println("Trovato: " + holder.getCf() + " " + holder.getFirstname() + " " + holder.getLastname());

        }
    }
}
