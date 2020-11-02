package edu.miu.groupx.product.productservice.models.dtos;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@Builder
public class ProductDTO {

    private long id;
    private String name;
    private double price;
    private String description;
    private List<String> imageUrl;
    private String category;
    private int quantity;
    private long vendorId;


}
