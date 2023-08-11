package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Orders;

public interface OrdersDAO extends JpaRepository<Orders, Integer> {
	@Query("SELECT o FROM Orders o WHERE o.active = true")
	List<Orders> findAllActive();

	@Query("SELECT o FROM Orders o WHERE o.active = false")
	List<Orders> findAllNoActive();

	@Query("SELECT o FROM Orders o WHERE o.user.userid = ?1")
	List<Orders> getAllOrdersByUserid(Integer userid);

	@Query("SELECT o FROM Orders o WHERE o.user.userid = ?1")
	List<Orders> findByUserId(Integer userid);

	@Query("SELECT DISTINCT i.product.image1, p.productname, i.quantity, p.price, (i.quantity * p.price) AS doanhthu "
			+ "FROM Item i INNER JOIN i.product p")
	List<Object[]> findDistinctItemsWithDoanhThu();
}
