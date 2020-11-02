package edu.miu.groupx.product.productservice.service;

public interface ProductImagesService {

	String saveProductImage(long productId, String url);

	void deleteProductImage(long productId, long vendorId, String url);
}
