package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Product;

public interface ProductService {
	List<Object[]> findTop4ProductsInRange();

	List<Product> findTop4ProductsByCategoriesId3();

	List<Product> findTop4ProductsByCategoriesId1();

	List<Product> findTop4ProductsByCategoriesId2();

	Product getProductById(Integer id);

	List<Product> getProductByBrand(String brand);

	Page<Product> findAllActive(Pageable pageable);

	Page<Product> findPriceDesc(Pageable pageable);

	Page<Product> findPriceAsc(Pageable pageable);

	Page<Product> findNameProductA_Z(Pageable pageable);

	Page<Product> findProductCategories(String category, Pageable pageable);

	Page<Product> searchByKeyword(String keyword, Pageable pageable);
	
	List<Product> findListActive();
	
	Product save(Product product);
}
