package com.koreait.kod.controller.user.productAndWishList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.biz.productAndWishList.WishListDTO;
import com.koreait.kod.biz.productAndWishList.WishListService;
import com.koreait.kod.controller.util.HeaderCartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetMainPage {
	
	@Autowired
	ProductService productService;
	@Autowired
	WishListService wishListService;
	@Autowired
	HeaderCartService headerCartService;
	
	// 메인페이지 이동 
	@GetMapping("/") // 루트페이지로 설정 => value="/"
	public String getMainPage(ProductDTO productDTO, WishListDTO wishListDTO,CartDTO cartDTO, Model model, HttpSession session) {
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		model.addAttribute("cartProductCnt", headerCartService.getCartProductCnt(cartDTO));
		// call by reference -> cartDTO 
		
		System.out.println("[로그:정현진] getMainPage요청들어옴");
		
		// 회원정보 가져오기
		String memberID=null;
		try {
			memberID = ((String)session.getAttribute("memberID"));
		} catch (Exception e) {
			System.out.println("[로그:정현진] memberID is null");
		}
		System.out.println("[로그:정현진] 로그인정보");
		if(memberID==null) { // 로그아웃상태, 인기상품목록 반환
			System.out.println("[로그:정현진] 로그아웃상태 들어옴");
			wishListDTO.setSearchCondition("popularAllProductsLogout");
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
		System.out.println("[로그:정현진] 상품목록 반환 종료");
		System.out.println("[로그:정현진] 연령별 인기상품 반환 시작");
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
		System.out.println("[로그:정현진] 연령별 인기상품 반환 종료");
		
		
		return "user/main";
	}
}
