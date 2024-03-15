package com.koreait.kod.controller.user.productAndWishList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.biz.productAndWishList.WishListDTO;
import com.koreait.kod.biz.productAndWishList.WishListService;

public class AsyncSoldOutProductRemove {
	
	@Autowired
	WishListService wishListService;
	
	// 품절상품 삭제 기능
	@RequestMapping(value="/asyncSoldOutProductRemove",method = RequestMethod.POST)
	public @ResponseBody String asyncSoldOutProductRemove(@ModelAttribute("wishList")WishListDTO wishListDTO,Model model) {
		wishListDTO.setSearchCondition("wishTotalCnt");
		wishListDTO=wishListService.selectOne(wishListDTO);
		model.addAttribute("wishTotalCnt", wishListDTO.getWishTotalCnt());
		
		return "wishList";
	}
}
