package edu.miu.groupx.order.orderservice.domain;

import java.time.LocalDateTime;

import javax.persistence.*;


@Entity
public class OrderHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
private String description;
	
	private LocalDateTime date;
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Users user;

	@JoinColumn(name="ORDER_ID")
	@ManyToOne
	private Orders orders;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;
	public Long getLongId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	
	
	
	

}
