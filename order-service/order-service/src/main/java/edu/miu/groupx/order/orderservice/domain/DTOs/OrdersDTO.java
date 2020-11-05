package edu.miu.groupx.order.orderservice.domain.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrdersDTO {

    List<OrderDTO> orders = new ArrayList<>();
}
