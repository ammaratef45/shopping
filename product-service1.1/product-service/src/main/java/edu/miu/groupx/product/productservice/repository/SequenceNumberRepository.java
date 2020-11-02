package edu.miu.groupx.product.productservice.repository;



import edu.miu.groupx.product.productservice.models.ESequenceType;
import edu.miu.groupx.product.productservice.models.IRepositoryConstant;
import edu.miu.groupx.product.productservice.models.SequenceNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@Repository
public interface SequenceNumberRepository /* extends JpaRepository<SequenceNumber,Long> */ {
  /*
    public static class QUERY{
        public static final String findBySequenceType = "select a from SequenceNumber a where a.sequenceType= :sequenceType ";
    }
    public static  class QUERY_NAME{
        public static final String findBySequenceType = "SequenceNumber.findBySequenceType";
    }
    public SequenceNumber findBySequenceType(@Param(IRepositoryConstant.SEQUENCE_TYPE) ESequenceType sequenceType);
*/
}

