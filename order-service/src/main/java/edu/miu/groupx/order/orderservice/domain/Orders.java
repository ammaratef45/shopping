package edu.miu.groupx.order.orderservice.domain;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	
	private String orderNumber;
	
	 @Column(name = "order_created")
	 @CreationTimestamp
	 @Temporal(TemporalType.TIMESTAMP)
	    private Date order_created;
	
	private BigDecimal totalAmount=BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;
	
	@JsonProperty
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "order")
	private List<OrderDetails> orderDetails;
	@JoinColumn(name="CARD_ID")
	@ManyToOne
	private Card card;
	@OneToOne
    private ShippingAddress shippingAddress;

	public Date getOrder_created() {
		return order_created;
	}

	public void setOrder_created(Date order_created) {
		this.order_created = order_created;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Orders(){

	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	
	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "Orders{" +
				"orderId=" + orderId +
				", createdDate=" + order_created +
				", totalAmount=" + totalAmount +
				", status=" + status +
				'}';
	}
}
