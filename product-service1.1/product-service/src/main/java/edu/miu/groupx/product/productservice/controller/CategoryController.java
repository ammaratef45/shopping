package edu.miu.groupx.product.productservice.controller;

import java.util.List;

import edu.miu.groupx.product.productservice.models.dtos.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.dtos.ProductDTO;
import edu.miu.groupx.product.productservice.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/categories")
	public CategoryDTO getCategories() {
		return categoryService.getAll();
	}

	@PostMapping("productCategory/save")
	public Category addCategory(@RequestBody Category category) {
		Category responseCategory = this.categoryService.save(category);
        if(responseCategory == null) throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, "The category already exists"
        );
        else 
        	return responseCategory;
    
	}

	@GetMapping("productCategories/{id}")
	Category getCategory(@PathVariable Long id) {
		return categoryService.getById(id);
	}

	
	  @PutMapping("productCategories/{id}")
	 public  Category Category(@RequestBody Category newCategry, @PathVariable Long id) {
	  
	  return categoryService.productCatagoryUpdate(id, newCategry); }
	 

	@DeleteMapping("productCategories/{id}")
	void deleteCategory(@PathVariable Long id) {
		categoryService.deleteById(id);
		
	}

}
