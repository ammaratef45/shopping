package edu.miu.groupx.product.productservice.models;

import java.io.Serializable;


import java.util.*;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(unique = true)
    private String name;

    @NotNull
    private String description;

    @NotNull
    private double price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductImages> pictures = new ArrayList<>();


    @NotNull
    private long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductWarehouse productWarehouse;

    public Product() {
    }

    public Product(Long id, String name, String description, double price, List<ProductImages> pictures, long userId, Category category, ProductWarehouse productWarehouse) {
        this.id = id;

        this.name = name;
        this.description = description;
        this.price = price;
        this.pictures = pictures;
        this.userId = userId;
        this.category = category;
        this.productWarehouse = productWarehouse;
    }

    public void addImage(ProductImages productImages) {
        this.pictures.add(productImages);
    }
}
