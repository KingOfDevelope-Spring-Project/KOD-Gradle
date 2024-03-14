package com.koreait.kod.controller.user.productAndWishList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.kod.model.member.MemberDTO;
import com.koreait.kod.model.productAndWishlist.ProductDTO;
import com.koreait.kod.model.productAndWishlist.ProductService;
import com.koreait.kod.model.productAndWishlist.WishListDTO;
import com.koreait.kod.model.productAndWishlist.WishListService;

import jakarta.servlet.http.HttpSession;

public class GetWishListPage {
	
	@Autowired
	ProductService productService;
	@Autowired
	WishListService wishListService;
	
	// 위시리스트 페이지 이동
	@RequestMapping(value = "/wishlistPage", method = RequestMethod.GET)
	public String storePage(@RequestParam("page") String page, ProductDTO productDTO, WishListDTO wishListDTO, Model model, HttpSession session) {
		
		// 상품목록 반환
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		wishListDTO.setSearchCondition("wishListByMember");
		List<WishListDTO> wishListDatas = wishListService.selectAll(wishListDTO);
		model.addAttribute("wishListDatas", wishListDatas);
		
		// 상품수량 반환
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("wishListCnt");
		model.addAttribute("wishListCnt", wishListService.selectAll(wishListDTO));
		
		// 페이징처리
		int productPerPage = 6;
		int currentPage = (page!=null && !page.isEmpty()) ? Integer.parseInt(page) : 1;
		int startIndex = (currentPage - 1) * productPerPage;
		int endIndex = Math.min(startIndex + productPerPage, wishListDatas.size());
		List<WishListDTO> currentPageProducts = wishListDatas.subList(startIndex, endIndex);
		currentPageProducts = new ArrayList<WishListDTO>(currentPageProducts);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", (int) Math.ceil((double) wishListDatas.size() / productPerPage));
		model.addAttribute("currentPageProducts", currentPageProducts);
			
		return "store";
	}
}
