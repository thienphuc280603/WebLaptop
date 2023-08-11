package com.poly.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ItemDAO;
import com.poly.entity.Item;
import com.poly.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemDAO itemDAO;

	@Override
	public List<Item> getListItemByOrderID(Integer id) {	
		return itemDAO.getListItemByOrderID(id);
	}

}
