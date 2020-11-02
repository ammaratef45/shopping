package edu.miu.groupx.product.productservice.service;

import java.util.List;

import edu.miu.groupx.product.productservice.models.Category;

public interface CategoryService {
	
	Category save(Category category);
	
	Category getByName(String name);
	Category getById(long id); 
	List<Category> getAll();
	
	Category productCatagoryUpdate(long id, Category category);
	   	   
	void  deleteById(long id);
	void delete(Category category);

}
