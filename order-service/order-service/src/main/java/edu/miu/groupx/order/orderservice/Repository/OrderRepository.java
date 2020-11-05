package edu.miu.groupx.order.orderservice.Repository;

import edu.miu.groupx.order.orderservice.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {


}
