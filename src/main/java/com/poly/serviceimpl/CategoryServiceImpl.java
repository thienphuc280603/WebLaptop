package com.poly.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.CategoryDAO;
import com.poly.entity.Category;
import com.poly.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO categoryDAO;

	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public Category findById(Integer id) {
		return categoryDAO.findById(id).get();
	}

	@Override
	public Category create(Category category) {
		return null;
	}

	@Override
	public Category update(Category category) {
		return null;
	}

	@Override
	public void delete(String id) {	
	}

}
