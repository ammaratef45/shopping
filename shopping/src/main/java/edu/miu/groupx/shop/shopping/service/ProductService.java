package edu.miu.groupx.shop.shopping.service;

import edu.miu.groupx.shop.shopping.models.Product;

public interface ProductService {
    public Product findProductById(Long id);

    Product save(Product product);

    Product update(Product product);
}
