package edu.miu.groupx.order.orderservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.order.orderservice.domain.OrderHistory;
import edu.miu.groupx.order.orderservice.domain.Orders;
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>{
	@Query("SELECT a FROM OrderHistory a WHERE a.orders = :order")
	List<OrderHistory> findByOrder(@Param("order")Orders order);
}
