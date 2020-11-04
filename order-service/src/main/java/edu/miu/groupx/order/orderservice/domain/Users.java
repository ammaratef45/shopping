package edu.miu.groupx.order.orderservice.domain;

import java.util.List;

import javax.persistence.*;

@Entity(name="Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	private String email;

	private String userName;

	@OneToMany
	private List<OrderHistory> orderHistroy;
	
	public List<OrderHistory> getOrderHistroy() {
		return orderHistroy;
	}

	public void setOrderHistroy(List<OrderHistory> orderHistroy) {
		this.orderHistroy = orderHistroy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
