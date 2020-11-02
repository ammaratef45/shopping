package edu.miu.groupx.product.productservice.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Builder
@Entity
public class ProductWarehouse {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;


    @Enumerated(EnumType.STRING)
    private ProductStatus status ;

    public ProductWarehouse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ProductWarehouse(Long id, int quantity, ProductStatus status) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
    }
}
