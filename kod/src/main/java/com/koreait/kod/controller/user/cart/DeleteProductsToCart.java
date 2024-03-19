package com.koreait.kod.controller.user.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DeleteProductsToCart {
	@Autowired
	private CartService cartService;
	@Autowired
	ObjectMapper objectMapper;
	
	@PostMapping("/deleteAllProductsToCart")
	public String deleteAllProductToCart(CartDTO cartDTO,HttpSession session) {
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		cartDTO.setSearchCondition("deleteAllProductToCart");
		cartService.delete(cartDTO);
		
		return "user/mypage/cart";
	}
	
	@PostMapping("/deleteSelectProductsToCart")
	public String deleteSelectProductsToCart(CartDTO cartDTO,HttpSession session) {
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		cartDTO.setSearchCondition("deleteSelectProductsToCart");
		cartService.delete(cartDTO);
		
		return "user/mypage/cart";
	}
}
