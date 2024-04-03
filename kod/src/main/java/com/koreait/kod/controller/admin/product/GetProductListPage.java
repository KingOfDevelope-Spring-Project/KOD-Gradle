package com.koreait.kod.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.productAndWishList.CategoryDTO;
import com.koreait.kod.biz.productAndWishList.CategoryService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetProductListPage {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/getProductListPage")
	public String getProductDatas(ProductDTO productDTO,CategoryDTO categoryDTO,Model model,HttpSession session) {
		
		// 등록된 상품목록 반환
		productDTO.setSearchCondition("allProductsDatas");
		model.addAttribute("productsDatas", productService.selectAll(productDTO));
		
		// 카테고리 목록 반환
		model.addAttribute("categoryDatas", categoryService.selectAll(categoryDTO));
		
		return "admin/product/productList";
	}
}
