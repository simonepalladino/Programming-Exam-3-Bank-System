package com.example.banksystem;

import com.example.banksystem.operation.CardOperation;
import com.example.banksystem.operation.HolderOperation;
import com.example.banksystem.operation.MovementOperation;
import com.example.banksystem.operation.ProductOperation;

import java.math.BigDecimal;


public class Actions {
    private static final Actions instance = new Actions();
    public final HolderOperation holderOperation = (HolderOperation) Factory.getNewOperation(Factory.OperationType.HOLDER);
    public final CardOperation cardOperation = (CardOperation) Factory.getNewOperation(Factory.OperationType.CARD);
    public final MovementOperation movementOperation = (MovementOperation) Factory.getNewOperation(Factory.OperationType.MOVEMENT);
    public final ProductOperation productOperation = new ProductOperation();

    /**
     * Classe singleton, l'inizializzazione privata previene l'istanziazione da parte di altre classi
     */
    private Actions() {}

    /**
     * @return restituisce l'istanza della classe Singleton, in modo da poter accedere ai vari Data Access Objects (DAO)
     * @see HolderOperation
     * @see CardOperation
     * @see MovementOperation
     * @see ProductOperation
     * @see com.example.banksystem.operation.Operation
     */
    public static Actions getInstance() {
        return instance;
    }
}
