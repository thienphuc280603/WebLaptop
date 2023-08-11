package com.poly.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.UsersDAO;
import com.poly.entity.Users;
import com.poly.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UsersDAO usersDAO;

	@Override
	public List<Users> getAllUsers() {
		return usersDAO.findAll();
	}

	@Override
	public Users getUserById(Integer id) {
		return usersDAO.findById(id).get();
	}

	@Override
	public Users saveUser(Users user) {
		return usersDAO.save(user);
	}

	@Override
	public Users findUserByUserName(String username) {
		return usersDAO.findUserByUserName(username);
	}
	
	//ĐĂNG KÍ
    public Users registerUser(Users registrationDto) {
        Users user = new Users();
        user.setFullname(registrationDto.getFullname());
        user.setPasswords(registrationDto.getPasswords());
        user.setUsername(registrationDto.getUsername());
        user.setActive(true);
        user.setPhone(registrationDto.getPhone());
        user.setEmail(registrationDto.getEmail());
        return usersDAO.save(user);
    }
    
    //ĐỔI MẬT KHẨU
    public boolean changePassword(String username, String password, String newPassword) {
        Users user = usersDAO.findUserByUserName(username);
        if (user != null && user.getPasswords().equals(password)) {
            user.setPasswords(newPassword);
            usersDAO.save(user);
            return true;
        }
        return false;
    }
    
    //MẬT KHẨU MỚI
    public void saveNewPassword(String username, String newPassword) {
        Users user = usersDAO.findUserByUserName(username);
        if (user != null) {
            user.setPasswords(newPassword);
            usersDAO.save(user);
        } else {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
    }

	@Override
	public List<Users> findListActive() {
		return usersDAO.findListActive();
	}

	@Override
	public Users save(Users user) {
		return usersDAO.save(user);
	}

}
