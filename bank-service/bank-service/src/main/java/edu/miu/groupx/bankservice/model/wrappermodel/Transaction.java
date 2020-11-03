package edu.miu.groupx.bankservice.model.wrappermodel;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Transaction {
    private String accountNumber;
    private BigDecimal amount;
}
