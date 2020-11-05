package edu.miu.groupx.order.orderservice.domain.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Setter
@Getter
public class OrderDTO {
    private long id;
    private double total;
    private Date date;
    List<ProductDTO> products = new ArrayList<>();
}
