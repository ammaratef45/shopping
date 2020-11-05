package edu.miu.groupx.payment.paymentservice.service.impl;

import edu.miu.groupx.payment.paymentservice.exception.PaymentException;
import edu.miu.groupx.payment.paymentservice.model.*;
import edu.miu.groupx.payment.paymentservice.model.enums.*;
import edu.miu.groupx.payment.paymentservice.repository.PaymentRepository;
import edu.miu.groupx.payment.paymentservice.service.PaymentService;
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
public class PaymentServiceImplementation implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${card-service.url}")
    private String cardServiceUrl;
    @Value("${bank-service.url}")
    private String bankServiceUrl;


    @Override
    public Payment findPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentException("Payment not found!! "));
    }

    @Override
    public void deletePayment(Payment payment) {
        paymentRepository.delete(payment);
    }

    @Override
    public Transaction chargeOrder(Order newOrder) {
        Card paymentCard = newOrder.getPayerCard();
        String cardUrl = this.getCardVerifierUrl(paymentCard);

        Transaction transaction = new Transaction();
        transaction.setOrderNumber(newOrder.getOrderNumber());

        if (!cardUrl.isEmpty()) {
            //call card api to verify the card

            String payingCardStatus = cardServiceCaller(cardUrl, newOrder);


            if (payingCardStatus.equals(CardStatus.VALID.getCardStatus())) {

                //if card is valid
                //call the bank service to carry the transaction
                BankResponseMessage response = bankServiceCaller(bankServiceUrl, newOrder);

                Payment newPayment = createPaymentFromOrder(newOrder);
                newPayment.setStatus(response.getTransferStatus());

                transaction.setStatus(response.getTransferStatus());
                transaction.setStatus(response.getTransferStatus());
                transaction.setPaymentMessage(response.getTransferMessage());
                paymentRepository.save(newPayment);

            } else {
                transaction.setPaymentMessage(TransactionStatus.CARD_ERROR.getTransactionStatus());
                transaction.setStatus(TransactionStatus.FAILED.getTransactionStatus());
            }

        } else {

            transaction.setStatus(TransactionStatus.CARD_NOT_SUPPORTED.getTransactionStatus());
        }

        return transaction;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }


    @Override
    public BalanceStatus verifyCardBalance(Card card, BigDecimal amount) {
        //call a bank api and ask for the amount of available balance the user has

        //if the balance is sufficient then send Balance status message to the caller
        return null;
    }

    @Override
    public String getCardVerifierUrl(Card card) {
        String cardNumber = card.getCardNumber();
        Integer startDigit = null;
        try {
            startDigit = Integer.parseInt(cardNumber.substring(0, 1));
        } catch (NumberFormatException e) {
            return "";
        }

        if (startDigit == CardType.MASTERCARD.getStartDigit()) {
            return CardCaller.MASTERCARD_URI.getCardParameter();

        } else if (startDigit == CardType.VISACARD.getStartDigit()) {
            return CardCaller.VISACARD_URI.getCardParameter();
        }
        return "";
    }

    @Override
    public String generateTransactionId() {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 15; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }

    @Override
    public String maskCardNumber(String cardNumber) {
        int maskable = cardNumber.length() - 4;
        int nonMaskable = 0;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < maskable; i++) {
            sb.append("*");
            nonMaskable++;
        }
        sb.append(cardNumber.substring(nonMaskable, cardNumber.length()));
        return sb.toString();
    }

    public String cardServiceCaller(String url, Order order) {
        Card payingCard = order.getPayerCard();
        HttpHeaders cardHeaders = new HttpHeaders();
        HttpEntity<Card> cardHttpEntity = new HttpEntity<>(payingCard, cardHeaders);
        ResponseEntity<String> cardStatus = restTemplate.postForEntity(cardServiceUrl + url, cardHttpEntity, String.class);
        String payingCardStatus = cardStatus.getBody();
        return payingCardStatus;
    }

    public BankResponseMessage bankServiceCaller(String url, Order order) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Order> orderHttpEntity = new HttpEntity<>(order, headers);
        ResponseEntity<BankResponseMessage> paymentStatus = restTemplate.postForEntity(bankServiceUrl + BankCaller.BANK_PAYMENT_URI.getBankApiUri(), orderHttpEntity, BankResponseMessage.class);
        BankResponseMessage response = paymentStatus.getBody();
        return response;
    }

    /**
     * calls the userDetail service and gets the vendor account number
     * for the given userId
     * @param url  the user detail service url
     * @param vendorId
     * @return the account number for a given user Id
     */
    public String userServiceCaller(String url, Long vendorId) {
        ResponseEntity<String> accountNumber = restTemplate.getForEntity(url+"/"+vendorId, String.class);
        return accountNumber.getBody();
    }

    public Payment createPaymentFromOrder(Order newOrder) {
        Payment newPayment = new Payment();
        newPayment.setOrderNumber(newOrder.getOrderNumber());
        newPayment.setPayingCardNumber(this.maskCardNumber(newOrder.getPayerCard().getCardNumber()));
        newPayment.setPaymentDate(LocalDate.now());
        newPayment.setAmount(newOrder.getAmount());
        newPayment.setRecipientAccountNumber(newOrder.getRecipientAccountNumber());
        newPayment.setTransactionId(this.generateTransactionId());

        return newPayment;
    }



}
