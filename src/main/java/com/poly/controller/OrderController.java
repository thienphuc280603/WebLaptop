package com.poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.serviceimpl.OrderServiceImpl;

@Controller
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		Integer userid = Integer.parseInt(request.getRemoteUser());
		model.addAttribute("orderitem", orderService.findByUserName(userid));
		return "home/list";
	}
	
	@RequestMapping("/order/detail{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "home/detailsitem";
	}

}
