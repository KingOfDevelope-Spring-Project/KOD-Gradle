package com.koreait.kod.controller.user.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;
import com.koreait.kod.biz.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	//@ResponseBody" 어노테이션은 컨트롤러 메소드가 HTTP 응답의 본문으로 데이터를 반환할 때 사용
	@PostMapping("/AddProductToCart")
	public @ResponseBody String addProductToCart(CartDTO cartDTO,HttpSession session) {
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		CartDTO cartDataOfMember = cartService.selectOne(cartDTO);
		
		if(cartDataOfMember.getProductID()==cartDTO.getProductID()) {
			cartDTO.setSearchCondition("sameProductExistToCart");
			cartService.update(cartDTO);
		}
		else {
			cartService.insert(cartDTO);
		}

		return "productDetail";
	}
	
	@PostMapping("asyncUpdateProductCntToCart")
	public @ResponseBody String UpdateProductCntToCart(CartDTO cartDTO, Model model, HttpSession session) {
			
			cartDTO.setMemberID((String)session.getAttribute("memberID"));
			cartDTO.setSearchCondition("asyncUpdateProductCntToCart");
			model.addAttribute("UpdatedProductCnt", cartService.update(cartDTO));
			
			return "cart";
	}
	
	@PostMapping("deleteAllProductsToCart")
	public String deleteAllProductToCart(CartDTO cartDTO,HttpSession session) {
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		cartDTO.setSearchCondition("deleteAllProductToCart");
		cartService.delete(cartDTO);
		
		return "redirect:cart";
	}
	
	@PostMapping("deleteSelectProductsToCart")
	public String deleteSelectProductsToCart(CartDTO cartDTO,HttpSession session) {
		
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		cartDTO.setSearchCondition("deleteSelectProductsToCart");
		cartService.delete(cartDTO);
		
		return "redirect:cart";
	}
	
}
