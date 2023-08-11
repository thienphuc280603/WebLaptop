package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SercuiryController {
	@RequestMapping("/sercurity/errorPage")
	public String errorPage() {
		return "sercurity/errorPage";
	}
}
