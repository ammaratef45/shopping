package edu.miu.groupx.product.productservice.models;

import java.io.Serializable;
import java.util.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

import lombok.Data;

@Component
@Entity
@Data
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private Integer version;
	@Column(unique = true)
	private String name;
	@NotNull
	private String description;
	@NotNull
	private double Price;
	
	private String imageUrl;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<ProductImages> pictures ;

	private Date addedOn;
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable
	private Set<Category> category= new HashSet();
	@Transient
	private Long productCatagoryId;
	@Transient
	private Long productWarehouseId;
	
	@ManyToOne
	private User user;
	
		
	public Product() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<ProductImages> getPictures() {
		return pictures;
	}
	public void setPictures(List<ProductImages> pictures) {
		this.pictures = pictures;
	}
	public Date getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	
	public Long getProductCatagoryId() {
		return productCatagoryId;
	}
	public void setProductCatagoryId(Long productCatagoryId) {
		this.productCatagoryId = productCatagoryId;
	}
	public Long getProductWarehouseId() {
		return productWarehouseId;
	}
	public void setProductWarehouseId(Long productWarehouseId) {
		this.productWarehouseId = productWarehouseId;
	}
	public Set<Category> getCategory() {
		return category;
	}
	public void setCategory(Set<Category> category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void addCategory(Category category) {
		this.category.add(category);
	}
	
	public void addImage(ProductImages productImages) {
		this.pictures.add(productImages);
	}
	

}
