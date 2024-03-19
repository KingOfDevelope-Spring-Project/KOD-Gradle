package com.koreait.kod.controller.user.productAndWishList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.biz.productAndWishList.WishListDTO;
import com.koreait.kod.biz.productAndWishList.WishListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetStorePage {
	
	@Autowired
	ProductService productService;
	@Autowired
	WishListService wishListService;
	
	// 상품목록페이지 이동
	@GetMapping("/getStorePage")
	public String storePage(@RequestParam("page") String page, ProductDTO productDTO, WishListDTO wishListDTO, Model model, HttpSession session) {
		
		// 상품목록 반환
		wishListDTO.setSearchCondition("storePage");
		List<WishListDTO> isWishedDatas = wishListService.selectAll(wishListDTO);
		model.addAttribute("isWishedDatas", isWishedDatas);
		
		// 위시리스트 상품수량 반환
		String memberID = (String)session.getAttribute("memberID");
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("wishListCnt");
		model.addAttribute("wishListCnt", wishListService.selectAll(wishListDTO));
		
		// 페이징처리
		int productPerPage = 6;
		int currentPage = (page!=null && !page.isEmpty()) ? Integer.parseInt(page) : 1;
		int startIndex = (currentPage - 1) * productPerPage;
		int endIndex = Math.min(startIndex + productPerPage, isWishedDatas.size());
		List<WishListDTO> currentPageProducts = isWishedDatas.subList(startIndex, endIndex);
		currentPageProducts = new ArrayList<WishListDTO>(currentPageProducts);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", (int) Math.ceil((double) isWishedDatas.size() / productPerPage));
		model.addAttribute("currentPageProducts", currentPageProducts);
		
		// 상품카테고리
		productDTO.setSearchCondition("category");
		model.addAttribute("productCategoryDatas", productService.selectAll(productDTO));
		
		// 상품판매 top3 반환
		productDTO.setSearchCondition("top3");
		model.addAttribute("orderRankDatas", productService.selectAll(productDTO));
		
			
		return "user/store";
	}
}
