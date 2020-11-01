package edu.miu.groupx.shop.shopping.controller;

import edu.miu.groupx.shop.shopping.models.Product;
import edu.miu.groupx.shop.shopping.models.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
     private RestTemplate restTemplate;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductList(){
        ProductList productList= restTemplate.getForObject("http://PRODUCT-SERVICE/api/productList",ProductList.class);
        return new ResponseEntity<List<Product>>(productList.getProductList(),HttpStatus.FOUND);
    }
    @GetMapping("/search/productList/any")
    public ResponseEntity<List<Product>> searchProductList(@RequestParam("keyword") String keyword){
        ProductList productList= restTemplate.getForObject("http://PRODUCT-SERVICE/api/search/productList/any"+keyword,ProductList.class);
        return new ResponseEntity<List<Product>>(productList.getProductList(),HttpStatus.FOUND);
    }
}
