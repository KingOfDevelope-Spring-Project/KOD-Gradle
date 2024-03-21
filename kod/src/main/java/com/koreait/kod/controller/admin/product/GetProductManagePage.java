package com.koreait.kod.controller.admin.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetProductManagePage {

	@GetMapping("/getProductManagePage")
	public String getProductManagePage() {
		
		
		return "admin/product/productManage";
	}
}
