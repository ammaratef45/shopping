package edu.miu.groupx.payment.paymentservice.service;

import edu.miu.groupx.payment.paymentservice.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    public Payment findPaymentById(Long id);
    public void deletePayment(Payment payment);
    public Transaction chargeOrder(Order order);
    public Payment updatePayment(Payment payment);
    public List<Payment>findAllPayments();
    public BalanceStatus verifyCardBalance(Card card, BigDecimal amount);
    public String getCardVerifierUrl(Card card);
    public String generateTransactionId();
    public String maskCardNumber(String cardNumber);

}
