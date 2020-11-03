package edu.miu.groupx.product.productservice.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
public class ProductImages implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String imagePth;

    public ProductImages() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePth() {
        return imagePth;
    }

    public void setImagePth(String imagePth) {
        this.imagePth = imagePth;
    }


}
