package edu.miu.groupx.product.productservice.models;


public enum ESequenceType {

	ORDER("Order"),
	PAYMENT("Payment"),
	USER("User"),
	PRODUCT("Product"),
	COUPON("Coupon");
	private String status;
	

	private ESequenceType(String status) {
		this.status=status;
	}

	public String getStatus() {
		return status;
	}
	

}
