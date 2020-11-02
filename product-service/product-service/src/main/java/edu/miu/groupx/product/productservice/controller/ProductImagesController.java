package edu.miu.groupx.product.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.ProductImages;
import edu.miu.groupx.product.productservice.service.CategoryService;
import edu.miu.groupx.product.productservice.service.ProductImagesService;

@RestController
@RequestMapping("/api")
public class ProductImagesController {

	@Autowired
	ProductImagesService productImagesService;

	@GetMapping("/ProductImages")
	public List<ProductImages> getCategories() {

		
		return productImagesService.getAllProductImages();
	}

	@PostMapping("ProductImages/products/{id}/save")
	public ProductImages addCategory(@RequestBody ProductImages productImages, @PathVariable Long id) {

		//missing implementation
		return productImagesService.saveProductImage(productImages,id);
	}

	@GetMapping("ProductImages/{id}")
	ProductImages getCategory(@PathVariable Long id) {
		//missing implementation
		return null;
	}

	
	  @PutMapping("ProductImages/{id}")
	 public  ProductImages Category(@RequestBody Category newCategry, @PathVariable Long id) {
	  //missing implementation
	  return null;
	}
	 

	@DeleteMapping("ProductImages/{id}")
	void deleteCategory(@PathVariable Long id) {
		
		//missing implementation
	}

}
