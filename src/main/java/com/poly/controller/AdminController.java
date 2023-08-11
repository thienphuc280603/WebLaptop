package com.poly.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.OrdersDAO;
import com.poly.entity.Authority;
import com.poly.entity.Category;
import com.poly.entity.Item;
import com.poly.entity.Orders;
import com.poly.entity.Product;
import com.poly.entity.Role;
import com.poly.entity.Users;
import com.poly.serviceimpl.CategoryServiceImpl;
import com.poly.serviceimpl.ItemServiceImpl;
import com.poly.serviceimpl.OrderServiceImpl;
import com.poly.serviceimpl.ProductServiceImpl;
import com.poly.serviceimpl.UploadServiceImpl;
import com.poly.serviceimpl.UserServiceImpl;


@CrossOrigin("*")
@Controller
public class AdminController {

	@Autowired
	ProductServiceImpl productService;

	@Autowired
	UserServiceImpl userService;

	@Autowired
	CategoryServiceImpl categoryService;

	@Autowired
	OrderServiceImpl orderService;

	@Autowired
	ItemServiceImpl ItemService;

	@Autowired
	UploadServiceImpl uploadService;
	

	
	
	@RequestMapping("home/admin/admin")
	public String getAdminHome(Model model) {
		List<Orders> listOrderActive = orderService.findAllActive();
		List<Orders> listOrderNoActive = orderService.findAllNoActive();
		model.addAttribute("listOrderActive",listOrderActive);
		model.addAttribute("listOrderNoActive",listOrderNoActive);
		return "admin/admin";
	}
	
//	@RequestMapping("/admin/ok")
//	public String getOrderSuccess(Model model, @RequestParam("id") Integer id, @RequestParam("active") String active) {
//		Orders order = orDao.findById(id).get();
//		if(active.equals("false")) {
//			order.setActive(false);
//		}else {
//			order.setActive(true);
//		}
//		orDao.save(order);
//		List<Orders> listOrderActive = orDao.findAllActive();
//		List<Orders> listOrderNoActive = orDao.findAllNoActive();
//		model.addAttribute("listOrderActive",listOrderActive);
//		model.addAttribute("listOrderNoActive",listOrderNoActive);
//		return "admin/admin";
//	}
	
	@RequestMapping("/admin/order")
	public String getDetailOrder(Model model, @RequestParam("id") Integer id) {
		Orders order = orderService.getOrdersById(id);
		List<Item> listItemOrder = ItemService.getListItemByOrderID(id);
		model.addAttribute("order",order);
		model.addAttribute("listItemOrder",listItemOrder);
		
		return "admin/detailorder";
	}
	
	@RequestMapping("/admin/ok")
	public String getOrderSuccess(Model model, @RequestParam("id") Integer id, @RequestParam("active") String active) {
		Orders order = orderService.getOrdersById(id);
		if(active.equals("false")) {
			order.setActive(false);
		}else {
			order.setActive(true);
		}
		orderService.save(order);
		List<Orders> listOrderActive = orderService.findAllActive();
		List<Orders> listOrderNoActive = orderService.findAllNoActive();
		model.addAttribute("listOrderActive",listOrderActive);
		model.addAttribute("listOrderNoActive",listOrderNoActive);
		return "admin/admin";
	}
	
	@RequestMapping("/admin/product-view")
	public String getProductView(Model model) {
		List<Product> listPro = productService.findListActive();
		model.addAttribute("listPro",listPro);
		return "admin/product-view";
	}
	
	@RequestMapping("/admin/product")
	public String getProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product",product);
		return "admin/product";
	}
	@RequestMapping("/admin/product/edit")
	public String getProductEdit(Model model,@RequestParam("id") Integer id) {
		Product product = productService.getProductById(id);
		model.addAttribute("product",product);
		return "admin/product";
	}
	
	@RequestMapping("/admin/product/action/delete")
	public String getDeleteProduct(Model model,@RequestParam("id") Integer id) {
		Product product = productService.getProductById(id);
		product.setActive(false);
		productService.save(product);
		model.addAttribute("message","Đã xoá thành công sản phẩm " + product.getProductname());
		
		Product productNew = new Product();
		model.addAttribute("product",productNew);
		return "admin/product";
	}
	
	@RequestMapping("/admin/product/action/save")
	public String getSaveProduct(
	        @RequestParam(name="imagea", required = false) MultipartFile file1,
	        @RequestParam(name="imageb", required = false) MultipartFile file2,
	        @RequestParam(name="imagec", required = false) MultipartFile file3,
	        @RequestParam(name="imaged", required = false) MultipartFile file4,
	        @ModelAttribute("product") Product product,
	        Model model
			) {
		try {
			product.setActive(true);
			product.setImage1(file1.getOriginalFilename());
			product.setImage2(file2.getOriginalFilename());
			product.setImage3(file3.getOriginalFilename());
			product.setImage4(file4.getOriginalFilename());
			uploadService.save(file1, "laptop");
			uploadService.save(file2, "laptop");
			uploadService.save(file3, "laptop");
			uploadService.save(file4, "laptop");
			System.out.println("luu product thanh cong ");
			
			productService.save(product);
			
			Product productNew = new Product();
			model.addAttribute("product",productNew);
			return "admin/product";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("luu product that bai ");
			return "admin/product";
		}
	}
	
	@RequestMapping("/admin/listuser")
	public String abcdef(Model model) {
		List<Users> listUser = userService.findListActive();
		model.addAttribute("listUser",listUser);
		return "admin/listuser";
	}

	@RequestMapping("/admin/listuser/delete")
	public String getDeleteUser(Model model,@RequestParam("id" ) Integer id) {
		Users user = userService.getUserById(id);
		user.setActive(false);
		userService.save(user);
		
		List<Users> listUser = userService.findListActive();
		model.addAttribute("listUser",listUser);
		return "admin/listuser";
	}
	
	@RequestMapping("/admin/doanhthu")
	public String abcdefg(Model model) {
		List<Object[]> distinctItemsWithDoanhThu = orderService.findDistinctItemsWithDoanhThu();
        model.addAttribute("itemsWithDoanhThu", distinctItemsWithDoanhThu);
        for (Object[] objects : distinctItemsWithDoanhThu) {
			System.out.print(objects);
		}
		return "admin/doanhthu";
	}
	@ModelAttribute("listCategory")
	public List<Category> listCate(){
		return categoryService.findAll();
	}
	@RequestMapping("/admin/product/action/update")
	public String getUpdateProduct(@ModelAttribute("product") Product product,
			@RequestParam(name = "imagea", required = false) MultipartFile file1,
			@RequestParam(name = "imageb", required = false) MultipartFile file2,
			@RequestParam(name = "imagec", required = false) MultipartFile file3,
			@RequestParam(name = "imaged", required = false) MultipartFile file4,
			@RequestParam("id") Integer id,
			 Model model) {

		Product proImage = productService.getProductById(id);
		System.out.println(proImage.getImage1());
		Product productUpdate = productService.getProductById(id);
		productUpdate.setProductname(product.getProductname());
		productUpdate.setPrice(product.getPrice());
		productUpdate.setBrand(product.getBrand());
		productUpdate.setPromotion(product.getPromotion());
		productUpdate.setProcessor(product.getProcessor());
		productUpdate.setRAM(product.getRAM());
		productUpdate.setVGA(product.getVGA());
		productUpdate.setMass(product.getMass());
		productUpdate.setOther(product.getOther());
		productUpdate.setDescriptions(product.getDescriptions());
		productUpdate.setCategoriesid(product.getCategoriesid());
		if (file1 != null) {
			try {
				uploadService.save(file1, "laptop");
				productUpdate.setImage1(file1.getOriginalFilename());
			}catch (Exception e) {
				System.out.println("cap nhat product that bai ");
				productUpdate.setImage1(proImage.getImage1());
			}

		}
		if (file2 != null) {
			try {
				uploadService.save(file2, "laptop");
				productUpdate.setImage2(file2.getOriginalFilename());
			}catch (Exception e) {
				System.out.println("cap nhat product that bai ");
			}
		}
		if (file3 != null) {
			try {
				uploadService.save(file3, "laptop");
				productUpdate.setImage3(file3.getOriginalFilename());
			}catch (Exception e) {
				System.out.println("cap nhat product that bai ");
			}
		}
		if (file4 != null) {
			try {
				uploadService.save(file4, "laptop");
				productUpdate.setImage4(file4.getOriginalFilename());
			}catch (Exception e) {
				System.out.println("cap nhat product that bai ");
			}
		}
		productService.save(productUpdate);
		System.out.println("cap nhat product thanh cong ");
		System.out.println(file1);
		List<Product> listPro = productService.findListActive();
		model.addAttribute("listPro", listPro);
		return "admin/product-view";
	}
	
	
}
