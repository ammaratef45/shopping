package edu.miu.groupx.product.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.miu.groupx.product.productservice.models.dtos.ProductDTO;
import edu.miu.groupx.product.productservice.models.dtos.ProductsDTO;
import edu.miu.groupx.product.productservice.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/products")
    public ProductsDTO getProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/products")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO responseProductDTO = this.productService.updateProduct(productDTO);
        if (responseProductDTO == null) throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, "ownership check failed"
        );
        else return responseProductDTO;
    }

    @PostMapping("/products")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        System.out.println("here");
        return this.productService.createProduct(productDTO, productDTO.getVendorId());
    }

    @DeleteMapping("/products")
    public void deleteProduct(@RequestParam("productId") long productId, @RequestParam("vendorId") long vendorId) {
        this.productService.deleteProduct(productId, vendorId);
    }

    @GetMapping("/products/search")
    public ProductsDTO searchProducts(@RequestParam("keyword") String keyword, @RequestParam("category") String category) {
        System.out.println(keyword + " " + category);
        return productService.searchProducts(keyword, category);
    }

    @GetMapping("/products/pending")
    public ProductsDTO getPendingProducts(@RequestParam("vendorId") long vendorId) {
        return productService.getPendingProducts(vendorId);
    }

    @GetMapping("/products/approved")
    public ProductsDTO getApprovedProducts(@RequestParam("vendorId") long vendorId) {
        return productService.getApprovedProducts(vendorId);
    }

    @GetMapping("/products/rejected")
    public ProductsDTO getRejectedProducts(@RequestParam("vendorId") long vendorId) {
        return productService.getRejectedProducts(vendorId);
    }

}
