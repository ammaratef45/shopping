package edu.miu.groupx.payment.paymentservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Card {
    private String holderName;
    private String cardNumber;
    private String CCV;
    private String expirationDate;

}
