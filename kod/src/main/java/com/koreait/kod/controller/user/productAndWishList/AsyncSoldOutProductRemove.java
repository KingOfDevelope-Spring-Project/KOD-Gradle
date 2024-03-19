package com.koreait.kod.controller.user.productAndWishList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.biz.productAndWishList.WishListDTO;
import com.koreait.kod.biz.productAndWishList.WishListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsyncSoldOutProductRemove {
	
	@Autowired
	WishListService wishListService;
	
	// 품절상품 삭제 기능
	@PostMapping("/asyncSoldOutProductsRemove")
	public @ResponseBody String asyncSoldOutProductRemove(@RequestBody List<Integer> soldOutProductIDDatas, WishListDTO wishListDTO, Model model, HttpSession session) {

		for (int soldOutProductID : soldOutProductIDDatas) {
			wishListDTO.setMemberID((String) session.getAttribute("memberID")); 
			wishListDTO.setProductID(soldOutProductID);
			if(!wishListService.delete(wishListDTO)) {
				System.out.println("[로그:정현진]"+soldOutProductID+"번 품절상품 삭제 실패");
			}
			System.out.println("[로그:정현진]"+soldOutProductID+"번 품절상품 삭제 성공");
		}
		
	    wishListDTO.setSearchCondition("wishTotalCnt");
	    wishListDTO = wishListService.selectOne(wishListDTO);

	    return String.valueOf(wishListService.selectOne(wishListDTO).getWishListCnt());
	}
}
