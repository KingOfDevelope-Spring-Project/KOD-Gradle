package com.koreait.kod.controller.user.productAndWishList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.model.member.MemberDTO;
import com.koreait.kod.model.productAndWishlist.ProductService;
import com.koreait.kod.model.productAndWishlist.WishListDTO;
import com.koreait.kod.model.productAndWishlist.WishListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsyncAddOrRemoveWishList {
	
	@Autowired
	ProductService productService;
	@Autowired
	WishListService wishListService;
	
	// 위시리스트 추가 또는 삭제 기능
	@RequestMapping(value="/asyncAddOrRemoveWishList",method = RequestMethod.POST)
	public @ResponseBody String asyncAddOrRemoveWishList(@ModelAttribute("wishList")WishListDTO wishListDTO,Model model,HttpSession session) {
		
		// 회원아이디 가져오기
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		wishListDTO.setMemberID(memberID);
		
		// 위시리스트 추가 또는 삭제
		wishListDTO.setSearchCondition("asyncAddOrRemoveWishList");
		wishListDTO = wishListService.selectOne(wishListDTO);
		if(wishListDTO==null) {
			wishListService.insert(wishListDTO);
		}
		else {
			wishListService.delete(wishListDTO);
		}
		
		// 위시리스트 상품수량 반환
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("wishListCnt");
		wishListDTO = wishListService.selectOne(wishListDTO);
		model.addAttribute("updatedWishListCnt", wishListDTO.getWishListCnt());
		
		return "wishList";
	}
}
