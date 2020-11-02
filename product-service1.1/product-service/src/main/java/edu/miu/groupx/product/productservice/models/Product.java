package edu.miu.groupx.product.productservice.models;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
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
    private Long id;

    @Version
    private Integer version;

    @Column(unique = true)
    private String name;

    @NotNull
    private String description;

    @NotNull
    private double price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval= true)
    @JoinColumn(name = "product_id")
    private List<ProductImages> pictures = new ArrayList<>() ;


    @NotNull
    private long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category ;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductWarehouse productWarehouse;

    public Product() {
    }

    public Product(Long id, Integer version, String name, String description, double price, List<ProductImages> pictures, long userId, Category category, ProductWarehouse productWarehouse) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictures = pictures;
        this.userId = userId;
        this.category = category;
        this.productWarehouse = productWarehouse;
    }

}
