package edu.miu.groupx.card.cardservice.model.wrappermodel;

import lombok.Data;

@Data
public class CardWrapper {
    private String holderName;
    private String cardNumber;
    private String CCV;
    private String expirationDate;
}
