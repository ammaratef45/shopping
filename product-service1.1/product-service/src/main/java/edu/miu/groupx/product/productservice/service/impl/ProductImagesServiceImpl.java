package edu.miu.groupx.product.productservice.service.impl;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductImages;
import edu.miu.groupx.product.productservice.models.dtos.ImageResponse;
import edu.miu.groupx.product.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.repository.ProductImagesRepository;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.ProductImagesService;

import java.util.stream.Collectors;

@Service
@Transactional
public class ProductImagesServiceImpl implements ProductImagesService {
    @Autowired
    ProductImagesRepository productImagesRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;


    @Override
    public ImageResponse getProductImages(long productId) {
        Product product = this.productRepository.getOne(productId);
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setImageUrl(product.getPictures()
                .stream()
                .map(ProductImages::getImagePth)
                .collect(Collectors.toList()));
        return imageResponse;
    }
    @Override
    public void saveProductImage(long productId, String url) {
        ProductImages productImages = new ProductImages();
        productImages.setImagePth(url);
        this.productService.addProductImage(productId, url);

    }
    @Override
    public void deleteProductImage(long productId, long vendorId, String url) {
        Product product = this.productRepository.getOne(productId);
        ProductImages productImages = this.productImagesRepository.getProductImagesByImagePth(url);
        product.getPictures().remove(productImages);
        this.productRepository.save(product);
    }
}
