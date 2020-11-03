package edu.miu.groupx.order.orderservice.service.impl;


import org.springframework.stereotype.Service;

import edu.miu.groupx.order.orderservice.Repository.SequenceNumberRepository;
import edu.miu.groupx.order.orderservice.domain.ESequenceType;
import edu.miu.groupx.order.orderservice.domain.SequenceNumber;
import edu.miu.groupx.order.orderservice.service.SequenceNumberService;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class SequenceNumberServiceImpl implements SequenceNumberService {
     private SequenceNumberRepository sequenceNumberRepository;
    public SequenceNumberServiceImpl(SequenceNumberRepository sequenceNumberRepository){
        this.sequenceNumberRepository=sequenceNumberRepository;
    }

    private Long getNextSequence(ESequenceType sequenceType){
        try {
            SequenceNumber sequence =sequenceNumberRepository.findBySequenceType(sequenceType);
            sequence.setSequence(sequence.getSequence() + Long.valueOf((1)));
            sequenceNumberRepository.save(sequence);
            return sequence.getSequence();
        } catch (Exception ex) {

            // That means we do not have any sequence, we need to create one
            SequenceNumber sequence = new SequenceNumber();
            sequence.setSequenceType(sequenceType);
            sequence.setSequence(Long.valueOf((1)));
            // create the sequence
            sequenceNumberRepository.save(sequence);
            return sequence.getSequence();
        }
    }
    private Integer getCurrentYear(){
        int year= LocalDate.now().getYear();
        return  year;
    }

    @Override
    public String getNextOrderNumber() {
        //PR+SEQUENCE+YEAR
        String  prefix="OR";
        //OR+SEQUENCE+YEAR eg:OR12020
        return  prefix+getNextSequence(ESequenceType.ORDER)+getCurrentYear();
    }
}