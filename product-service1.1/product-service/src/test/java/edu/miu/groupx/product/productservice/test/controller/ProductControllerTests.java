
package edu.miu.groupx.product.productservice.test.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import edu.miu.groupx.product.productservice.controller.ProductController;
import edu.miu.groupx.product.productservice.models.dtos.ProductDTO;
import edu.miu.groupx.product.productservice.models.dtos.ProductsDTO;
import edu.miu.groupx.product.productservice.service.ProductService;


@SpringBootTest
public class ProductControllerTests {
	
	@InjectMocks
	private ProductController subject;
	
	@Mock
	private ProductService productServiceMock;
	
	private ProductDTO product;
	private long productId;
	private ProductDTO savedProduct;
	
	private ProductsDTO productsDTO;
	
	@Test
	public void getProduct_returnProduct() {
		
		productId = 1L;
		
		product = ProductDTO.builder()
				.id(productId)
				.name("productName")
				.price(1000)
				.description("descriptionString")
				.imageUrl(Collections.singletonList("imageUrl"))
				.category("stringCategory")
				.quantity(5)
				.vendorId(2L)
				.build();
		
		when(productServiceMock.getProductById(productId)).thenReturn(product);
		
		ProductDTO actual = subject.getProduct(productId);
		
		verify(productServiceMock).getProductById(productId);
		assertThat(actual).isEqualTo(product);
		
	}
	
	
	@Test
	public void getProducts_returnProductsDTO() {
		
		product = ProductDTO.builder()
				.id(productId)
				.name("productName")
				.price(1000)
				.description("descriptionString")
				.imageUrl(Collections.singletonList("imageUrl"))
				.category("stringCategory")
				.quantity(5)
				.vendorId(2L)
				.build();
		
		
		productsDTO = new ProductsDTO();
		productsDTO.setProducts(Collections.singletonList(product));
		
		when(productServiceMock.getAllProducts()).thenReturn(productsDTO);
		
		ProductsDTO actual = subject.getProducts();
		
		verify(productServiceMock).getAllProducts();
		assertThat(actual).isEqualTo(productsDTO);
		
	}
	
	@Test
	public void createProduct_returnSavedProduct() {
		
		productId = 1L;
		
		product = ProductDTO.builder()
				
				.name("productName")
				.price(1000)
				.description("descriptionString")
				.imageUrl(Collections.singletonList("imageUrl"))
				.category("stringCategory")
				.quantity(5)
				.vendorId(2L)
				.build();
		
		savedProduct= product;
		savedProduct.setId(productId);
		
		when(productServiceMock.createProduct(product, product.getVendorId())).thenReturn(savedProduct);
		
		ProductDTO actual = subject.createProduct(product);
		
		verify(productServiceMock).createProduct(product, product.getVendorId());
		assertThat(actual).isEqualTo(product);
		
	}
	
	

	

}
