package com.poly.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.ItemDAO;
import com.poly.dao.OrdersDAO;
import com.poly.entity.Item;
import com.poly.entity.Orders;
import com.poly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrdersDAO ordersDAO;

	@Autowired
	ItemDAO itemDAO;

	@Override
	public Orders create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		Orders order = mapper.convertValue(orderData, Orders.class);
		ordersDAO.save(order);

		TypeReference<List<Item>> type = new TypeReference<List<Item>>() {
		};
		List<Item> details = mapper.convertValue(orderData.get("items"), type).stream().peek(d -> d.setOrder(order))
				.collect(Collectors.toList());
		itemDAO.saveAll(details);
		
		return order;
	}

	@Override
	public List<Orders> findAllActive() {
		return ordersDAO.findAllActive();
	}

	@Override
	public List<Orders> findAllNoActive() {
		return ordersDAO.findAllNoActive();
	}
	
	public Orders getOrdersById(Integer id) {
		return ordersDAO.findById(id).get();
	}

	@Override
	public Orders save(Orders order) {
		return ordersDAO.save(order);
	}

	@Override
	public List<Orders> getAllOrdersByUserid(Integer userid ) {
		return ordersDAO.getAllOrdersByUserid(userid);
	}

	@Override
	public Orders findById(Integer id) {
		return ordersDAO.findById(id).get();
	}

	@Override
	public List<Orders> findByUserName(Integer id) {
		return ordersDAO.findByUserId(id);
	}

	@Override
	public List<Object[]> findDistinctItemsWithDoanhThu() {
		return ordersDAO.findDistinctItemsWithDoanhThu();
	}

}
