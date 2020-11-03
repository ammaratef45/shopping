package edu.miu.groupx.order.orderservice.domain;



import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity

public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderDetailId;
	
	
	
	@NotNull
	private BigDecimal quantity=BigDecimal.ZERO;
	@NotNull
	private BigDecimal itemPrice=BigDecimal.ZERO;
	@NotNull
	private BigDecimal subTotal=BigDecimal.ZERO;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Long vendeorID;

	




	public OrderDetails(){

	}
	
	

	public long getVendeorID() {
		return vendeorID;
	}

	public void setVendeorID(long vendeorID) {
		this.vendeorID = vendeorID;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BigDecimal getSubTotal() {
		return getQuantity().multiply(getItemPrice());
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	
	



	


	@Override
	public String toString() {
		return "OrderDetails{" +
				"orderDetailId=" + orderDetailId +
				", quantity=" + quantity +
				", itemPrice=" + itemPrice +
				", subTotal=" + subTotal +
				", productId=" + productId +
				'}';
	}
}
