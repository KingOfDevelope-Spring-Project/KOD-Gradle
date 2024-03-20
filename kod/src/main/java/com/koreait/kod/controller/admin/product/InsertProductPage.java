package com.koreait.kod.controller.admin.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InsertProductPage {

	@GetMapping("/insertProductPage")
	public String insertProductPage() {
		
		
		return "admin/product/insertProduct";
	}
}
