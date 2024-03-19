package com.koreait.kod.controller.user.productAndWishList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsyncFilterController {
	
	@Autowired
	ProductService productService;
	@Autowired
	ObjectMapper objectMapper;

	@PostMapping("/asyncFilterProducts")
	public @ResponseBody String categoryProductList(
				@RequestParam("maxPrice") int maxPrice, 
				@RequestParam("minPrice") int minPrice, 
				@RequestParam("categoryList") String categoryList, 
				ProductDTO productDTO,
				Model model,
				HttpSession session) throws JsonProcessingException {
		
		final int PRICE = 10000;
		
		productDTO.setMemberID((String)session.getAttribute("memberID"));
		
		categoryList = categoryList.replace("[", "");
		categoryList = categoryList.replace("]", "");
		categoryList = categoryList.replace("\"", "");
		String[] ar = categoryList.split(",");
		productDTO.setProductCategoryList(ar);
		productDTO.setProductMaxPrice(maxPrice*PRICE);
		productDTO.setProductMinPrice(minPrice*PRICE);
		productDTO.setSearchCondition("filter");
		
		
		return objectMapper.writeValueAsString(productService.selectAll(productDTO));
	}
}
