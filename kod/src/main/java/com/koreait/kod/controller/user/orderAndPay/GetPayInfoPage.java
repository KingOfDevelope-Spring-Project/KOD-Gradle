package com.koreait.kod.controller.user.orderAndPay;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;
import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.biz.order.OrderContentDTO;
import com.koreait.kod.biz.order.OrderContentService;
import com.koreait.kod.biz.order.OrderListDTO;
import com.koreait.kod.biz.order.OrderListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetPayInfoPage {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderListService orderListService;
	@Autowired
	private OrderContentService orderContentService;
	@Autowired
	private CouponService couponService;
	
	@GetMapping("/getPayInfoPage")
	public String getPayInfoPage(OrderListDTO orderListDTO,
								 OrderContentDTO orderContentDTO,
								 CouponDTO couponDTO,
								 AddressDTO addressDTO,
								 Model model, 
								 HttpSession session) {
		
		System.out.println("[로그:정현진] /getPayInfoPage 들어옴");
		
		/*
		 * 결제정보 페이지 로직
		 * 
		 * 해당 로직에서는 결제할 상품에 대한 데이터가 모두 들어있어야 함
		 * 걸제정보에는 
		 * 	1. 결제된 상품정보 -> orderList.selectOne & orderContent.selectAll , 주문번호로 주문내역 가져오기
		 * 	2. 적용된 쿠폰정보 -> couponService.selectAll , 상품에 적용된 쿠폰들
		 *  3. 배송지가 존재함 -> addressService.seletOne , 배송지는 주문번호당 1개니까 selectOne
		 */
		
		// 주문번호를 반환받아 해당 주문번호로 배송지와 주문내역 찾아오기
		orderListDTO.setSearchCondition("check_max_id");
		orderListDTO.setMemberID((String)session.getAttribute("memberID"));
		int orderListID = orderListService.selectOne(orderListDTO).getOrderListID();
		System.out.println("[로그:정현진] orderListID : "+orderListID);
		
		// 주문내역 IDs 반환받기
		orderContentDTO.setSearchCondition("orderContentIDs"); //주문정보
		orderContentDTO.setMemberID((String)session.getAttribute("memberID")); // 회원정보 설정
		orderContentDTO.setOrderListID(orderListID); // 주문번호 설정
		List<OrderContentDTO> orderContentDatas = orderContentService.selectAll(orderContentDTO);
		System.out.println("[로그:정현진] orderContentDatas : "+orderContentDatas);
		
		/*
		 * 주문상세내역과 적용된 쿠폰내역을 어떻게 맵핑시켜줄것인가 ?
		 * 1. COUPON_STATUS 테이블은 주문내역번호를 가지고 있음
		 * 	  -> 해당 주문내역에 어떤 쿠폰이 사용되었는지 조회할수있음 -> 쿠폰DTO에 주문내역ID를 설정하여 상세내역을 반환받자
		 * 
		 */
//		List<CouponDTO> payInfoDatas = new ArrayList<CouponDTO>();
//		for (OrderContentDTO orderContent : orderContentDatas) {
//			CouponDTO data = new CouponDTO();
//			couponDTO.setOrderContentID(orderContent.getOrderContentID());
//			payInfoDatas.add(couponService.selectOne(data));
//		}
//		model.addAttribute("payInfoDatas", payInfoDatas);
		
		// 배송지 가져오기
		addressDTO.setSearchCondition("shippingAddress");
		addressDTO.setMemberID((String)session.getAttribute("memberID"));
//		addressDTO.setOrderListID = setOrderListID(orderListID);
		model.addAttribute("addressData", addressService.selectOne(addressDTO));
		/*
		 * " addressDTO.setOrderListID = setOrderListID(orderListID); "
		 *   OrderList테이블에 배송지 컬럼이 존재하지 않아 
		 *   주문번호에 해당하는 배송지를 반환받지 못하는 상황으로
		 *   해당 코드는 주석처리, 현재 기본배송지만 조회되고 있는 상황
		 */
		
		return "user/pay/payInfo";
	}

}
