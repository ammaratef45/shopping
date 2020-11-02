package edu.miu.groupx.product.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductWarehouse;

;

@Repository
public interface ProductWarehouseRepository extends JpaRepository<ProductWarehouse, Long> {
	

	//ProductWarehouse findByName(String productName);
	

}
