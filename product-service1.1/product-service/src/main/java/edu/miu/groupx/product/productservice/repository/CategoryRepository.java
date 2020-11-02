package edu.miu.groupx.product.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.product.productservice.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	 
	
	Category findByName(String name);
	
}
