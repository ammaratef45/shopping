package edu.miu.groupx.bankservice.model.wrappermodel;

import edu.miu.groupx.bankservice.model.Card;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private String orderNumber;
    private String orderDescription;
    private Card payerCard;
    private String recipientAccountNumber;
    private BigDecimal amount;
}
