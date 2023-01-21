package com.example.banksystem;

import com.example.banksystem.model.Product;
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




    /*
    public void withDrawCard(String cardNumber, double x) {
        for (Object cardObject : cards.getAll() ){
            Card card = (Card) cardObject;
            if (card.getCard_number().equals(cardNumber)){
                switch (this.contract_type){
                    case("Basic"):
                        card.withDraw(x - 1);
                        break;
                    case("Premium"):
                        card.withDraw(x - 0.50);
                        break;
                    case("Enterprise"):
                        card.withDraw(x - 0.25);
                        break;
                }
            }
            else
                System.out.println("There are no cards");
        }
    }

    public void depositCard (String cardNumber, double x){
        for (Object cardObject : cards.getAll() ){
            Card card = (Card) cardObject;
            if (card.getCard_number().equals(cardNumber)){
                switch (this.contract_type){
                    case ("Basic"):
                        card.withDraw(x - 1);
                        break;
                    case ("Premium"):
                        card.withDraw(x - 0.50);
                        break;
                    case ("Enterprise"):
                        card.withDraw(x - 0.25);
                        break;
                }
            }
        }
    }
     */
}
