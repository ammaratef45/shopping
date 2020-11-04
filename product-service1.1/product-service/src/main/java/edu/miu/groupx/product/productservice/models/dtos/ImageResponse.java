package edu.miu.groupx.product.productservice.models.dtos;


import lombok.Setter;

import java.util.List;


public class ImageResponse {
    private List<String> imageUrl ;

    public void setImageUrl(List<String> imageUrls) {
        this.imageUrl = imageUrls;
    }
}
