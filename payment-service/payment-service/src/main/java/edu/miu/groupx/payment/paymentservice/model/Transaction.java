package edu.miu.groupx.payment.paymentservice.model;

import edu.miu.groupx.payment.paymentservice.model.enums.PaymentStatus;
import lombok.Data;

@Data
public class Transaction {
    private String orderNumber;
    private String paymentMessage;
    private String status;


}
