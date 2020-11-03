package edu.miu.groupx.product.productservice.repository;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.groupx.product.productservice.models.dtos.ProductsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.ProductStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Product findByName(String productName);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.description) LIKE %:keyword%")
     List<Product> searchProductsByKeyword(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.description) LIKE %?1% AND p.category.id = ?2")
    List<Product> searchProductsByKeywordAndCategory(@Param("keyword") String keyword, @Param("category") long category);

    List<Product> getProductsByProductWarehouseStatus(ProductStatus productStatus);

    /*
     * @Query(value = "select p from Product p  where p.status = :status ")
     * List<Product> getNew(@Param(value = "status") ProductStatus productStatus);
     *
     * @Query(value = "select p from Product p  where p.status = :status ")
     * List<Product> getApproved(@Param(value = "status") ProductStatus
     * productStatus);
     *
     * @Query(value = "select p from Product p  where p.status = :status ")
     * List<Product> getRejected(@Param(value = "status") ProductStatus
     * productStatus);
     */
/*
	@Query(value = "select p from Product p  where p.category = :category ")
	List<Product> getByCategory(@Param(value = "category") Category category);
*/
    /*
     *
     * )

     */
/*
	@Query("SELECT p from  Product p where p.name like '%'||:keyword||'%' or p.Price like cast(:keyword as double) or addedOn like '%'||:keyword||'%'")
	List<Product> searchProducts(@Param(value = "keyword") String keyword);

//	SELECT p.id, p.price, p.name, w.quantity as stockAmount from Product p join product_warehouse w on p.warehouse_id=w.id where p.user_id=1 order by p.id
	@Query("SELECT p from Product p where p.user.id= :id order by p.id")
	List<Product> findRProductsByVendorId(@Param("id") Long id);

	@JsonIgnore
	@Query("SELECT p,w from Product p, ProductWarehouse w where w.id=p.id AND w.status='NEW'")
	List<Product> getPendingProducts();

	@JsonIgnore
	@Query("SELECT p,w from Product p, ProductWarehouse w where w.id=p.id AND w.status='APPROVED'")
	List<Product> getApprovedProducts();

	@JsonIgnore
	@Query("SELECT p,w from Product p, ProductWarehouse w where w.id=p.id AND w.status='REJECTED'")
	List<Product> getRejectedProducts();
	*/

}
