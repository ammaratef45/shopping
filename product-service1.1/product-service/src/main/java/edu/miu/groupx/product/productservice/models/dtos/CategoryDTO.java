package edu.miu.groupx.product.productservice.models.dtos;

import edu.miu.groupx.product.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryDTO {
    private List<String> categories ;
}
