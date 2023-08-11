package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.Item;

public interface ItemDAO extends JpaRepository<Item, Integer> {
	@Query("SELECT i FROM Item i WHERE i.order.id = :id")
    List<Item> getListItemByOrderID(@Param("id") Integer id);
}
