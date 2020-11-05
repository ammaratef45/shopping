package edu.miu.groupx.payment.paymentservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String orderNumber;
    private String orderDescription;
    private Card payerCard;
    private String recipientAccountNumber;
    private List<VendorInfo> vendorInfos = new ArrayList<>();
    private BigDecimal amount;
}
