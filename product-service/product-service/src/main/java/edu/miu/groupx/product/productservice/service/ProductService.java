package edu.miu.groupx.product.productservice.service;

import java.util.List;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductCatagory;
import edu.miu.groupx.product.productservice.models.ProductStatus;

public interface ProductService {
	Product save(Product product);
	
	List<Product> getAllProducts();
    Product getProductById(long id);
    Product getProductByName(String name);
    List<Product> getProductByCategory(ProductCatagory productCatagory);
    List<Product> getNewProducts(ProductStatus productStatus);
    List<Product> getApprovedProducts(ProductStatus productStatus);
    List<Product> getRejectedProducts(ProductStatus productStatus);
    
    Product updateProduct(long id, Product product);
    void  deleteProduct(Long id);
    void delete(Product product);
   
    List<Product> search(String keyword);
    
}
