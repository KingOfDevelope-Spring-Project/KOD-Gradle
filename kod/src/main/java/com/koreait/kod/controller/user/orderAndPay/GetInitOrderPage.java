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
import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetInitOrderPage {  

	@Autowired
	private AddressService addressService;
	@Autowired
	ProductService productService;
	@Autowired
	CouponService couponService;
	
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
//	public String rightNowBuy(ProductDTO productDTO,Model model) {
//		
//		// productDTO에는 상품ID와 수량이 들어있음
//		model.addAttribute("productsToBuy",productDTO);
//		
//		return "redirect:/getOrderInfoPage";
//	}
	
//	// 선택구매할 상품정보를 주문정보페이지로 보내기
//	@GetMapping("/buy")
//	public String buy(List<ProductDTO> productList,Model model) {
//		
//		model.addAttribute("productsToBuy", productList);
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
	public String getInitOrderPage(List<ProductDTO> productList,CouponDTO couponDTO,AddressDTO addressDTO,Model model, HttpSession session) {
			
//	    // model에서 받아온 데이터가 null이라면
//	    if (model.getAttribute("productsToBuy") == null) {
//	    	return "common/error";
//	    }
	    
//	    // 구매할 상품정보 반환하기
//	    if (model.getAttribute("productsToBuy") instanceof ProductDTO) {
//            // 즉시구매 일 경우
//            ProductDTO productDTO = (ProductDTO) model.getAttribute("productsToBuy");
//            model.addAttribute("productOrderInfo", productDTO);
//        } 
//        else {
            // 선택구매 일 경우 
        	// @@@@@질문2. new ArrayList<ProductDTO>()
        	// ★ 반환타입이 인터페이스인 List인데 괜찮은건지.. ArrayList로 형변환하여 반환해야하는지 .. 고민 ?
//            productList = (List<ProductDTO>) model.getAttribute("productsToBuy");  // 받아온 리스트
		
//            List<ProductDTO> productDatas = new ArrayList<ProductDTO>(); // 스코프이슈 (반환할 데이터)
//            for (ProductDTO product : productList) {
//            	ProductDTO data = new ProductDTO();
//            	data.setProductID(product.getProductID());
//            	data = productService.selectOne(product); // 구매수량을 제외한 상품정보가 모두 반환되어야함
//            	data.setProductCnt(product.getProductCnt());
//            	productDatas.add(data);
//			}
//            model.addAttribute("productDatas", productDatas);
//        }
		
		
        List<ProductDTO> productDatas = new ArrayList<ProductDTO>(); // 스코프이슈 (반환할 데이터)
        for (ProductDTO product : productList) { // productList에는 상품정보와 구매유형(즉시구매,선택구매)이 담겨있음
        	ProductDTO data = new ProductDTO();
        	data.setProductID(product.getProductID());
        	data = productService.selectOne(product); // 구매수량을 제외한 상품정보가 모두 반환되어야함
        	data.setProductCnt(product.getProductCnt()); // 구매수량 설정
        	data.setPurchaseType(product.getPurchaseType()); // 구매유형 설정
        	productDatas.add(data);
		}
        model.addAttribute("productDatas", productDatas);
	    
	    // 쿠폰정보 반환하기
	    couponDTO.setMemberID((String)session.getAttribute("memberID"));
	    model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
        
        // 배송지 반환하기
		addressDTO.setSearchCondition("shippingAddress");
		addressDTO.setMemberID((String)session.getAttribute("memberID"));
//		addressDTO.setOrderListID = setOrderListID(orderListID);
		model.addAttribute("shippingAddress", addressService.selectOne(addressDTO));
		/*
		 * " addressDTO.setOrderListID = setOrderListID(orderListID); "
		 *   ORDERLIST테이블에 배송지FK 컬럼이 존재하지 않아 
		 *   주문번호에 해당하는 배송지를 반환받지 못하는 상황으로
		 *   해당 코드는 주석처리, 현재 기본배송지만 조회되고 있는 상황
		 */
		
		return "user/pay/initOrder";
    }
	
}
