package edu.miu.groupx.payment.paymentservice.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BankResponseMessage {
    private String transferMessage;
    private String transferStatus;
    private LocalDate transferDate;
}
