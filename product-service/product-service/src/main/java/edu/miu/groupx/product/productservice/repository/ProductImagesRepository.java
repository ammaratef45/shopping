package edu.miu.groupx.product.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductImages;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {

   ProductImages getProductImagesByImagePth(String url);
}
