package com.koreait.kod.controller.user.productAndWishList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.biz.productAndWishList.WishListDTO;
import com.koreait.kod.biz.productAndWishList.WishListService;

import jakarta.servlet.http.HttpSession;

public class GetMainPage {
	
	@Autowired
	ProductService productService;
	@Autowired
	WishListService wishListService;
	
	// 메인페이지 이동
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public String mainPage(@RequestParam("page") String page, ProductDTO productDTO, WishListDTO wishListDTO, Model model, HttpSession session) {
		
		// 회원정보 가져오기
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		if(memberID==null) { // 로그아웃상태, 인기상품목록 반환
			wishListDTO.setSearchCondition("popularAllItemsLogout");
			model.addAttribute("popularAllItems", wishListService.selectAll(wishListDTO));
		}
		else { // 로그인상태, 인기상품목록 반환
			wishListDTO.setMemberID(memberID);
			wishListDTO.setSearchCondition("popularAllItemsLogin");
			model.addAttribute("popularAllItems", wishListService.selectAll(wishListDTO));
			
			// 위시리스트 상품수량 반환
			wishListDTO.setSearchCondition("wishListCnt");
			model.addAttribute("wishListCnt", wishListService.selectAll(wishListDTO));
		}
		
		// 연령별 인기상품 반환
		wishListDTO.setMemberMinAge(10);
		wishListDTO.setMemberMaxAge(20);
		wishListDTO.setSearchCondition("productWishRankingByAge"); // 10대
		model.addAttribute("teenagerRanking", wishListService.selectAll(wishListDTO));
		
		wishListDTO.setMemberMinAge(20);
		wishListDTO.setMemberMaxAge(30);
		wishListDTO.setSearchCondition("productWishRankingByAge"); // 20대
		model.addAttribute("twentyRanking", wishListService.selectAll(wishListDTO));

		wishListDTO.setMemberMinAge(30);
		wishListDTO.setMemberMaxAge(40);
		wishListDTO.setSearchCondition("productWishRankingByAge"); // 30대
		model.addAttribute("thirtyRanking", wishListService.selectAll(wishListDTO));
		
		return "main";
	}
}
