package edu.miu.groupx.product.productservice.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Entity
@Data
public class ProductWarehouse {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "warehouse_id")
	private List<Product> products;

	public ProductWarehouse() {
		
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public void addProduct(Product product) {
		
		this.quantity++;
		products.add(product);
	}
	
	public boolean reduceProduct(Product product, int quantityBought) {
		boolean productSave=false;
		if(this.products.contains(product)) {
			productSave= this.products.remove(product);
			this.quantity=quantity-quantityBought;
			return productSave;
			
		}
		
		return productSave;
		
		
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
