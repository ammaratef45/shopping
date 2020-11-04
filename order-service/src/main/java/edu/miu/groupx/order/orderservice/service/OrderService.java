package edu.miu.groupx.order.orderservice.service;


import java.util.List;

import org.springframework.stereotype.Repository;

import edu.miu.groupx.order.orderservice.domain.OrderHistory;
import edu.miu.groupx.order.orderservice.domain.Orders;

@Repository
public interface OrderService  {
	
	public void  save(Orders orders);
	
	public List<Orders> getAllOrders();
	public Orders getOrderById(long orderId);
	public Orders  getOrderByUserID(Long  userId);
	public Orders updateOrder(long orderId, Orders orders);
	public void  deleteOrder(Long orderId);
	public List<OrderHistory> findOrderHistoryByOrder(Orders orders);
	public Orders placeOrder(Orders orders);
	public Orders cancelOrder(Orders orders);
}

