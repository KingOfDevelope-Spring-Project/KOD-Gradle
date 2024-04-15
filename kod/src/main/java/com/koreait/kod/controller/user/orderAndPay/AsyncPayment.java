package com.koreait.kod.controller.user.orderAndPay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;
import com.koreait.kod.biz.coupon.CouponStatusDTO;
import com.koreait.kod.biz.coupon.CouponStatusService;
import com.koreait.kod.biz.order.OrderContentDTO;
import com.koreait.kod.biz.order.OrderContentService;
import com.koreait.kod.biz.order.OrderListDTO;
import com.koreait.kod.biz.order.OrderListService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsyncPayment {
	
	@Autowired
	OrderListService orderListService;
	@Autowired
	OrderContentService orderContentService;
	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;
	@Autowired
	CouponStatusService couponStatusService;

	@PostMapping("/asyncPayment")
	public @ResponseBody int asyncPayment(@RequestParam("payCk") int payCk,
										  @RequestParam("productList") List<String> cartSelectedProducts,
								          @RequestParam("productCnt") List<String> cartProductCnt,
								          @RequestParam("couponIDs") List<String> couponIDs,
									      OrderListDTO orderListDTO,
									      OrderContentDTO orderContentDTO,
									      CartDTO cartDTO,Model model,
									      HttpSession session) {
		
		System.out.println("[로그:정현진] /asyncPayment들어옴");
		
		orderListDTO.setMemberID((String)session.getAttribute("memberID"));
		boolean flag = orderListService.insert(orderListDTO);
		System.out.println("[로그:정현진] 주문번호 생성 flag : "+flag);
		
//		for (String data : cartSelectedProducts) {
//			System.out.println("[로그:정현진] data : "+data);
//			String parsingData = data.replaceAll("[\\[\\],\"]", "");
//			System.out.println("[로그:정현진] parsingData : "+parsingData);
//		}
//		for (String data : cartProductCnt) {
//			System.out.println("[로그:정현진] data : "+data);
//			String parsingData = data.replaceAll("[\\[\\],\"]", "");
//			System.out.println("[로그:정현진] parsingData : "+parsingData);
//		}
		
		int orderListID=0; // 스코프 이슈
		if(flag==true) {
			orderListDTO.setMemberID((String)session.getAttribute("memberID"));
			orderListDTO.setSearchCondition("check_max_id");
			orderListID = orderListService.selectOne(orderListDTO).getOrderListID(); // 주문내역을 저장할 주문번호 가져오기
			System.out.println("[로그:정현진] orderListID : "+orderListID);
			for (int i = 0; i < cartSelectedProducts.size(); i++) {
				String parsingProductID = cartSelectedProducts.get(i).replaceAll("[\\[\\],\"]", "");
				String parsingProductCnt = cartProductCnt.get(i).replaceAll("[\\[\\],\"]", "");
				System.out.println("[로그:정현진] parsingProductID : "+parsingProductID);				
				System.out.println("[로그:정현진] parsingProductCnt : "+parsingProductCnt);				
							
				orderContentDTO.setOrderListID(orderListID);
				orderContentDTO.setProductID(Integer.parseInt(parsingProductID));
				orderContentDTO.setOrderContentCnt(Integer.parseInt(parsingProductCnt));
				flag = orderContentService.insert(orderContentDTO);
				System.out.println("[로그:정현진] 주문내역 추가 flag : "+flag);
				
				// 구매한 상품 재고변경 
				ProductDTO data = new ProductDTO();
				data.setProductID(Integer.parseInt(parsingProductID));
				data.setProductCnt(Integer.parseInt(parsingProductCnt));
				data.setSearchCondition("payment");
				flag = productService.update(data); // (구매한 수량만큼 재고 감소)
				System.out.println("[로그:정현진] 상품 재고변경 flag : "+flag);
				
				if(payCk==0) { // 장바구니 구매 일 경우 구매한상품 장바구니에서 제거
					System.out.println("[로그:정현진] payCk==0 장바구니 구매 들어옴");
					cartDTO.setMemberID((String)session.getAttribute("memberID"));
					cartDTO.setProductID(Integer.parseInt(parsingProductID));
					cartDTO.setSearchCondition("removeProductOfCart");
					flag=cartService.delete(cartDTO);
					System.out.println("[로그:정현진] 장바구니 비우기 flag : "+flag);
				}// if(선택구매일 경우)
				
			}// for(구매로직)
		}// if(주문번호가 생성되었다면)
		
		
		/*
		 * 쿠폰을 사용했을 경우에만
		 * 쿠폰상태에 주문내역 UPDATE
		 * 1. 주문내역 찾아오기
		 * 	1-1. 주문내역을 찾아오려면 주문번호가 필요함 -> 주문번호는 url에 있음
		 */
		System.out.println("[로그:정현진] 쿠폰상태 UPDATE 시작");
		orderContentDTO.setSearchCondition("getOrderContentID");
		orderContentDTO.setOrderListID(orderListID);
		List<OrderContentDTO> orderContentDatas = orderContentService.selectAll(orderContentDTO);
		for (int i = 0; i < couponIDs.size(); i++) {
			String parsingCouponID = couponIDs.get(i).replaceAll("[\\[\\],\"]", "");
			System.out.println("[로그:정현진] parsingCouponID : "+parsingCouponID);	
			int couponID = Integer.parseInt(parsingCouponID);
			System.out.println("[로그:정현진] couponID : "+couponID);	
			if(couponID>0) {
				CouponStatusDTO couponStatusDTO = new CouponStatusDTO();
				couponStatusDTO.setMemberID((String)session.getAttribute("memberID"));
				couponStatusDTO.setOrderContentID(orderContentDatas.get(i).getOrderContentID());
				couponStatusDTO.setCouponID(couponID);
				couponStatusService.update(couponStatusDTO); // 쿠폰상태에 주문내역 설정
			}
		}
		System.out.println("[로그:정현진] 쿠폰상태 UPDATE 종료");
		
		
//		for (int i = 0; i < couponIDs.size(); i++) {
//			if(couponIDs.get(i)>0) { // 쿠폰을 사용한 경우에만 
//				CouponStatusDTO couponStatusDTO = new CouponStatusDTO();
//				couponStatusDTO.setMemberID((String)session.getAttribute("memberID"));
//				couponStatusDTO.setOrderContentID(orderContentDatas.get(i).getOrderContentID());
//				couponStatusDTO.setCouponID(couponIDs.get(i));
//				couponStatusService.update(couponStatusDTO); // 쿠폰상태에 주문내역 설정
//			}
//		}
			
			
		
//		if(flag==true) { // 주문번호가 생성되었다면
//			for (ProductDTO product : productList) { // 구매로직 실행
//				orderListDTO.setMemberID((String)session.getAttribute("memberID"));
//				int orderListID = orderListService.selectOne(orderListDTO).getOrderListID();
//				orderContentDTO.setOrderListID(orderListID);
//				orderContentDTO.setProductID(product.getProductID());
//				orderContentDTO.setOrderContentCnt(product.getProductCnt());
//				flag = orderContentService.insert(orderContentDTO);
//				
//				// 구매한 상품 재고변경
//				ProductDTO data = new ProductDTO();
//				data.setProductCnt(orderContentDTO.getOrderContentCnt());
//				data.setProductID(orderContentDTO.getProductID());
//				productService.update(productDTO);
//				if(product.getPurchaseType()==0) { // 선택구매 일 경우 선택상품 장바구니 비우기
//					cartDTO.setProductID(orderContentDTO.getProductID());
//					cartDTO.setMemberID((String)session.getAttribute("memberID"));
//					cartDTO.setSearchCondition("개별상품삭제");
//					flag = cartService.delete(cartDTO);
//				}// if(선택구매일 경우)
//			}// for(구매로직)
//		} // if(주문번호가 생성되었다면)
		
		return orderListID;
	}
}
