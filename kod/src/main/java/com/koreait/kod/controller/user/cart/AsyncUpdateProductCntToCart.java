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
		
//		cartDTO.setSearchCondition("asyncUpdateProductCntToCart");
//		model.addAttribute("UpdatedProductCnt", cartService.update(cartDTO));
		
	    System.out.println("[로그:정현진] 회원ID : " + cartDTO.getMemberID());
	    System.out.println("[로그:정현진] 상품ID : " + cartDTO.getProductID());
	    System.out.println("[로그:정현진] 상품수량 : " + cartDTO.getCartProductCnt());
		boolean flag = cartService.update(cartDTO);
		System.out.println("[로그:정현진] flag : "+flag);
		if(!flag) {
			System.out.println("[로그:정현진] 장바구니 상품수량 변경 실패 ");
		}
		else{
			System.out.println("[로그:정현진] 장바구니 상품수량 변경 성공 ");
		}
		
		return objectMapper.writeValueAsString(cartService.selectOne(cartDTO).getCartProductCnt());
	}
}
