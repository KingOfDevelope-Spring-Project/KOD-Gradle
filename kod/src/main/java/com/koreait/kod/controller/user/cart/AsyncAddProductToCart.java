package com.koreait.kod.controller.user.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsyncAddProductToCart {

	@Autowired
	private CartService cartService;
	
	//@ResponseBody" 어노테이션은 컨트롤러 메소드가 HTTP 응답의 본문으로 데이터를 반환할 때 사용
	@PostMapping("/asyncAddProductToCart")
	public @ResponseBody String addProductToCart(CartDTO cartDTO, HttpSession session) {
		
	    try {
			cartDTO.setMemberID((String) session.getAttribute("memberID"));
		} catch (Exception e) {
			System.out.println("[로그:정현진] memberID is null");
		}
	    
	    CartDTO cartDataOfMember = cartService.selectOne(cartDTO);

	    boolean flag;
	    if (cartDataOfMember.getProductID() == cartDTO.getProductID()) {
	        cartDTO.setSearchCondition("sameProductExistToCart");
	        flag = cartService.update(cartDTO);
	    } 
	    else {
	        flag = cartService.insert(cartDTO);
	    }
	    
	    return String.valueOf(flag);
	}
}

