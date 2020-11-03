package edu.miu.groupx.payment.paymentservice.model.enums;

public enum CardType {
    MASTERCARD(5),
    VISACARD(4);
    private Integer startDigit;

    public Integer getStartDigit() {
        return this.startDigit;
    }

    private CardType(Integer startDigit) {
        this.startDigit = startDigit;
    }

}
