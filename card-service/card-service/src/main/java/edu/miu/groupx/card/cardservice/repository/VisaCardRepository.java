package edu.miu.groupx.card.cardservice.repository;

import edu.miu.groupx.card.cardservice.model.VisaCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaCardRepository extends JpaRepository<VisaCard, Long> {

    @Query(value = " select card from VisaCard card where card.cardNumber =?1 and card.CCV = ?2")
    public VisaCard findVisaCardByCardNumberAndCCV(String cardNumber, String CCV);
}
