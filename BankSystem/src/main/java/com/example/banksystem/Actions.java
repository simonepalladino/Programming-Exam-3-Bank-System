package com.example.banksystem;

import com.example.banksystem.operation.CardOperation;
import com.example.banksystem.operation.HolderOperation;
import com.example.banksystem.operation.MovementOperation;

public class Actions {
    private static final Actions instance = new Actions();
    public HolderOperation holderOperation = null;
    public CardOperation cardOperation = null;
    public MovementOperation movementOperation = null;

    //Singleton, previene l'istanziazione da parte di altre classi
    private Actions() {
        if (holderOperation == null)
            holderOperation = (HolderOperation) Factory.getNewOperation(Factory.OperationType.HOLDER);

        if (cardOperation == null)
            cardOperation = (CardOperation) Factory.getNewOperation(Factory.OperationType.CARD);

        if (movementOperation == null)
            movementOperation = (MovementOperation) Factory.getNewOperation(Factory.OperationType.MOVEMENTS);
    }
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
