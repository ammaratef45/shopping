package edu.miu.groupx.product.productservice.service.impl;

import java.util.List;


import edu.miu.groupx.product.productservice.models.ProductStatus;
import edu.miu.groupx.product.productservice.service.SequenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.ProductWarehouse;
import edu.miu.groupx.product.productservice.repository.CategoryRepository;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.repository.ProductWarehouseRepository;
import edu.miu.groupx.product.productservice.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	 @Autowired
	    ProductRepository productRepository;
	 @Autowired
	 ProductCatagoryRepository productCatagoryRepository;
	@Autowired
	private SequenceNumberService sequenceNumberService;

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductWarehouseRepository ProductWarehouseRepo;

	@Override
	public Product save(Product product) {
		// List<ProductImages> productImages=product.getPictures().g

		Product actualProduct = null;
		Long productCategoryId=product.getProductCatagoryId();
			if(productCategoryId!=null) {
			ProductCatagory productCatagory=productCatagoryRepository.findById(productCategoryId).get();
			//how to set the quantity
				String productSequence=sequenceNumberService.getNextProductNumber();
				product.setProductNumber(productSequence);
			product.setProductCatagory(productCatagory);
			System.out.println(product.getProductCatagory().getId());
			actualProduct=productRepository.save(product);
			productCatagory.setQuantity(productCatagory.getQuantity()+1);
		Long productCategoryId = product.getProductCatagoryId();
		Long productWarehouseId = product.getProductWarehouseId();
		ProductWarehouse productWarehouse = null;
		if (productCategoryId != null) {
			Category category = categoryRepository.findById(productCategoryId).get();
			//System.out.println(product.getName());
			//Set<Category> categoryList= new HashSet();
			//categoryList.add(category);
			product.addCategory(category);
			
			//product.setCategory(categoryList);

		}
		if (productWarehouseId != null) {
			productWarehouse = ProductWarehouseRepo.findById(productWarehouseId).get();

		}
		actualProduct = productRepository.save(product);
		productWarehouse.addProduct(actualProduct);
		ProductWarehouseRepo.save(productWarehouse);

		return actualProduct;

	}

	@Override
	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long id) {

		return productRepository.findById(id).get();

	}

	@Override
	public Product getProductByName(String name) {

		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductByCategory(Category category) {

		return productRepository.getByCategory(category);
	}
	/*
	 * @Override public List<Product> getNewProducts(ProductStatus productStatus) {
	 * 
	 * return productRepository.getNew(productStatus); }
	 * 
	 * @Override public List<Product> getApprovedProducts(ProductStatus
	 * productStatus) {
	 * 
	 * return productRepository.getApproved(productStatus); }
	 * 
	 * @Override public List<Product> getRejectedProducts(ProductStatus
	 * productStatus) {
	 * 
	 * return productRepository.getRejected(productStatus); }
	 */

	@Override
	public Product updateProduct(long id, Product newProduct) {

		Product ActualProduct = getProductById(id);
		ActualProduct.setName(newProduct.getName());
		System.out.println("Before change: "+ActualProduct.getPrice());
		ActualProduct.setPrice(newProduct.getPrice());
		System.out.println("after change: "+ActualProduct.getPrice());
		ActualProduct.setCategory(newProduct.getCategory());
		ActualProduct.setAddedOn(newProduct.getAddedOn());
		// ActualProduct.setStatus(newProduct.getStatus());
		ActualProduct.setDescription(newProduct.getDescription());
		ActualProduct.setImageUrl(newProduct.getImageUrl());

		return productRepository.save(ActualProduct);

	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);

	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);

	}

	@Override
	public List<Product> search(String keyword) {

		return productRepository.searchProducts(keyword);
	}

	@Override
	public List<Product> getPendingProducts() {
		return productRepository.getPendingProducts();
	}

	@Override
	public List<Product> getApprovedProducts() {
		return productRepository.getApprovedProducts();
	}

	@Override
	public List<Product> getRejectedProducts() {
		return productRepository.getRejectedProducts();
	}
}
