package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Users;

public interface UsersDAO extends JpaRepository<Users, Integer> {
	@Query(value = "select * from Users u where u.Username = ?1", nativeQuery = true)
	Users findUserByUserName(String username);

	@Query("SELECT u FROM Users u WHERE u.active = true")
	List<Users> findListActive();
}
