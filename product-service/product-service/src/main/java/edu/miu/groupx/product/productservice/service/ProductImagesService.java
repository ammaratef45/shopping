package edu.miu.groupx.product.productservice.service;

import java.util.List;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductImages;
import edu.miu.groupx.product.productservice.models.Category;

public interface ProductImagesService {
	List<ProductImages> getAllProductImages();
	ProductImages getProductImagesById(long id);
	ProductImages saveProductImage(ProductImages image,Long id);
	List<ProductImages> saveProductImages(List<ProductImages> imagesList, Long id);
   

}
