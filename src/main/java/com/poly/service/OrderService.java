package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Orders;

public interface OrderService {
	public Orders create(JsonNode orderData);
	
	List<Orders> findAllActive();
	
	List<Orders> findAllNoActive();
	
	Orders save(Orders order);
	
	List<Orders> getAllOrdersByUserid(Integer userid);
	
	Orders findById(Integer id);
	
	List<Orders> findByUserName(Integer userid);
	
	List<Object[]> findDistinctItemsWithDoanhThu();
}
