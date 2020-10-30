package edu.miu.groupx.payment.paymentservice.controller;

import edu.miu.groupx.payment.paymentservice.model.Order;
import edu.miu.groupx.payment.paymentservice.model.Payment;
import edu.miu.groupx.payment.paymentservice.model.Transaction;
import edu.miu.groupx.payment.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments/")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<List<Payment>>(paymentService.findAllPayments(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return new ResponseEntity<Payment>(paymentService.findPaymentById(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/")
    public void deletePayment(@RequestBody Payment payment) {
        paymentService.deletePayment(payment);
    }

    @PutMapping("/")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
        return new ResponseEntity<Payment>(paymentService.updatePayment(payment), HttpStatus.CREATED);
    }

    @PostMapping("/{amount}")
    public ResponseEntity<Transaction> createPayment(@RequestBody Order order) {
        return new ResponseEntity<>(paymentService.chargeOrder(order), HttpStatus.CREATED);
    }

}
