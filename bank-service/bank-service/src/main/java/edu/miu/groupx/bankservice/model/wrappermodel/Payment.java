package edu.miu.groupx.bankservice.model.wrappermodel;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Data
@Component
public class Payment {
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;

}
