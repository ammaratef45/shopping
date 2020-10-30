package edu.miu.groupx.card.cardservice.controller;

import edu.miu.groupx.card.cardservice.model.CardStatus;
import edu.miu.groupx.card.cardservice.model.MasterCard;
import edu.miu.groupx.card.cardservice.model.wrappermodel.CardWrapper;
import edu.miu.groupx.card.cardservice.service.MasterCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/master-card")
public class MasterCardController {
    @Autowired
    private MasterCardService masterCardService;

    @GetMapping("/")
    public ResponseEntity<List<MasterCard>> getAllMasterCards() {

        return new ResponseEntity<List<MasterCard>>(masterCardService.findAllCards(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterCard> getMasterCard(@PathVariable Long id) {
        return new ResponseEntity<MasterCard>(this.masterCardService.findCardById(id), HttpStatus.CREATED);

    }

    @PostMapping("")
    public ResponseEntity<MasterCard> createNewMasterCard(@RequestBody String holderName) {
        System.out.println("**********got a call from bank service **************");
        return new ResponseEntity<MasterCard>(this.masterCardService.createNewCardForCustomer(holderName), HttpStatus.CREATED);
    }

    //    @PutMapping("/")
//    public ResponseEntity<MasterCard> updateMasterCard(@RequestBody MasterCard card) {
//        return new ResponseEntity<MasterCard>(this.masterCardService.(card), HttpStatus.CREATED);
//    }
    @PostMapping("verify")
    public ResponseEntity<String> getMasterCardStatus(@RequestBody CardWrapper card) {
        String holderName = card.getCardNumber();
        String CCV = card.getCCV();
        String cardStatus = "";
        try {
            cardStatus = masterCardService.getMasterCardStatus(holderName, CCV);
        } catch (Exception e) {
            return new ResponseEntity<>(CardStatus.INVALID.getCardStatus(), HttpStatus.CREATED);
        }
        return new ResponseEntity<String>(cardStatus, HttpStatus.CREATED);
    }

}
