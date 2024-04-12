package com.koreait.kod.controller.user.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsyncUpdateProductCntToCart {
	@Autowired
	private CartService cartService;
	@Autowired
	ObjectMapper objectMapper;
	
	@PostMapping("/asyncUpdateProductCntToCart")
	public @ResponseBody String UpdateProductCntToCart(CartDTO cartDTO, Model model, HttpSession session) throws JsonProcessingException {
			
	    try {
			cartDTO.setMemberID((String) session.getAttribute("memberID"));
		} catch (Exception e) {
			System.out.println("[로그:정현진] memberID is null");
		}
		
		cartDTO.setSearchCondition("asyncUpdateProductCntToCart");
		model.addAttribute("UpdatedProductCnt", cartService.update(cartDTO));
		
		boolean flag = cartService.update(cartDTO);
		if(!flag) {
			System.out.println("[로그:정현진] 장바구니 상품추가 실패 ");
		}
		System.out.println("[로그:정현진] 장바구니 상품추가 성공 ");
		
		return objectMapper.writeValueAsString(cartService.selectOne(cartDTO).getCartProductCnt());
	}
}
