package com.koreait.kod.controller.user.productAndWishList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.model.member.MemberDTO;
import com.koreait.kod.model.productAndWishlist.ProductDTO;
import com.koreait.kod.model.productAndWishlist.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsyncFilterController {
	
	ProductService productService;

	@RequestMapping(value = "/asyncFilterProductList",method = RequestMethod.POST)
	public @ResponseBody String CategoryProductList(@RequestParam("maxPrice") int maxPrice, @RequestParam("minPrice") int minPrice, @RequestParam("categoryList") String categoryList, ProductDTO productDTO,Model model,/*Gson gson,*/HttpSession session) {
		
		final int PRICE = 10000;
		
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		productDTO.setMemberID(memberID);
		
		categoryList = categoryList.replace("[", "");
		categoryList = categoryList.replace("]", "");
		categoryList = categoryList.replace("\"", "");
		String[] ar = categoryList.split(",");
		productDTO.setProductCategoryList(ar);
		productDTO.setProductMaxPrice(maxPrice*PRICE);
		productDTO.setProductMinPrice(minPrice*PRICE);
		productDTO.setSearchCondition("filter");
		
		model.addAttribute("productFilterDatas", productService.selectAll(productDTO));
		
		return "store";
	}
	
	
}
