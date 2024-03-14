package com.koreait.kod.controller.user.productAndWishList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.model.productAndWishlist.WishListDTO;
import com.koreait.kod.model.productAndWishlist.WishListService;

public class AsyncSoldOutProductRemove {
	
	@Autowired
	WishListService wishlistService;
	
	// 품절상품 삭제 기능
	@RequestMapping(value="/asyncSoldOutProductRemove",method = RequestMethod.POST)
	public @ResponseBody String asyncSoldOutProductRemove(@ModelAttribute("wishlist")WishListDTO wishlistDTO,Model model) {
		wishlistDTO.setSearchCondition("wishTotalCnt");
		wishlistDTO=wishlistService.selectOne(wishlistDTO);
		model.addAttribute("wishTotalCnt", wishlistDTO.getWishTotalCnt());
		
		return "wishlist";
	}
}
