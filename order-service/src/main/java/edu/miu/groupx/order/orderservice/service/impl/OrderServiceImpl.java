package edu.miu.groupx.order.orderservice.service.impl;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.groupx.order.orderservice.Repository.OrderHistoryRepository;
import edu.miu.groupx.order.orderservice.Repository.OrderRepository;
import edu.miu.groupx.order.orderservice.domain.OrderDetails;
import edu.miu.groupx.order.orderservice.domain.OrderHistory;
import edu.miu.groupx.order.orderservice.domain.Orders;
import edu.miu.groupx.order.orderservice.domain.Status;
import edu.miu.groupx.order.orderservice.service.OrderService;
import edu.miu.groupx.order.orderservice.service.SequenceNumberService;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired 
	OrderRepository orderRepository;
	@Autowired
	OrderHistoryRepository orderHistoryRepository;
	@Autowired
	private SequenceNumberService SequenceNumberService;

	@Override
	public void save(Orders orders) {
		BigDecimal totalOrderAmount=BigDecimal.ZERO;
		String orderNumber=SequenceNumberService.getNextOrderNumber();
		orders.setStatus(Status.PENDING);
		orders.setOrderNumber(orderNumber);
	    List<OrderDetails> orderDetailsList=orders.getOrderDetails();
	    for(OrderDetails orderDetail:orderDetailsList) {
	    	totalOrderAmount=totalOrderAmount.add(orderDetail.getSubTotal());
	    	orderDetail.setOrder(orders);
	    }
	    orders.setTotalAmount(totalOrderAmount);
		createOrderHistory(orders);	
		orderRepository.save(orders);
	}
	
	private void createOrderHistory(Orders order) {
		
		OrderHistory orderHistory=new OrderHistory();
		orderHistory.setOrders(order);
		orderHistory.setDate(LocalDateTime.now());
		orderHistory.setDescription(order.getOrderNumber()+"is "+order.getStatus());
		orderHistory.setStatus(order.getStatus());
		///orderHistory.setUser(the loggin user for spring security)
		orderHistoryRepository.save(orderHistory);
	}
	
	
	

	@Override
	public List<Orders> getAllOrders() {
		
		return (List<Orders>)orderRepository.findAll();
	}

	@Override
	public Orders getOrderById(long orderId) {
		
		return orderRepository.findById(orderId).get();
	}

	@Override
	public Orders getOrderByUserID(Long userId) {
		
		return orderRepository.findById(userId).get();
		}

	@Override
	public Orders updateOrder(long orderId, Orders orders) {
		
		Orders order=orderRepository.findById(orderId).get();
		///copy
		
	       order.setStatus(orders.getStatus());
	       order.setShippingAddress(orders.getShippingAddress());	
	       createOrderHistory(order);
		return orderRepository.save(order);
	}
 

	@Override
	public void deleteOrder(Long orderId) {
		Orders order=orderRepository.findById(orderId).get();
		createOrderHistory(order);
		orderRepository.delete(order);
		
	}

	@Override
	public Orders placeOrder(Orders orders) {
		Orders order=orderRepository.findById(orders.getOrderId()).get();
		order.setStatus(Status.PLACED);
		createOrderHistory(order);
		return orderRepository.save(order);
	}

	@Override
	public Orders cancelOrder(Orders orders) {
		Orders order=orderRepository.findById(orders.getOrderId()).get();
		order.setStatus(Status.CANCELED);
		createOrderHistory(order);
		return orderRepository.save(order);
	}

	@Override
	public List<OrderHistory> findOrderHistoryByOrder(Orders orders) {
		Orders order=orderRepository.findById(orders.getOrderId()).get();
		return orderHistoryRepository.findByOrder(order);
	}
	
	



}
