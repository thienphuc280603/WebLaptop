package com.poly.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.poly.entity.Users;
import com.poly.serviceimpl.EmailServiceImpl;
import com.poly.serviceimpl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	EmailServiceImpl emailService;
	
	//đăng nhập
	@PostMapping("/api/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes
    		, HttpSession session, Model model) {
        Users user = userService.findUserByUserName(username);
        if (user == null || !password.equals(user.getPasswords())) {
        	model.addAttribute("message", "Username hoặc password sai!");
            return "redirect:/home/login";
        } else {
        	session.setAttribute("UserID", user.getUserid());
        	session.setAttribute("loggedInUser", user.getUsername());
            // Đăng nhập thành công, trả về trang chính hoặc trang bất kỳ
            return "redirect:/home/listproduct"; // Thay đổi đường dẫn tới trang chính của ứng dụng của bạn
        }
        
        
    }
	//đăng kí
	@PostMapping("/api/register")
    public String registerUser(@ModelAttribute("userRegistrationDto") Users registrationDto, Model model) {
        try {
            Users newUser = userService.registerUser(registrationDto);

            // Gửi email chào mừng đến người dùng sau khi đăng ký thành công.
            emailService.sendWelcomeEmail(newUser.getEmail());
        } catch (IllegalArgumentException ex) {
            // Xử lý nếu có lỗi xảy ra khi đăng ký.
        }
        return "/home/login";
    }
	
	//change pass
	@PostMapping("/api/change-password")
    public String changePassword(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("newPassword") String newPassword,
                                 Model model) {
        try {
            boolean isPasswordChanged = userService.changePassword(username, password, newPassword);
            if (isPasswordChanged) {
                model.addAttribute("message", "Đổi mật khẩu thành công");
            } else {
                model.addAttribute("errorMessage", "*Tên đăng nhập hoặc mật khẩu không chính xác");
                return "/home/changepass";
            }
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", "Lỗi đổi mật khẩu: " + ex.getMessage());
        }
        return "/home/changepass";
    }
	
	
	//fogotpass
	@PostMapping("/api/send-email")
    public String sendEmail( @RequestParam("username") String username,
                            @RequestParam("email") String email,
                            Model model) {
		

		// Kiểm tra xem người dùng có tồn tại hay không
	    Users user = userService.findUserByUserName(username);
	    if (user == null) {
	        model.addAttribute("message", "Username sai!");
	        return "home/fogotpass";
	    }
	    
        String newPassword = emailService.generateRandomPassword();
        // Lưu mật khẩu mới vào database cho người dùng
        userService.saveNewPassword(username, newPassword);

        // Gửi email chào mừng đến người dùng với mật khẩu mới
        emailService.sendEmailFogotPass(username, email, newPassword);

        model.addAttribute("message", "Đã gửi email. Vui lòng kiểm tra hộp thư đến của bạn.");

        return "home/changepass";
    }
	
	
	

}
