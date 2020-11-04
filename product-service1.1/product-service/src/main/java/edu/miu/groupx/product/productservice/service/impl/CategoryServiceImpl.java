package edu.miu.groupx.product.productservice.service.impl;

import edu.miu.groupx.product.productservice.models.dtos.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.repository.CategoryRepository;
import edu.miu.groupx.product.productservice.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {

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
    public CategoryDTO getAll() {
        CategoryDTO categoryDTO = new CategoryDTO();
        List<Category> categoryList = this.categoryRepository.findAll();
        List<String> categories = categoryList.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
        categoryDTO.setCategories(categories);
    return categoryDTO;
    }


    @Override
    public Category productCatagoryUpdate(long id, Category newProductCatagory) {
        Category Category = getById(id);
        Category.setName(newProductCatagory.getName());
        return categoryRepository.save(Category);

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
