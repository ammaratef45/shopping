package edu.miu.groupx.product.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductWarehouse;
import edu.miu.groupx.product.productservice.service.ProductWarehouseService;

@RestController
@RequestMapping("/api")
public class ProductWarehouseController {

	@Autowired
	ProductWarehouseService productWarehouseService;

	@GetMapping("/Warehouses")
	public List<ProductWarehouse> getWarehouses() {

		return productWarehouseService.getAllWarehouses();
	}

	@PostMapping("Warehouse/save")
	public ProductWarehouse addWarehouse(@RequestBody ProductWarehouse productWarehouse) {

		return productWarehouseService.save(productWarehouse);
	}
	
	@PostMapping("Warehouses/{id}/product/save")
	public ProductWarehouse addProductInWarehouse(@RequestBody Product product, @PathVariable Long id) {

		return null;
	}
	
	@GetMapping("Warehouses/{id}/product/{id}")
	public ProductWarehouse getAllProductInWarehouse(@RequestBody Product product, @PathVariable Long id) {

		return null;
	}

	@GetMapping("Warehouses/{id}")
	ProductWarehouse getWarehouse(@PathVariable Long id) {
		return productWarehouseService.getWarehouseById(id);
	}

	
	  @PutMapping("Warehouse/{id}")
	 public  ProductWarehouse UpdateWarehouse(@RequestBody ProductWarehouse newProductWarehouse, @PathVariable Long id) {
	  
	  return null;
	}
	 

	@DeleteMapping("ProductWarehouses/{id}")
	void deleteWarehouse(@PathVariable Long id) {
	}

	@PutMapping("/warehouse/{id}/approve")
	public Long approveProductById(@PathVariable Long id) {
		return productWarehouseService.approveProductById(id);
	}

	@PutMapping("/warehouse/{id}/reject")
	public Long rejectProductById(@PathVariable Long id) {
		return productWarehouseService.rejectProductById(id);
	}
}
