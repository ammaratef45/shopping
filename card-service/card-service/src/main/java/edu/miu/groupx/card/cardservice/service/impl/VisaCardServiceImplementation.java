package edu.miu.groupx.card.cardservice.service.impl;

import edu.miu.groupx.card.cardservice.exception.CardException;
import edu.miu.groupx.card.cardservice.model.CardStatus;
import edu.miu.groupx.card.cardservice.model.CardType;
import edu.miu.groupx.card.cardservice.model.VisaCard;
import edu.miu.groupx.card.cardservice.repository.VisaCardRepository;
import edu.miu.groupx.card.cardservice.service.CardUtilService;
import edu.miu.groupx.card.cardservice.service.VisaCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class VisaCardServiceImplementation implements VisaCardService {

    @Autowired
    private VisaCardRepository visaCardRepository;
    @Autowired
    private CardUtilService cardUtilService;


    @Override
    public VisaCard findCardById(Long id) {

        return this.visaCardRepository.findById(id).orElseThrow(() -> new CardException("Card not found"));
    }

    @Override
    public void deleteCard(VisaCard card) {

        this.visaCardRepository.delete(card);
    }

    @Override
    public VisaCard createNewCardForCustomer(String cardHolderName) {
        Integer firstDigit = CardType.VISA_CARD_START_NUMBER.getValue();
        Integer cardLength = CardType.VISA_CARD_LENGTH.getValue();
        VisaCard newCard = new VisaCard();
        String cardNumber;
        cardNumber = cardUtilService.generateCreditCardNumber(firstDigit + "", cardLength);
        newCard.setHolderName(cardHolderName);
        newCard.setCardNumber(cardNumber);
        newCard.setExpirationDate(cardUtilService.generateExpirationDate());
        newCard.setCCV(cardUtilService.generateCCV());
        newCard.setStatus(CardStatus.VALID);
        return visaCardRepository.save(newCard);
    }

    @Override
    public List<VisaCard> findAllCards() {

        return this.visaCardRepository.findAll();
    }

    @Override
    public String getVisaCardStatus(String cardNumber, String CCV) {
        VisaCard card = visaCardRepository.findVisaCardByCardNumberAndCCV(cardNumber, CCV);
        String STATUS = "INVALID";
        return STATUS;

    }
}
