package edu.miu.groupx.bankservice.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class AccountEntries {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String transactionNumber;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private String subjectAccountNumber;
    private TransactionType transactionType;

}
