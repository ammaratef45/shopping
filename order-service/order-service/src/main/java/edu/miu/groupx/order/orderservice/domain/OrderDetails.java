package edu.miu.groupx.order.orderservice.domain;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.persistence.*;

@Builder
@Entity
@JsonIgnoreProperties("order")
public class OrderDetails {

	@Id
	@GeneratedValue
	private Long orderDetailId;
	private Double quantity;
	private Double itemPrice;
	private Double subTotal;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	private Long productId;
	private String productName;

	public OrderDetails(Double quantity, double itemPrice, Long productId){
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.productId = productId;
		this.subTotal = quantity * itemPrice;
	}

	public OrderDetails(){

	}

	public OrderDetails(Long orderDetailId, Double quantity, Double itemPrice, Double subTotal, Orders order, Long productId, String productName) {
		this.orderDetailId = orderDetailId;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.subTotal = subTotal;
		this.order = order;
		this.productId = productId;
		this.productName = productName;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getSubTotal() {
		return getQuantity() * getItemPrice();
	}

	public void setSubTotal(Double subTotal) {
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
