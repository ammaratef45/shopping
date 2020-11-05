package edu.miu.groupx.order.orderservice.Repository;

import edu.miu.groupx.order.orderservice.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailController extends JpaRepository<OrderDetails, Long> {
}

