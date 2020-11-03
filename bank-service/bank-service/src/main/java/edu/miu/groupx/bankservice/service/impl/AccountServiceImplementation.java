package edu.miu.groupx.bankservice.service.impl;

import edu.miu.groupx.bankservice.exeception.AccountException;
import edu.miu.groupx.bankservice.model.AccountEntries;
import edu.miu.groupx.bankservice.model.Card;
import edu.miu.groupx.bankservice.model.CheckingAccount;
import edu.miu.groupx.bankservice.model.TransactionType;
import edu.miu.groupx.bankservice.model.wrappermodel.BankResponseMessages;
import edu.miu.groupx.bankservice.model.wrappermodel.OperationMessages;
import edu.miu.groupx.bankservice.model.wrappermodel.OperationStatus;
import edu.miu.groupx.bankservice.repository.CheckingAccountRepository;
import edu.miu.groupx.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class AccountServiceImplementation implements AccountService {
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Value("${card-service.url}")
    private String cardServiceUrl;
    @Autowired
    private RestTemplate restTemplate;
    Random random = new Random(System.currentTimeMillis());

    @Override
    public CheckingAccount createCheckingAccount(String holderName, String cardType) {
        //call the card API here (either master-card  or visa-card)
        HttpHeaders headers = new HttpHeaders();
        //set headers and tokens here
        HttpEntity<String> cardHttpEntity = new HttpEntity<>(holderName, headers);
        System.out.println("calling card service.............." + cardType);
        // send request to the card service and ask it to generate a new card number for the new customer
        ResponseEntity<Card> newCard = restTemplate.postForEntity(cardServiceUrl + "/" + cardType, cardHttpEntity, Card.class);
        //set the details of the card individually. this is important for the fact that the card id will not be set to the one gotten from the remote
        CheckingAccount newCheckingAccount = new CheckingAccount();
        newCheckingAccount.setAccountNumber(this.generateAccountNumber());

        if (cardType.equals("master-card")) {
            Card newMasterCard = new Card();
            newMasterCard.setHolderName(newCard.getBody().getHolderName());
            newMasterCard.setCardNumber(newCard.getBody().getCardNumber());
            newMasterCard.setCCV(newCard.getBody().getCCV());
            newMasterCard.setExpirationDate(newCard.getBody().getExpirationDate());
            newMasterCard.setStatus(newCard.getBody().getStatus());
            newCheckingAccount.setCard(newMasterCard);

        } else {
            Card newVisaCard = new Card();
            newVisaCard.setHolderName(newCard.getBody().getHolderName());
            newVisaCard.setCardNumber(newCard.getBody().getCardNumber());
            newVisaCard.setCCV(newCard.getBody().getCCV());
            newVisaCard.setExpirationDate(newCard.getBody().getExpirationDate());
            newVisaCard.setStatus(newCard.getBody().getStatus());
            newCheckingAccount.setCard(newVisaCard);
        }


        return newCheckingAccount;
    }

    @Override
    public CheckingAccount updateCheckingAccount(CheckingAccount account) {

        return checkingAccountRepository.save(account);
    }

    @Override
    public void deleteCheckingAccount(CheckingAccount account) {

        checkingAccountRepository.delete(account);

    }

    @Override
    public CheckingAccount findCheckingAccountById(Long id) {
        return checkingAccountRepository.findById(id).orElseThrow(() -> new AccountException("Could not find account"));
    }

    @Override
    public List<CheckingAccount> getAllCheckingAccount() {

        return checkingAccountRepository.findAll();
    }

    @Override
    public BankResponseMessages deposit(BigDecimal amount, String accountNumber) {
        CheckingAccount account = null;
        BankResponseMessages responseMessages = new BankResponseMessages();
        responseMessages.setTransferDate(LocalDate.now());

        try {
            account = this.findAccountByAccountNumber(accountNumber);
            account.setBalance(account.getBalance().add(amount));
            AccountEntries accountEntry = new AccountEntries();
            accountEntry.setTransactionDate(LocalDate.now());
            accountEntry.setAmount(amount);
            accountEntry.setSubjectAccountNumber(accountNumber);
            accountEntry.setTransactionType(TransactionType.DEPOSIT);
            account.getAccountEntries().add(accountEntry);
            checkingAccountRepository.save(account);
        } catch (Exception e) {

            responseMessages.setTransferMessage(OperationMessages.DEPOSIT_UNSUCCESSFUL.getOperationMessages());
            responseMessages.setTransferStatus(OperationStatus.FAILED.getOperationStatusMessages());
            return responseMessages;
        }
        responseMessages.setTransferMessage(OperationMessages.DEPOSIT_SUCCESSFUL.getOperationMessages());
        responseMessages.setTransferStatus(OperationStatus.SUCCESS.getOperationStatusMessages());

        return responseMessages;
    }

    @Override
    public BankResponseMessages withdraw(BigDecimal amount, String accountNumber) {

        CheckingAccount account = this.findAccountByAccountNumber(accountNumber);
        BankResponseMessages responseMessages = new BankResponseMessages();
        responseMessages.setTransferDate(LocalDate.now());
        if (this.hasEnoughBalance(amount, accountNumber)) {
            try {
                account.setBalance(account.getBalance().subtract(amount));
                AccountEntries accountEntry = new AccountEntries();
                accountEntry.setTransactionDate(LocalDate.now());
                accountEntry.setAmount(amount);
                accountEntry.setSubjectAccountNumber(accountNumber);
                accountEntry.setTransactionType(TransactionType.WITHDRAWAL);
                account.getAccountEntries().add(accountEntry);
                checkingAccountRepository.save(account);

            } catch (Exception e) {
                responseMessages.setTransferStatus(OperationMessages.WITHDRAWAL_UNSUCCESSFUL.getOperationMessages());
                responseMessages.setTransferMessage(OperationStatus.FAILED.getOperationStatusMessages());
                return responseMessages;

            }

        } else {
            responseMessages.setTransferStatus(OperationMessages.NOT_ENOUGH_BALANCE.getOperationMessages());
            responseMessages.setTransferMessage(OperationStatus.FAILED.getOperationStatusMessages());
            return responseMessages;
        }
        responseMessages.setTransferStatus(OperationMessages.WITHDRAWAL_SUCCESSFUL.getOperationMessages());
        responseMessages.setTransferMessage(OperationStatus.FAILED.getOperationStatusMessages());
        return responseMessages;
    }

    @Override
    public CheckingAccount findAccountByAccountNumber(String accountNumber) {

        return checkingAccountRepository.findCheckingAccountByAccountNumber(accountNumber);
    }

    @Override
    public boolean hasEnoughBalance(BigDecimal amount, String accountNumber) {
        BigDecimal currentBalance = null;
        BigDecimal difference = null;
        try {
            CheckingAccount account = this.findAccountByAccountNumber(accountNumber);
            currentBalance = account.getBalance();
            difference = currentBalance.subtract(amount);

        } catch (Exception e) {
            System.out.println("Can not find account..............");
        }
        return (difference.compareTo(BigDecimal.ZERO) != -1);
    }


    private BankResponseMessages transferFund(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) {
        Boolean transferStatus = false;
        BankResponseMessages bankResponseMessages = new BankResponseMessages();
        LocalDate transactionDate = LocalDate.now();
        bankResponseMessages.setTransferDate(transactionDate);

        if (hasEnoughBalance(amount, sourceAccountNumber)) {
            CheckingAccount destinationAccount = null;
            try {
                destinationAccount = this.findAccountByAccountNumber(destinationAccountNumber);
                destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

            } catch (Exception e) {
                bankResponseMessages.setTransferMessage(OperationMessages.CANNOT_VERIFY_RECIPIENT_ACCOUNT.getOperationMessages());
                bankResponseMessages.setTransferStatus(OperationStatus.FAILED.getOperationStatusMessages());
                return bankResponseMessages;
            }
            CheckingAccount sourceAccount = this.findAccountByAccountNumber(sourceAccountNumber);
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));


            AccountEntries sourceEntry = new AccountEntries();
            sourceEntry.setTransactionDate(transactionDate);
            sourceEntry.setAmount(amount);
            sourceEntry.setSubjectAccountNumber(destinationAccountNumber);
            sourceEntry.setTransactionType(TransactionType.WITHDRAWAL);
            sourceAccount.getAccountEntries().add(sourceEntry);
            checkingAccountRepository.save(sourceAccount);

            AccountEntries destinationEntry = new AccountEntries();
            destinationEntry.setTransactionDate(transactionDate);
            destinationEntry.setAmount(amount);
            destinationEntry.setSubjectAccountNumber(sourceAccountNumber);
            destinationEntry.setTransactionType(TransactionType.DEPOSIT);
            destinationAccount.getAccountEntries().add(destinationEntry);
            checkingAccountRepository.save(destinationAccount);
            bankResponseMessages.setTransferMessage(OperationMessages.PAYMENT_COMPLETED_SUCCESSFULLY.getOperationMessages());
            bankResponseMessages.setTransferStatus(OperationStatus.SUCCESS.getOperationStatusMessages());

        }
        return bankResponseMessages;
    }

    @Override
    public BankResponseMessages makePayment(String sourceAccount, String destinationAccount, BigDecimal amount) {
        return this.transferFund(sourceAccount, destinationAccount, amount);
    }

    @Override
    public String findCheckingAccountByCardNumberAndCCV(Card card) {
        CheckingAccount payerAccount = checkingAccountRepository.findCheckingAccountByCardNumberAndCCV(card.getCardNumber(), card.getCCV());

        return payerAccount.getAccountNumber();
    }

    /**
     * Account Utility methods
     */
    private String generateAccountNumber() {

        StringBuilder stringBuilder = new StringBuilder("2");
        for (int i = 0; i < 9; i++) {
            int rand = random.nextInt(10);
            stringBuilder.append(rand);
        }
        return stringBuilder.toString();
    }

    private String generateTransactionNumber() {
        StringBuilder stringBuilder = new StringBuilder("TR");
        for (int i = 0; i < 9; i++) {
            int rand = random.nextInt(10);
            stringBuilder.append(rand);
        }
        return stringBuilder.toString();
    }


}
  
