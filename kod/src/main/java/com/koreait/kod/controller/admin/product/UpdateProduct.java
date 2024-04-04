package com.koreait.kod.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateProduct {

	@Autowired
	ProductService productService;
	
	@PostMapping("/updateProduct")
	public String updateProduct(ProductDTO productDTO,HttpSession session) {
		
		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
			return "common/error";
		}
		
		productService.update(productDTO);
		
		return "admin/product/productUpdate";
	}
}
