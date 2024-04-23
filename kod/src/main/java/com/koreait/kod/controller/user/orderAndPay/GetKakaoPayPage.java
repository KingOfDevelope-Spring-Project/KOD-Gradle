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
import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.biz.coupon.CouponStatusService;
import com.koreait.kod.biz.order.OrderContentService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

@Controller
public class GetKakaoPayPage {

	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	CouponService couponService;
	@Autowired
	CouponStatusService couponstatusService;
	@Autowired
	OrderContentService orderContentService;
	
	@GetMapping("/getKakaoPayPage")
	public String getKakaoPayPage(@RequestParam("payCk") int purchaseType,
								  @RequestParam("paymentProvider") String paymentProvider,
								  @RequestParam("productIDs") List<Integer> productIDs,
								  @RequestParam("productCnts") List<Integer> productCnts,
								  @RequestParam("couponIDs") List<Integer> couponIDs,
								  Model model) {
		System.out.println("[로그:정현진] getKakaoPayPage 들어옴");

		if(productIDs == null || productIDs.size()==0) { // 구매할 상품들이 없다면
			return "common/error";
		}
		
		List<CartDTO> productDatas = new ArrayList<CartDTO>();
		for (int i = 0; i < productIDs.size(); i++) {
			CartDTO data = new CartDTO();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setSearchCondition("getProductName");
			productDTO.setProductID(productIDs.get(i));
			productDTO = productService.selectOne(productDTO); // 상품ID & 상품명 반환
			data.setProductID(productDTO.getProductID());
			data.setProductName(productDTO.getProductName());
			data.setCartProductCnt(productCnts.get(i));
			System.out.println("[로그:정현진] data : [상품ID] : "+data.getProductID()+"\t[상품명] : "+data.getProductName()+"\t[상품수량] : "+data.getCartProductCnt());
			productDatas.add(data);
		}
		
		/*
		 * 결제 금액 계산 로직
		 * 쿠폰ID 값이 0이하일경우 반복문 i+1 -> 다음 반복 진행
		 */
		
		double totalPriceDoubleType = 0;
		for (int i = 0; i < productIDs.size(); i++) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductID(productIDs.get(i));
			productDTO.setSearchCondition("getProductPrice");
			double productPrice = productService.selectOne(productDTO).getProductPrice()*productCnts.get(i); // 상품금액 계산
			System.out.println("[로그:정현진] productPrice * productCnt : "+productPrice); // 2596000
			
			if(couponIDs.get(i)>0) { // 쿠폰을 적용 한 경우
				CouponDTO couponDTO = new CouponDTO();
				couponDTO.setCouponID(couponIDs.get(i));
				couponDTO.setSearchCondition("getCouponDiscountRateAndPrice");
				double discountRate = couponService.selectOne(couponDTO).getCouponDiscountRate(); 
				double discountMaxPrice = couponService.selectOne(couponDTO).getCouponDiscountMaxPrice(); 
				System.out.println("[로그:정현진] discountRate : "+discountRate);// 50
				System.out.println("[로그:정현진] discountMaxPrice : "+discountMaxPrice);// 500,000
				
				if(discountMaxPrice<productPrice*(discountRate/100.0)) { // 최대할인 금액을 넘었을 경우
					productPrice = productPrice-discountMaxPrice; // 상품가격 - 최대할인금액, 2096000
					System.out.println("[로그:정현진] productPrice : "+productPrice);
				}
				else{
					System.out.println("[로그:정현진] 할인할 금액이 최대할인금액을 넘지않음 ");
					productPrice=productPrice-productPrice*(discountRate/100.0);
					System.out.println("[로그:정현진] productPrice : "+productPrice);
				}
			} // if
			totalPriceDoubleType+=productPrice; // 결제금액 계산하기
			System.out.println("[로그:정현진] totalPriceDoubleType : "+totalPriceDoubleType);
		}
		
		int totalPrice = (int)totalPriceDoubleType;
		System.out.println("[로그:정현진] totalPrice : "+totalPrice);
		System.out.println("[로그:정현진] productDatas : "+productDatas);
		System.out.println("[로그:정현진] purchaseType : "+purchaseType);
		System.out.println("[로그:정현진] paymentProvider : "+paymentProvider);
		
		model.addAttribute("productDatas", productDatas); // 상품ID, 상품명, 상품수량 반환
		model.addAttribute("totalPrice", totalPrice); // 결제금액 합계
		model.addAttribute("purchaseType", purchaseType); // 구매유형(바로구매, 장바구니 구매)
		model.addAttribute("paymentProvider", paymentProvider); // 결제제공자 (카카오페이)
		model.addAttribute("couponIDs", couponIDs);
		
		

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
