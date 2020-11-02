package edu.miu.groupx.product.productservice.models.dtos;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class ProductsDTO {

    private List<ProductDTO> products ;

    public List<ProductDTO> getProducts() {
        return products;
    }
}
