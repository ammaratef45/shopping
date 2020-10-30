package edu.miu.groupx.card.cardservice.repository;


import edu.miu.groupx.card.cardservice.model.MasterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCardRepository extends JpaRepository<MasterCard, Long> {
    @Query(value = "select card from MasterCard card where card.cardNumber=?1 and card.CCV=?2")
    MasterCard findMasterCardByCardNumberAndCCV(String cardNumber, String CCV);
}
