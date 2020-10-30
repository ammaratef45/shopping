package edu.miu.groupx.card.cardservice.model;

public enum CardStatus {
    INVALID("INVALID"),
    VALID("VALID"),
    EXPIRED("EXPIRED");
    private String cardStatus;
    public String getCardStatus(){
        return this.cardStatus;
    }
    private CardStatus(String cardStatus){
        this.cardStatus = cardStatus;
    }
}
