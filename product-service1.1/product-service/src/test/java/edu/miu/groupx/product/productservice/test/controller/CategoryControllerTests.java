package edu.miu.groupx.product.productservice.test.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import edu.miu.groupx.product.productservice.controller.CategoryController;
import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.dtos.CategoryDTO;
import edu.miu.groupx.product.productservice.models.dtos.ProductDTO;
import edu.miu.groupx.product.productservice.models.dtos.ProductsDTO;
import edu.miu.groupx.product.productservice.service.CategoryService;
import edu.miu.groupx.product.productservice.service.ProductService;


@SpringBootTest
public class CategoryControllerTests {
	
	@InjectMocks
	private CategoryController subject;
	
	@Mock
	private CategoryService categoryServiceMock;
	
	private Category productCategory;
	private long categoryId;
	private Category savedCategory;
	
	private ProductsDTO productsDTO;
	
	@Test
	public void createdCategory_returnSavedCategory() {
		Product product= new Product();
		product.setId(1L);
		product.setName("ProductName");
		categoryId = 1L;
		
		productCategory = new Category();
		productCategory.setName("CategoryName");
		productCategory.setProducts(Collections.singleton(product));		
		
		savedCategory=productCategory;
		savedCategory.setId(2L);
		when(categoryServiceMock.save(productCategory)).thenReturn(savedCategory);
		
		Category actual = subject.addCategory(productCategory);
		
		verify(categoryServiceMock).save(productCategory);
		assertThat(actual).isEqualTo(savedCategory);
		
	}
	
	
	@Test
	public void getProducts_returnProductsDTO() {
		Product product= new Product();
		product.setId(1L);
		product.setName("ProductName");
		categoryId = 1L;
		
		productCategory = new Category();
		productCategory.setName("CategoryName");
		productCategory.setProducts(Collections.singleton(product));
		
		
		CategoryDTO categoryDTO= new CategoryDTO();
		categoryDTO.setCategories(Collections.singletonList(productCategory.getName()));
		
		when(categoryServiceMock.getAll()).thenReturn(categoryDTO);
		
		CategoryDTO actual = subject.getCategories();
		
		verify(categoryServiceMock).getAll();
		assertThat(actual).isEqualTo(categoryDTO);
		
	}

}
