package com.poly.service;

import java.util.List;

import com.poly.entity.Users;

public interface UserService {
	public List<Users> getAllUsers();

    public Users getUserById(Integer id);
   
    public Users saveUser(Users user);      
   
    public Users findUserByUserName(String username);
    
    List<Users> findListActive();

    Users save(Users user);
}
