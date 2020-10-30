package edu.miu.groupx.bankservice.model.wrappermodel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BankResponseMessages {
    private String transferMessage;
    private String transferStatus;
    private LocalDate transferDate;

}
