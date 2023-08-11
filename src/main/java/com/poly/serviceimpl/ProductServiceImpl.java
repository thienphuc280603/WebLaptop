package com.poly.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.constain.PageableNumberConstain;
import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	int pageNumberMin = PageableNumberConstain.PAGE_MIN;
	int pageNumberMax = PageableNumberConstain.PAGE_MAX;
	Pageable pageable = PageRequest.of(pageNumberMin, pageNumberMax);

	
	@Override
	public List<Product> findTop4ProductsByCategoriesId3() {
		return productDAO.findTop4ProductsByCategoriesId3(pageable);
	}

	@Override
	public List<Product> findTop4ProductsByCategoriesId1() {
		return productDAO.findTop4ProductsByCategoriesId1(pageable);
	}

	@Override
	public List<Product> findTop4ProductsByCategoriesId2() {
		return productDAO.findTop4ProductsByCategoriesId2(pageable);
	}

	@Override
	public Product getProductById(Integer id) {
		return productDAO.getProductById(id);
	}

	@Override
	public List<Product> getProductByBrand(String brand) {
		return productDAO.getProductByBrand(brand, pageable);
	}

	@Override
	public Page<Product> findAllActive(Pageable pageable) {
		return productDAO.findAllActive(pageable);
	}

	@Override
	public Page<Product> findPriceDesc(Pageable pageable) {
		return productDAO.findPriceDesc(pageable);
	}

	@Override
	public Page<Product> findPriceAsc(Pageable pageable) {
		return productDAO.findPriceAsc(pageable);
	}

	@Override
	public Page<Product> findNameProductA_Z(Pageable pageable) {
		return productDAO.findNameProductA_Z(pageable);
	}

	@Override
	public Page<Product> findProductCategories(String category, Pageable pageable) {
		return productDAO.findProductCategories(category, pageable);
	}

	@Override
	public Page<Product> searchByKeyword(String keyword, Pageable pageable) {
		return productDAO.searchByKeyword(keyword, pageable);
	}

	@Override
	public List<Product> findListActive() {
		return productDAO.findListActive();
	}

	@Override
	public Product save(Product product) {
		return productDAO.save(product);
	}

	@Override
	public List<Object[]> findTop4ProductsInRange() {
		return productDAO.findTop4ProductsInRange(pageable);
	}



}
