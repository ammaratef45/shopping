package edu.miu.groupx.order.orderservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.groupx.order.orderservice.domain.OrderHistory;
//import edu.miu.groupx.order.orderservice.domain.OrderDetails;
import edu.miu.groupx.order.orderservice.domain.Orders;
import edu.miu.groupx.order.orderservice.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("")
    public Orders addOrder(@RequestBody Orders orders){
    	 orderService.save(orders);
    	 
    	 return orders;
    	     
    }
    

	/***
	 * get All orders API
	 * 
	 *
	 ***/
    @GetMapping("")
    public List<Orders> getOrders(){
        return orderService.getAllOrders();
    }

    /***
	 * get all order By UserID
	 * 
	 *
	 ***/
	
	
	@GetMapping("users/{userId}")
	public Orders getOrderByUserID(@PathVariable int userId) {
		return orderService.getOrderByUserID((long) userId);
	}

    
    /***
	 * get all order By orderId
	 * 
	 *
	 ***/

	@GetMapping("/{orderId}")
	public Orders getOrderByOrderID(@PathVariable int orderId) {
		return orderService.getOrderById((long) orderId);
	}

	   
    /***
	 * delete order By orderId
	 * 
	 *
	 ***/

    @DeleteMapping("/{orderId}")
    public void deleteByOrderid(@PathVariable("orderId") long orderId){
        orderService.deleteOrder(orderId);
    }
    
    /***
   	 * update order By orderId
   	 * 
   	 *
   	 ***/

    @PutMapping("/{orderId}")
    public Orders updateOrder(@PathVariable("orderId") long orderId, @RequestBody Orders order){
        return orderService.updateOrder(orderId, order);
    }
    
    /***
   	 *order history by orderId 
   	 * 
   	 *
   	 ***/
    @GetMapping("orderHistory")
    public List<OrderHistory> findOrderHistoryByOrder(@RequestBody  Orders orders){
    	return orderService.findOrderHistoryByOrder(orders);
    }
    	
    /***
   	 * cancel order
   	 * 
   	 *
   	 ***/
    @PutMapping("order-status/{orderId}")
    public Orders cancelOrder(@RequestBody Orders orders) {
    	 return orderService.cancelOrder(orders);
    }
    
  
}
