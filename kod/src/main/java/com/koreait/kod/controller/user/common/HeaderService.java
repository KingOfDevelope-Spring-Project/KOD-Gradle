package com.koreait.kod.controller.user.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;
import com.koreait.kod.biz.productAndWishList.WishListDTO;
import com.koreait.kod.biz.productAndWishList.WishListService;

import jakarta.servlet.http.HttpSession;

@Service
public class HeaderService {
	
	@Autowired
	private WishListService wishListService;
	@Autowired
	private CartService cartService;
	
	public void getHeaderPage(WishListDTO wishListDTO,CartDTO cartDTO,Model model,HttpSession session) {

		wishListDTO.setMemberID((String)session.getAttribute("memberID"));
		wishListDTO.setSearchCondition("wishListCnt");
		model.addAttribute("wishListCnt",wishListService.selectOne(wishListDTO));
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		cartDTO.setSearchCondition("cartCnt");
		model.addAttribute("cartCnt", cartService.selectOne(cartDTO));
		
	}
}
