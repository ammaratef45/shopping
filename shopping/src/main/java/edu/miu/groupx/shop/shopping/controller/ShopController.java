package edu.miu.groupx.shop.shopping.controller;

import edu.miu.groupx.shop.shopping.models.Product;
import edu.miu.groupx.shop.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShopController {
    @Autowired
    private ProductService productService;
    @GetMapping("/hello")
    public ResponseEntity<String> getMessage(){
        return new ResponseEntity<String>("Hello here!", HttpStatus.FOUND);
    }

    @PostMapping("/products")
    ResponseEntity<Product> save(@RequestBody Product product){
         product=productService.save(product);
        return new ResponseEntity<Product>(product,HttpStatus.CREATED);
    }
    @PutMapping("/products")
    ResponseEntity<Product> update(@RequestBody Product product){
        product=productService.update(product);
        return new ResponseEntity<Product>(product,HttpStatus.CREATED);
    }
}
