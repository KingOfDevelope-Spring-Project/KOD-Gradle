package com.koreait.kod.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.productAndWishList.CategoryDTO;
import com.koreait.kod.biz.productAndWishList.CategoryService;

@Controller
public class GetCategoryManagePage {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/getCategoryManagePage")
	public String getCategoryManagePage(CategoryDTO categoryDTO,Model model) {
		
		model.addAttribute("categoryDatas",categoryService.selectAll(categoryDTO));
		
		return "admin/product/categoryManagePage";
	}
}
