package edu.miu.groupx.payment.paymentservice.model.enums;

public enum CardCaller {
    MASTERCARD_URI("/master-card/verify"),
    VISACARD_URI("/visa-card/verify");

    private String cardParameter;
    public String getCardParameter (){
        return this.cardParameter;
    }
    private CardCaller(String cardParameter){
        this.cardParameter = cardParameter;
    }
}
