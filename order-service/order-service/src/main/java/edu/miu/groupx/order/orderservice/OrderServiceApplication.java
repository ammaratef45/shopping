package edu.miu.groupx.order.orderservice;

import edu.miu.groupx.order.orderservice.Repository.OrderDetailController;
import edu.miu.groupx.order.orderservice.Repository.OrderRepository;
import edu.miu.groupx.order.orderservice.domain.OrderDetails;
import edu.miu.groupx.order.orderservice.domain.Orders;
import edu.miu.groupx.order.orderservice.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
//@EnableEurekaClient
//@EnableResourceServer
public class OrderServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Autowired
	OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		OrderDetails orderDetails = OrderDetails.builder()
				.itemPrice(5.0)
				.productName("product1")
				.quantity(4.0)
				.subTotal(400.0)
				.build();
		List<OrderDetails> details = new ArrayList<>();
		details.add(orderDetails);
		Orders orders = Orders.builder()
				.createdDate(date)
				.totalAmount(5000.0)
				.orderDetails(details)
				.build();


	}
}
