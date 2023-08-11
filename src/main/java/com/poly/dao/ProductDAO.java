package com.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
//	 @Query("SELECT p FROM Product p WHERE p.price BETWEEN 27000000 AND 30000000 ORDER BY p.price")
	@Query(value = "select p.Productid, p.Image1, p.Productname, p.Processor, p.RAM, p.VGA, p.Other, p.Price, sum(i.quantity) \r\n"
			+ "from Product p, Item i\r\n"
			+ "where p.Productid = i.Productid\r\n"
			+ "group by p.Productid, p.Image1, p.Productname, p.Processor, p.RAM, p.VGA, p.Other, p.Price\r\n"
			+ "order by sum(i.quantity) desc", nativeQuery = true)
	List<Object[]> findTop4ProductsInRange(Pageable pageable);


	@Query("SELECT p FROM Product p WHERE p.categoriesid.id = 3 ORDER BY p.price")
	List<Product> findTop4ProductsByCategoriesId3(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.categoriesid.id = 1 ORDER BY p.price")
	List<Product> findTop4ProductsByCategoriesId1(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.categoriesid.id = 2 ORDER BY p.price")
	List<Product> findTop4ProductsByCategoriesId2(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.productid = :id")
	Product getProductById(@Param("id") Integer id);

	@Query("SELECT p FROM Product p WHERE p.brand = :brand ORDER BY p.price")
	List<Product> getProductByBrand(@Param("brand") String brand, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.active = true")
	Page<Product> findAllActive(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.price DESC")
	Page<Product> findPriceDesc(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.price ASC")
	Page<Product> findPriceAsc(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.productname ASC")
	Page<Product> findNameProductA_Z(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.categoriesid.Namecategories =  :category")
	Page<Product> findProductCategories(@Param("category") String category, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE LOWER(p.productname) LIKE %:keyword% and p.active = true")
	Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.active = true")
	List<Product> findListActive();
}
