package com.koreait.kod.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;

@Service
public class HeaderCartService {

	@Autowired
	CartService cartService;
	
	public int getCartProductCnt(CartDTO cartDTO) {
		System.out.println("[로그:정현진] HeaderCartService.getCartProductCnt() 들어옴");
		System.out.println("[로그:정현진] 회원ID : "+cartDTO.getMemberID());
		int cartProductCnt=0;
		cartDTO.setSearchCondition("getCartProductCnt");
		cartProductCnt = cartService.selectOne(cartDTO).getCartProductCnt();
		System.out.println("[로그:정현진] 장바구니 상품수량 : "+cartProductCnt);
		return cartProductCnt;
		
	}
	
}
