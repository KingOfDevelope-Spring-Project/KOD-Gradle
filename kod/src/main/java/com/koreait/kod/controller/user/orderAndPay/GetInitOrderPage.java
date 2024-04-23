package com.koreait.kod.controller.user.orderAndPay;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;
import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;
import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.controller.util.HeaderCartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetInitOrderPage {  

	@Autowired
	private AddressService addressService;
	@Autowired
	CartService cartService;
	@Autowired
	CouponService couponService;
	@Autowired
	HeaderCartService headerCartService;
	
	/*
	 * 구매로직 한글코딩
	 * 
	 * 1. 사용자가 선택구매를 하는지 바로구매를 하는지 확인이 필요함
	 * 2. 선택구매일 경우
	 * 	2-1. 선택된 상품객체 리스트를 model에 담아 주문정보 컨트롤러로 이동
	 * 3. 즉시구매일 경우
	 * 	3-1. 구매할 상품객체를 model에 담아 주문정보 컨트롤러로 이동 
	 * 
	 * 참고) 상품객체는 상품ID와 구매할 수량이 존재함
	 * 
	 */
	
	
	/* @@@@@질문1
	 * 의문.. 즉시구매일 경우와 선택구매일 경우의 구분이 꼭 필요한가 ?
	 * 하나를 구매하더라도 List에 담아주면 바로구매인지 선택구매인지 구분할 필요가 없어보임
	 */
	
	
//	// 즉시구매할 상품정보를 주문정보페이지로 보내기
//	@GetMapping("/rightNowBuy")
//	public String rightNowBuy(CartDTO CartDTO,Model model) {
//		
//		// CartDTO에는 상품ID와 수량이 들어있음
//		model.addAttribute("productsToBuy",CartDTO);
//		
//		return "redirect:/getOrderInfoPage";
//	}
	
//	// 선택구매할 상품정보를 주문정보페이지로 보내기
//	@GetMapping("/buy")
//	public String buy(List<CartDTO> selectedProducts,Model model) {
//		
//		model.addAttribute("productsToBuy", selectedProducts);
//		
//		return "redirect:/getOrderInfoPage";
//	}

	/* 
	 * 주문정보페이지 로직 한글코딩
	 * 
	 * 1. 주문정보페이지에서는 결제할 상품정보가 DTO인지 리스트인지 확인할수있어야함
	 * 2. 주문하는 사용자 정보로
	 * 	2-1. 보유한 쿠폰정보와 
	 * 	2-2. 배송지정보를 가져와야 함
	 * 
	 */
	
	// 주문정보 페이지
	@GetMapping("/getInitOrderPage") // 주문정보 가져오기
	public String getInitOrderPage(@RequestParam("payCk") int payCK,
								   @RequestParam("selectedProducts") List<Integer> cartSelectedProducts,
						           @RequestParam("cartProductCnt") List<Integer> cartProductCnt,
						           CouponDTO couponDTO,
						           AddressDTO addressDTO,
						           Model model, 
						           HttpSession session) {
		
		System.out.println("[로그:정현진] getInitOrderPage 들어옴");
//	    // model에서 받아온 데이터가 null이라면
//	    if (model.getAttribute("productsToBuy") == null) {
//	    	return "common/error";
//	    }
	    
//	    // 구매할 상품정보 반환하기
//	    if (model.getAttribute("productsToBuy") instanceof CartDTO) {
//            // 즉시구매 일 경우
//            CartDTO CartDTO = (CartDTO) model.getAttribute("productsToBuy");
//            model.addAttribute("productOrderInfo", CartDTO);
//        } 
//        else {
            // 선택구매 일 경우 
        	// @@@@@질문2. new ArrayList<CartDTO>()
        	// ★ 반환타입이 인터페이스인 List인데 괜찮은건지.. ArrayList로 형변환하여 반환해야하는지 .. 고민 ?
//            selectedProducts = (List<CartDTO>) model.getAttribute("productsToBuy");  // 받아온 리스트
		
//            List<CartDTO> productDatas = new ArrayList<CartDTO>(); // 스코프이슈 (반환할 데이터)
//            for (CartDTO product : selectedProducts) {
//            	CartDTO data = new CartDTO();
//            	data.setProductID(product.getProductID());
//            	data = productService.selectOne(product); // 구매수량을 제외한 상품정보가 모두 반환되어야함
//            	data.setProductCnt(product.getProductCnt());
//            	productDatas.add(data);
//			}
//            model.addAttribute("productDatas", productDatas);
//        }
		
		// 
		List<CartDTO> selectedProducts = new ArrayList<CartDTO>();
        for (int i = 0; i < cartSelectedProducts.size(); i++) {
            int productId = cartSelectedProducts.get(i);
            int quantity = cartProductCnt.get(i);
            System.out.println("상품 ID: " + productId + ", 수량: " + quantity+", 구매유형 : "+payCK);
            CartDTO cartDTO = new CartDTO();
            cartDTO.setPayCk(payCK);
            cartDTO.setProductID(productId);
            cartDTO.setCartProductCnt(quantity);
            selectedProducts.add(cartDTO);
        }
//        for (CartDTO data : selectedProducts) {
//			System.out.println("[로그:정현진] data : "+data);
//			System.out.println("[로그:정현진] 구매유형 : "+data.getPayCk());
//		}
		
        List<CartDTO> productDatas = new ArrayList<CartDTO>(); // 스코프이슈 (반환할 데이터)
        for (CartDTO product : selectedProducts) { // selectedProducts에는 상품정보와 구매유형(즉시구매,선택구매)이 담겨있음
        	CartDTO data = new CartDTO();
        	data.setSearchCondition("getProductDatas");
        	data.setMemberID((String)session.getAttribute("memberID"));
        	data.setProductID(product.getProductID());
        	System.out.println("[로그:정현진] data.getProductID : "+data.getProductID());
        	data = cartService.selectOne(data); // 구매수량을 제외한 상품정보가 모두 반환되어야함
        	System.out.println("[로그:정현진] data : "+data);
        	data.setPayCk(product.getPayCk()); // 구매유형 설정
        	productDatas.add(data);
		}
        
//        for (CartDTO cartDTO : productDatas) {
//			System.out.println("[로그:정현진] cartDTO : "+cartDTO);
//		}
        
        model.addAttribute("productDatas", productDatas);
        model.addAttribute("payCk", payCK);
	    
	    // 쿠폰정보 반환하기
	    couponDTO.setMemberID((String)session.getAttribute("memberID"));
	    couponDTO.setSearchCondition("availableCoupon");
//	    List<CouponDTO> couponDatas = couponService.selectAll(couponDTO);
//	    System.out.println("[로그:정현진] couponDatas : "+couponDatas);
	    model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
        
        // 배송지 반환하기
//		addressDTO.setSearchCondition("shippingAddress");
		addressDTO.setMemberID((String)session.getAttribute("memberID"));
//		addressDTO.setOrderListID = setOrderListID(orderListID);
//		System.out.println("[로그:정현진] 배송지 : "+addressService.selectOne(addressDTO));
		model.addAttribute("addressDatas", addressService.selectOne(addressDTO));
		
		// 장바구니 상품수량 반환하기
		CartDTO cartDTO = new CartDTO();
		cartDTO.setMemberID((String)session.getAttribute("memberID"));
		model.addAttribute("cartProductCnt", headerCartService.getCartProductCnt(cartDTO));
		
		/*
		 * " addressDTO.setOrderListID = setOrderListID(orderListID); "
		 *   ORDERLIST테이블에 배송지FK 컬럼이 존재하지 않아 
		 *   주문번호에 해당하는 배송지를 반환받지 못하는 상황으로
		 *   해당 코드는 주석처리, 현재 기본배송지만 조회되고 있는 상황
		 */
		
		return "user/pay/initOrder";
    }
	
}
