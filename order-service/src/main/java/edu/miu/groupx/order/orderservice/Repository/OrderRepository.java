package edu.miu.groupx.order.orderservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.order.orderservice.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

//	public Orders findOneByUserId(Long userId);
//	public List<Orders> findAllSubSelect();

}
