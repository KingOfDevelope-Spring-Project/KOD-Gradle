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
import com.koreait.kod.biz.productAndWishList.ProductService;

@Controller
public class GetKakaoPayPage {

	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	
	@GetMapping("/getKakaoPayPage")
	public String getKakaoPayPage(@RequestParam("payCk") int payCk,
								  @RequestParam("productID") List<Integer> selectedProducts,
								  @RequestParam("cartProductCnt") List<Integer> cartProductCnt,
								  @RequestParam("totalPrice") int totalPrice,
								  Model model) {
		System.out.println("[로그:정현진] getKakaoPayPage 들어옴");

		if(selectedProducts == null || selectedProducts.size()==0) { // 구매할 상품들이 없다면
			return "common/error";
		}
		
		List<CartDTO> productDatas = new ArrayList<CartDTO>();
		for (int i = 0; i < selectedProducts.size(); i++) {
			CartDTO data = new CartDTO();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setSearchCondition("getProductName");
			productDTO.setProductID(selectedProducts.get(i));
			productDTO = productService.selectOne(productDTO); // 상품ID & 상품명 반환
			data.setProductID(productDTO.getProductID());
			data.setProductName(productDTO.getProductName());
			data.setCartProductCnt(cartProductCnt.get(i));
			System.out.println("[로그:정현진] data : [상품ID] : "+data.getProductID()+"\t[상품명] : "+data.getProductName()+"\t[상품수량] : "+data.getCartProductCnt());
			productDatas.add(data);
		}
		
		System.out.println("[로그:정현진] productDatas : "+productDatas);
		System.out.println("[로그:정현진] totalPrice : "+totalPrice);
//		System.out.println("[로그:정현진] payCk : "+payCk);
		
		model.addAttribute("productDatas", productDatas); // 상품ID, 상품명, 상품수량 반환
		model.addAttribute("totalPrice", totalPrice); // 결제금액 합계
//		model.addAttribute("payCk", payCk); // 구매유형(바로구매, 장바구니 구매)
		
		

//		if(productList == null || productList.size()==0) { // 구매할 상품들이 없다면
//		return "common/error";
//		}
	
//		List<CartDTO> productDatas = new ArrayList<CartDTO>();
//		for (int product : SelectedProducts) {
//			CartDTO data = new CartDTO();
//			data.setProductID(product);
//			data=cartService.selectOne(data);
//			productDatas.add(data);
//		}
//		for (CartDTO cartDTO : productDatas) {
//			System.out.println("[로그:정현진] cartDTO : "+cartDTO);
//		}
//		model.addAttribute("productDatas", productDatas);
		
		
		
		return "user/pay/kakaoPay";
	}
	
}
