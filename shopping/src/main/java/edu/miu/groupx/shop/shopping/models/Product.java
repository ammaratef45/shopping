package edu.miu.groupx.shop.shopping.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Integer version;
    private String name;
    private String productNumber;
    private BigDecimal price;

    ///others
}
