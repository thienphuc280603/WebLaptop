package com.poly.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Orders;
import com.poly.service.OrderService;
import com.poly.serviceimpl.OrderServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
	@Autowired
	OrderServiceImpl orderService;
	
	
	@PostMapping
	public Orders create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}

}
