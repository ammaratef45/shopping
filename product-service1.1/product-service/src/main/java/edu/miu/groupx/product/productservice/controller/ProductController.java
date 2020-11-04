package edu.miu.groupx.product.productservice.controller;

import java.util.List;


import edu.miu.groupx.product.productservice.models.ProductList;
import edu.miu.groupx.product.productservice.models.dtos.ProductDTO;
import edu.miu.groupx.product.productservice.models.dtos.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.ProductService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products/{id}")
    ProductDTO getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/products")
    public ProductsDTO getProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/products")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO responseProductDTO = this.productService.updateProduct(productDTO);
        if(responseProductDTO == null) throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, "ownership check failed"
        );
        else return responseProductDTO;
    }

    @PostMapping("/products")
    public ProductDTO createProduct( @RequestBody ProductDTO productDTO) {
        System.out.println("here");
        return this.productService.createProduct(productDTO, productDTO.getVendorId());
    }

    @DeleteMapping("/products")
    public void deleteProduct(@RequestParam("productId") long productId, @RequestParam("vendorId") long vendorId) {
        this.productService.deleteProduct(productId, vendorId);
    }

    @GetMapping("/products/search")
    public ProductsDTO searchProducts(@RequestParam("keyword") String keyword, @RequestParam("category") String category) {
       System.out.println(keyword+" "+category);
        return productService.searchProducts(keyword, category);
    }

    @GetMapping("/products/pending")
    public ProductsDTO getPendingProducts() {
        return productService.getPendingProducts();
    }

    @GetMapping("/products/approved")
    public ProductsDTO getApprovedProducts() {
        return productService.getApprovedProducts();
    }

    @GetMapping("/products/rejected")
    public ProductsDTO getRejectedProducts() {
        return productService.getRejectedProducts();
    }

}
