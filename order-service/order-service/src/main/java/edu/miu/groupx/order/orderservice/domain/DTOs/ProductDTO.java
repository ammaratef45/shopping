package edu.miu.groupx.order.orderservice.domain.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductDTO {
    private String productName;
    private double price;
    private int quantity;
    private double subtotal;
}
