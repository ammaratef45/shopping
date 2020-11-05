package edu.miu.groupx.order.orderservice.controller;


import edu.miu.groupx.order.orderservice.domain.DTOs.OrdersDTO;
import edu.miu.groupx.order.orderservice.domain.OrderDetails;
import edu.miu.groupx.order.orderservice.domain.Orders;
import edu.miu.groupx.order.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/user/{userId}")
    public OrdersDTO getOrderByUserId(@PathVariable("userId") long userId){
        return orderService.getByUserId(userId);
    }

    @GetMapping("/order/{orderId}")
    public Optional<Orders> getByOrderId(@PathVariable("orderId") long orderId){
        return orderService.getByorderId(orderId);
    }

    @PostMapping
    public Orders addOrder(@RequestBody Orders order){


        List<OrderDetails> orderDetails = order.getOrderDetails();

        Double total = 0.0;
        for(OrderDetails o : orderDetails){
        	o.setOrder(order);
            o.setSubTotal(o.getQuantity() * o.getItemPrice());
            total =+ o.getSubTotal();
        }

        order.setOrderDetails(orderDetails);
        order.setTotalAmount(total);

        return orderService.addOrder(order);

    }

    @DeleteMapping("/remove/{orderId}")
    public void deleteByOrderid(@PathVariable("orderId") long orderId){
        orderService.deleteOrderById(orderId);
    }

    @PutMapping("/update/{orderId}")
    public Orders updateOrder(@PathVariable("orderId") long orderId, @RequestBody Orders order){
        return orderService.updateOrder(orderId, order);
    }
}
