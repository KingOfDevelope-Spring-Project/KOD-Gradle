package com.koreait.kod.controller.admin.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

@Controller
public class InsertProductPage {

	@GetMapping("/insertProductPage")
	@LoginCheck(checkRole = Role.ADMIN)
	public String insertProductPage() {
		
		
		return "admin/product/insertProduct";
	}
}
