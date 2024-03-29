package com.koreait.kod.controller.user.orderAndPay;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;

@Controller
public class GetKakaoPayPage {

	@Autowired
	CartService cartService;
	
	@GetMapping("/getKakaoPayPage")
	public String getKakaoPayPage(@RequestParam("payCk") int payCK,
								  @RequestParam("productID") List<Integer> SelectedProducts,
								  List<ProductDTO> productList,
								  Model model) {
		System.out.println("[로그:정현진] getKakaoPayPage 들어옴");

		if(productList == null || productList.size()==0) { // 구매할 상품들이 없다면
			return "common/error";
		}
		
		List<CartDTO> productDatas = new ArrayList<CartDTO>();
		for (int product : SelectedProducts) {
			CartDTO data = new CartDTO();
			data.setProductID(product);
			data=cartService.selectOne(data);
			productDatas.add(data);
		}
		for (CartDTO cartDTO : productDatas) {
			System.out.println("[로그:정현진] cartDTO : "+cartDTO);
		}
		model.addAttribute("productDatas", productDatas);
		
		
		
		return "user/pay/kakaoPay";
	}
	
}
