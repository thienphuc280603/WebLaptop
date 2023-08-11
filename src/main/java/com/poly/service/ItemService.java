package com.poly.service;

import java.util.List;

import com.poly.entity.Item;

public interface ItemService {
	List<Item> getListItemByOrderID(Integer id);
}
