package edu.miu.groupx.product.productservice.service;

import edu.miu.groupx.product.productservice.models.dtos.ProductDTO;
import edu.miu.groupx.product.productservice.models.dtos.ProductsDTO;

public interface ProductService {

    ProductDTO getProductById(long id);
    ProductsDTO getAllProducts();
    ProductDTO updateProduct(ProductDTO productDTO);
    ProductDTO createProduct(ProductDTO productDTO);
    void deleteProduct(long productId, long vendorId);
    ProductsDTO searchProducts(String keyword, String category);
    ProductsDTO getPendingProducts();
    ProductsDTO getApprovedProducts();
    ProductsDTO getRejectedProducts();
    boolean checkProductOwnership(long productId, long vendorId);


}
