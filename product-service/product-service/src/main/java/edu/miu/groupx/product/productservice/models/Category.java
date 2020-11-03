package edu.miu.groupx.product.productservice.models;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	

	@ManyToMany(mappedBy = "category", cascade = CascadeType.ALL)
	
	private Set<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
	
	  public void addProducts(Product product) {
	  
	  products.add(product); 
	  }
	 

	
	
}
