package com.koreait.kod.controller.user.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;
import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.controller.util.HeaderCartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetCartPage {
	
	@Autowired
	CartService cartService;
	@Autowired
	HeaderCartService headerCartService;

	@GetMapping("/getCartPage")
	public String getCartPage(CartDTO cartDTO,MemberDTO memberDTO,Model model,HttpSession session) {
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		model.addAttribute("cartDatas", cartService.selectAll(cartDTO));
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		model.addAttribute("cartProductCnt",headerCartService.getCartProductCnt(cartDTO));
		
		return "user/mypage/cart";
	}
}
