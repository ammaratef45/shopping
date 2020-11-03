package edu.miu.groupx.product.productservice.service.impl;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.repository.ProductImagesRepository;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.ProductImagesService;

@Service
@Transactional
public class ProductImagesServiceImpl implements ProductImagesService {
	@Autowired
	ProductImagesRepository productImagesRepository;

	@Autowired
	ProductRepository productRepository;


	@Override
	public String saveProductImage(long productId, String url) {
		Product product = this.productRepository.getOne(productId);
		ProductImages productImages = new ProductImages();
		productImages.setImagePth(url);
		product.getPictures().add(productImages);
		this.productRepository.save(product);
		return url;
	}

	@Override
	public void deleteProductImage(long productId, long vendorId, String url) {
		Product product = this.productRepository.getOne(productId);
		ProductImages productImages = this.productImagesRepository.getProductImagesByImagePth(url);
		product.getPictures().remove(productImages);
		this.productRepository.save(product);
	}
}
