package com.poly.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.poly.entity.Product;
import com.poly.entity.Users;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;
import com.poly.serviceimpl.CategoryServiceImpl;
import com.poly.serviceimpl.ProductServiceImpl;

@Controller
public class HomeController {
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	CategoryServiceImpl categoryService;

	// Gọi trang chủ
	@RequestMapping("/home/index")
	public String Index(Model model) {
		model.addAttribute("cate", categoryService.findAll());
		model.addAttribute("pro", productService.findTop4ProductsInRange());
		model.addAttribute("prodohoa", productService.findTop4ProductsByCategoriesId3());
		model.addAttribute("provanphong", productService.findTop4ProductsByCategoriesId1());
		model.addAttribute("progaming", productService.findTop4ProductsByCategoriesId2());
		return "home/listproduct";
	}

	// Gọi trang chi tiết sản phẩm
	@GetMapping("/home/chitietsp")
	public String Product(Model model, @RequestParam("id") Integer id) {

		Product pro = productService.getProductById(id);
		model.addAttribute("ctsp", pro);
		List<Product> brand1 = productService.getProductByBrand(pro.getBrand());
		model.addAttribute("brand", brand1);
		return "home/details";
	}

	// Gọi trang tất cả sản phẩm
	@GetMapping("/home/allsanpham")
	public String allProduct(Model model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("cate", categoryService.findAll());
		Page<Product> pros = productService.findAllActive(PageRequest.of(page, 12));
		model.addAttribute("pros", pros);
		return "home/collection";
	}

	// Gọi trang tất cả sản phẩm
	@GetMapping("/home/procate")
	public String FindProductCatrgory(Model model, @RequestParam("category") String category,
			@RequestParam(defaultValue = "0") int page) {
		Page<Product> pros = productService.findProductCategories(category, PageRequest.of(page, 12));
		model.addAttribute("pros", pros);
		return "home/collection";
	}

	// Gọi trang tất cả sản phẩm giá giảm dân
	@GetMapping("/home/giagiamdan")
	public String PriceDesc(Model model, @RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
		model.addAttribute("currentPath1", request.getRequestURI());
		Page<Product> pros = productService.findPriceDesc(PageRequest.of(page, 12));
		model.addAttribute("pros", pros);
		return "home/collection";
	}

	// Gọi trang tất cả sản phẩm giá tăng dần
	@GetMapping("/home/giatangdan")
	public String PriceAsc(Model model, @RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
		model.addAttribute("currentPath", request.getRequestURI());
		Page<Product> pros = productService.findPriceAsc(PageRequest.of(page, 12));
		model.addAttribute("pros", pros);
		return "home/collection";
	}

	// Gọi trang tất cả sản phẩm theo tên
	@GetMapping("/home/namea_z")
	public String NameA_Z(Model model, @RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
		model.addAttribute("currentPath2", request.getRequestURI());
		Page<Product> pros = productService.findNameProductA_Z(PageRequest.of(page, 12));
		model.addAttribute("pros", pros);
		return "home/collection";
	}

	// Gọi hàm tìm kiếm
	@GetMapping("/home/search")
	public String SearchByNameProduct(Model model, @RequestParam("keyword") String keyword,
			@RequestParam(defaultValue = "0") int page) {
		Page<Product> pros = productService.searchByKeyword(keyword, PageRequest.of(page, 12));
		model.addAttribute("pros", pros);
		return "home/collection";
	}

	// gọi trang login
	@GetMapping("/user/login")
	public String login() {
		return "home/login";
	}

	// gọi trang đăng kí
	@GetMapping("/user/register")
	public String register(Model model) {
		model.addAttribute("userRegistrationDto", new Users());
		return "home/register";
	}

	// gọi trang quên mật khẩu
	@GetMapping("/user/forgotpass")
	public String forgotPass(Model model) {
		return "home/fogotpass";
	}

	// gọi trang đổi mật khẩu
	@GetMapping("/user/changepass")
	public String changePass(Model model) {
		return "home/changepass";
	}

	@GetMapping("/user/cart")
	public String cart() {
		return "home/cart";
	}

	@GetMapping("/home/logout")
	public String logout(HttpSession session) {
		// Xóa thông tin đăng nhập khỏi session
		session.removeAttribute("UserID");
		session.removeAttribute("loggedInUser");
		// Chuyển hướng về trang login hoặc trang chính của ứng dụng
		return "/home/login"; // Thay đổi đường dẫn tới trang login hoặc trang chính của ứng dụng của bạn
	}
}
