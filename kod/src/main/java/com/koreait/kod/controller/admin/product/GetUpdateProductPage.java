package com.koreait.kod.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetUpdateProductPage {

	@Autowired
	ProductService productService;
	
	@GetMapping("/getUpdateProductPage")
	public String getProductData(ProductDTO productDTO, Model model, HttpSession session) {
		
		productDTO.setSearchCondition("getProductData");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		
		return "admin/product/productUpdate";
	}
}
