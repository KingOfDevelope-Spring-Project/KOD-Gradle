package com.koreait.kod.controller.admin.product;

import org.springframework.web.bind.annotation.GetMapping;

public class GetCategoryManagePage {

	@GetMapping("/getCategoryManagePage")
	public String getCategoryManagePage() {
		
		return "admin/product/categoryManagePage";
	}
}
