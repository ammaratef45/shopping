package edu.miu.groupx.card.cardservice.service;

import edu.miu.groupx.card.cardservice.model.VisaCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VisaCardService {
    public VisaCard findCardById(Long id);

    public void deleteCard(VisaCard card);

    public VisaCard createNewCardForCustomer(String cardHolderName);

    public List<VisaCard> findAllCards();

    public String getVisaCardStatus(String cardNumber, String CCV);

}
