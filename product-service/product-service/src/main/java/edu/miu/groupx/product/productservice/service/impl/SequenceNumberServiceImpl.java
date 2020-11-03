package edu.miu.groupx.product.productservice.service.impl;

import edu.miu.groupx.product.productservice.models.ESequenceType;
import edu.miu.groupx.product.productservice.models.SequenceNumber;
import edu.miu.groupx.product.productservice.repository.SequenceNumberRepository;
import edu.miu.groupx.product.productservice.service.SequenceNumberService;
import org.springframework.stereotype.Service;

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
    public String getNextProductNumber() {
        //PR+SEQUENCE+YEAR
        String  prefix="PR";
        //OR+SEQUENCE+YEAR eg:PR12020
        return  prefix+getNextSequence(ESequenceType.PRODUCT)+getCurrentYear();
    }


}
