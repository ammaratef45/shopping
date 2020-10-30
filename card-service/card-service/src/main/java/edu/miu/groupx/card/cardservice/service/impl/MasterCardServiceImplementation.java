package edu.miu.groupx.card.cardservice.service.impl;

import edu.miu.groupx.card.cardservice.exception.CardException;
import edu.miu.groupx.card.cardservice.model.CardStatus;
import edu.miu.groupx.card.cardservice.model.CardType;
import edu.miu.groupx.card.cardservice.model.MasterCard;
import edu.miu.groupx.card.cardservice.repository.MasterCardRepository;
import edu.miu.groupx.card.cardservice.service.CardUtilService;
import edu.miu.groupx.card.cardservice.service.MasterCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class MasterCardServiceImplementation implements MasterCardService {
    @Autowired
    private MasterCardRepository masterCardRepository;
    @Autowired
    private CardUtilService cardUtilService;


    @Override
    public MasterCard findCardById(Long id) {

        return masterCardRepository.findById(id).orElseThrow(() -> new CardException("Card not found"));
    }

    @Override
    public void deleteCard(MasterCard card) {

        masterCardRepository.delete(card);

    }

    @Override
    public MasterCard createNewCardForCustomer(String cardHolderName) {
        //generate new master-card number;
        Integer firstDigit = CardType.MASTER_CARD_START_NUMBER.getValue();
        Integer cardLength = CardType.MASTER_CARD_LENGTH.getValue();
        MasterCard newCard = new MasterCard();
        String cardNumber = cardUtilService.generateCreditCardNumber(firstDigit + "", cardLength);
        newCard.setHolderName(cardHolderName);
        newCard.setCardNumber(cardNumber);
        newCard.setExpirationDate(cardUtilService.generateExpirationDate());
        newCard.setCCV(cardUtilService.generateCCV());
        newCard.setStatus(CardStatus.VALID);
        return masterCardRepository.save(newCard);
    }

    @Override
    public List<MasterCard> findAllCards() {

        return masterCardRepository.findAll();
    }

    @Override
    public String getMasterCardStatus(String cardNumber, String CCV) {
        System.out.println("Card number is: "+cardNumber+", CCV is "+CCV);
        MasterCard card = masterCardRepository.findMasterCardByCardNumberAndCCV(cardNumber, CCV);
        return card.getStatus().getCardStatus();
    }


}


