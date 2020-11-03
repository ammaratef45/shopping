package edu.miu.groupx.order.orderservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.order.orderservice.domain.ESequenceType;
import edu.miu.groupx.order.orderservice.domain.IRepositoryConstant;
import edu.miu.groupx.order.orderservice.domain.SequenceNumber;

@Repository
public interface SequenceNumberRepository extends JpaRepository<SequenceNumber,Long> {
    public static class QUERY{
        public static final String findBySequenceType = "select a from SequenceNumber a where a.sequenceType= :sequenceType ";
    }
    public static  class QUERY_NAME{
        public static final String findBySequenceType = "SequenceNumber.findBySequenceType";
    }
    public SequenceNumber findBySequenceType(@Param(IRepositoryConstant.SEQUENCE_TYPE) ESequenceType sequenceType);
}