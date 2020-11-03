package edu.miu.groupx.product.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.models.*;
import edu.miu.groupx.product.productservice.service.ProductWarehouseService;
import edu.miu.groupx.product.productservice.repository.*;

@Service
@Transactional
public class ProductWarehouseServiceImpl implements ProductWarehouseService {

	@Autowired
	ProductWarehouseRepository ProductWarehouseRepository;

	@Override
	public List<ProductWarehouse> getAllWarehouses() {

		return ProductWarehouseRepository.findAll();
	}
	
	public List<Product> getAllProductsInWarehouse(long id) {

		return ProductWarehouseRepository.findById(id).get().getProducts();
	}

	@Override
	public Long approveProductById(Long id) {
		ProductWarehouse pw = ProductWarehouseRepository.getOne(id);
		pw.setStatus(ProductStatus.APPROVED);
		return ProductWarehouseRepository.save(pw).getId();
	}

	@Override
	public Long rejectProductById(Long id) {
		ProductWarehouse pw = ProductWarehouseRepository.getOne(id);
		pw.setStatus(ProductStatus.REJECTED);
		return ProductWarehouseRepository.save(pw).getId();
	}

	@Override
	public ProductWarehouse getWarehouseById(long id) {

		return ProductWarehouseRepository.findById(id).get();
	}

	@Override
	public ProductWarehouse save(ProductWarehouse productWarehouse) {

		return ProductWarehouseRepository.save(productWarehouse);
	}

	@Override
	public Product removeProductFromWarehouse(Product product) {

		return null;
	}

	@Override
	public List<Product> removeProductFromWarehouseById(long warehouseID, Product product, int numberOfProducts) {
		ProductWarehouse productWarehouse = ProductWarehouseRepository.findById(warehouseID).get();
		boolean result=productWarehouse.reduceProduct(product, numberOfProducts);
		if(result) {
		ProductWarehouseRepository.save(productWarehouse);
		}
		
		return productWarehouse.getProducts();
	}

}
