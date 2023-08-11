package com.poly.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Product;
import com.poly.service.ProductService;
import com.poly.serviceimpl.ProductServiceImpl;


@CrossOrigin("*")
@RestController
@RequestMapping("/home/rest/products")
public class ProductRestController {
	@Autowired
	ProductServiceImpl productService;
	

	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.getProductById(id);
	}}
