package com.koreait.kod.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.productAndWishList.CategoryDTO;
import com.koreait.kod.biz.productAndWishList.CategoryService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

@Controller
public class InsertCategory {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/insertCategory")
	@LoginCheck(checkRole = Role.ADMIN)
	public String insertCategory(CategoryDTO categoryDTO, Model model) {
		
		categoryService.insert(categoryDTO);
		model.addAttribute("categoryDatas", categoryService.selectAll(categoryDTO));
		
		return "redirect:/getCategoryManagePage";
	}
}
