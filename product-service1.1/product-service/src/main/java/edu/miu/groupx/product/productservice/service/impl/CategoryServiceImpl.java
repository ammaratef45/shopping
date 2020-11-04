package edu.miu.groupx.product.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.repository.CategoryRepository;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category save(Category category) {
		Category categorycheck=getByName(category.getName());
		if(categorycheck!=null)
			return null;
		return categoryRepository.save(category);
	}

	@Override
	public Category getByName(String name) {
		
		return categoryRepository.findByName(name);
	}

	@Override
	public Category getById(long id) {
		
		return categoryRepository.findById(id).get();
	}

	@Override
	public List<Category> getAll() {
		
		return categoryRepository.findAll();
	}

	
	  @Override 
	  public Category productCatagoryUpdate(long id, Category newProductCatagory) 
	  { 
		  Category Category= getById(id);
		  Category.setName(newProductCatagory.getName());
		  return save(Category);
	  
	  }
	 

	@Override
	public void deleteById(long id) {
		categoryRepository.deleteById(id);
		
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
		
	}

}
