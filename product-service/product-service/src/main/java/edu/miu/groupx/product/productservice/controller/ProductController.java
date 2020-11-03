package edu.miu.groupx.product.productservice.controller;

import java.util.List;


import edu.miu.groupx.product.productservice.models.ProductList;
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

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product/add")
	public Product addProduct(@RequestBody Product product) {

		return productService.save(product);
	}

	@GetMapping("/productList")
	public ProductList getProductList() {
         ProductList productList=new ProductList();
         productList.setProductList(productService.getAllProducts());
		return productList;
	}

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
	@GetMapping("/products/{id}")
	ResponseEntity<?> getProduct(@PathVariable Long id) {
		
		Product product = productService.getProductById(id);
		
		if( product == null ) {
			return new ResponseEntity<String>("No product found with id:"+id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/search/products/any")
	public ResponseEntity<?> searchProducts(@RequestParam("keyword") String keyword) {
		System.out.println(keyword);
		return new ResponseEntity<List<Product>>(productService.search(keyword), HttpStatus.OK);
	}

	@GetMapping("/search/productList/any")
	public ResponseEntity<?> searchProductList(@RequestParam("keyword") String keyword) {
	    ProductList productList=new ProductList();
	    productList.setProductList(productService.search(keyword));
		return new ResponseEntity<ProductList>(productList, HttpStatus.OK);
	}

	@PutMapping("/products/{id}")
	public Product ProductUpdate(@RequestBody Product newProduct, @PathVariable Long id) {

		return productService.updateProduct(id, newProduct);
	}

	@DeleteMapping("products/{id}")
	void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}

	@GetMapping("/products/pending")
	public List<Product> getPendingProducts() {

		return productService.getPendingProducts();
	}

	@GetMapping("/products/approved")
	public List<Product> getApprovedProducts() {

		return productService.getApprovedProducts();
	}

	@GetMapping("/products/rejected")
	public List<Product> getRejectedProducts() {

		return productService.getRejectedProducts();
	}

}
