package com.example.banksystem;

import com.example.banksystem.operation.CardOperation;
import com.example.banksystem.operation.HolderOperation;
import com.example.banksystem.operation.MovementOperation;
import com.example.banksystem.operation.ProductOperation;

public class Actions {
    private static final Actions instance = new Actions();
    public final HolderOperation holderOperation = (HolderOperation) Factory.getNewOperation(Factory.OperationType.HOLDER);
    public final CardOperation cardOperation = (CardOperation) Factory.getNewOperation(Factory.OperationType.CARD);
    public final MovementOperation movementOperation = (MovementOperation) Factory.getNewOperation(Factory.OperationType.MOVEMENT);
    public final ProductOperation productOperation = new ProductOperation();

    //Singleton, previene l'istanziazione da parte di altre classi
    private Actions() {}
    public static Actions getInstance() {
        return instance;
    }
}
