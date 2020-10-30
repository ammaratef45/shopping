package edu.miu.groupx.bankservice.service;

import edu.miu.groupx.bankservice.model.Card;
import edu.miu.groupx.bankservice.model.CheckingAccount;
import edu.miu.groupx.bankservice.model.Customer;
import edu.miu.groupx.bankservice.model.wrappermodel.BankResponseMessages;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
    public CheckingAccount createCheckingAccount(String holderName, String cardType);

    public CheckingAccount updateCheckingAccount(CheckingAccount account);

    public void deleteCheckingAccount(CheckingAccount account);

    public CheckingAccount findCheckingAccountById(Long id);

    public List<CheckingAccount> getAllCheckingAccount();

    public BankResponseMessages deposit(BigDecimal amount, String accountNumber);

    public BankResponseMessages withdraw(BigDecimal amount, String accountNumber);

    public CheckingAccount findAccountByAccountNumber(String accountNumber);

    public boolean hasEnoughBalance(BigDecimal amount, String accountNumber);

    public BankResponseMessages makePayment(String sourceAccount, String destinationAccount, BigDecimal amount);

    public String findCheckingAccountByCardNumberAndCCV(Card card);
}
