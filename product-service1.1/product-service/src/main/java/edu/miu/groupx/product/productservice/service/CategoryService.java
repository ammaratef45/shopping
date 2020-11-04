package edu.miu.groupx.product.productservice.service;

import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.dtos.CategoryDTO;

public interface CategoryService {
	
	Category save(Category category);
	
	Category getByName(String name);
	Category getById(long id); 
	CategoryDTO getAll();
	
	Category productCatagoryUpdate(long id, Category category);
	   	   
	void  deleteById(long id);
	void delete(Category category);

}
