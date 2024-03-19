package com.koreait.kod.controller.user.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;
import com.koreait.kod.biz.order.OrderListDTO;
import com.koreait.kod.biz.order.OrderListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetOrderInfo {

	@Autowired
	private OrderListService orderListService;
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/getOrderInfo")
	public String getOrderInfo(OrderListDTO orderListDTO, AddressDTO addressDTO,Model model, HttpSession session) {
		
		// 세션에 저장된 memberID 가져오기
		String memberID = (String)session.getAttribute("memberID");
		// 객체에 가져온 memberID 설정
		addressDTO.setMemberID(memberID);
		addressDTO.setSearchCondition("shippingAddress");
		model.addAttribute("shippingAddress", addressService.selectOne(addressDTO));
		
		
		orderListDTO.setMemberID((String)session.getAttribute("memberID"));
		model.addAttribute("orderNumber", orderListService.selectOne(orderListDTO));
		
		return "forward:getOrderContentInfoPage";
	}
}
