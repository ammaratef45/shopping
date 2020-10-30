package edu.miu.groupx.shop.shopping.service.impl;

import edu.miu.groupx.shop.shopping.exception.ShoppingException;
import edu.miu.groupx.shop.shopping.models.Product;
import edu.miu.groupx.shop.shopping.repository.ProductRepository;
import edu.miu.groupx.shop.shopping.service.ProductService;;
import edu.miu.groupx.shop.shopping.service.SequenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SequenceNumberService sequenceNumberService;
    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ShoppingException("Object not found"));
    }
    @Override
    public Product save(Product product) {
        String productSequence=sequenceNumberService.getNextProductNumber();
        product.setProductNumber(productSequence);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }
}
