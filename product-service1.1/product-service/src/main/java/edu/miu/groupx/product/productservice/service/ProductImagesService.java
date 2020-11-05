package edu.miu.groupx.product.productservice.service;

import edu.miu.groupx.product.productservice.models.dtos.ImageResponse;

public interface ProductImagesService {


	ImageResponse getProductImages(long productId);

	void deleteProductImage(long productId, long vendorId, String url);

	void saveProductImage(long productId, String url);
}
