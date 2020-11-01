package edu.miu.groupx.product.productservice.service;

import java.util.List;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductWarehouse;

public interface ProductWarehouseService {

	List<ProductWarehouse> getAllWarehouses();
	ProductWarehouse getWarehouseById(long id);
	ProductWarehouse save(ProductWarehouse productWarehouse);
	List<Product> removeProductFromWarehouseById(long warehouseID, Product product, int numberOfProducts);
	Product removeProductFromWarehouse(Product product);
	List<Product> getAllProductsInWarehouse(long id);

	Long approveProductById(Long id);

	Long rejectProductById(Long id);
}
