package edu.miu.groupx.bankservice.controller;


import edu.miu.groupx.bankservice.model.CheckingAccount;
import edu.miu.groupx.bankservice.model.Customer;
import edu.miu.groupx.bankservice.model.wrappermodel.*;
import edu.miu.groupx.bankservice.service.AccountService;
import edu.miu.groupx.bankservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("bank")
public class BankServiceController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    /**
     * ************************************USER RELATED APIS ***********************************************************
     *
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getAllUsers() {

        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.CREATED);
    }

    @PostMapping("/users/{cardType}")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody User newUser, @PathVariable String cardType) {

        return new ResponseEntity<>(customerService.createNewCustomer(newUser, cardType), HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer updatedCustomer) {
        return new ResponseEntity<>(customerService.updateCustomerDetail(updatedCustomer), HttpStatus.CREATED);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.CREATED);
    }

    /**
     * ********************************ACCOUNT RELATED APIS******************************************
     *
     */
    @PostMapping("/make-payment")
    public ResponseEntity<BankResponseMessages> makePayment(@RequestBody Order order) {
        System.out.println("got called from the payment service.......");
        Payment newPayment = new Payment();
        String sourceAccountNumber = null;
        try {
            sourceAccountNumber = accountService.findCheckingAccountByCardNumberAndCCV(order.getPayerCard());

        } catch (Exception e) {
            BankResponseMessages response = new BankResponseMessages();
            response.setTransferStatus(OperationStatus.FAILED.getOperationStatusMessages());
            response.setTransferMessage(OperationMessages.CANNOT_VERIFY_PAYER_ACCOUNT.getOperationMessages());
            response.setTransferDate(LocalDate.now());
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }

        String destinationAccountNumber = order.getRecipientAccountNumber();
        BigDecimal amount = order.getAmount();


        return new ResponseEntity<BankResponseMessages>(accountService.makePayment(sourceAccountNumber, destinationAccountNumber, amount), HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<BankResponseMessages> deposit(@RequestBody Transaction transaction) {
        String accountNumber = transaction.getAccountNumber();
        BigDecimal amount = transaction.getAmount();
        return new ResponseEntity<BankResponseMessages>(accountService.deposit(amount, accountNumber), HttpStatus.CREATED);

    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankResponseMessages> withdraw(@RequestBody Transaction transaction) {
        String accountNumber = transaction.getAccountNumber();
        BigDecimal amount = transaction.getAmount();
        return new ResponseEntity<>(accountService.withdraw(amount, accountNumber), HttpStatus.CREATED);
    }
    @GetMapping("/accounts")
    public ResponseEntity<List<CheckingAccount>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllCheckingAccount(), HttpStatus.CREATED);
    }
}
