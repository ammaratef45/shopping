package edu.miu.groupx.payment.paymentservice.model;

import edu.miu.groupx.payment.paymentservice.model.enums.PaymentStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String transactionId;
    private String orderNumber;
    private String payingCardNumber;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String recipientAccountNumber ;
    private String status;

}
