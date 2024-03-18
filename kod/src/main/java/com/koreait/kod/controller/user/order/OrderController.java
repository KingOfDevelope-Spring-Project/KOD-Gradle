package com.koreait.kod.controller.user.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;
import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.order.OrderContentDTO;
import com.koreait.kod.biz.order.OrderContentService;
import com.koreait.kod.biz.order.OrderListDTO;
import com.koreait.kod.biz.order.OrderListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	private OrderContentService orderContentService;
	@Autowired
	private OrderListService orderListService;
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "getOrderInfo",method = RequestMethod.GET)
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
	
	@RequestMapping(value = "getOrderContentInfoPage",method = RequestMethod.GET)
	public String getOrderContent(OrderContentDTO orderContentDTO, Model model, HttpSession session) {
		
		//세션에 저장된 memberID 가져오기
		orderContentDTO.setMemberID((String)session.getAttribute("memberID"));
		orderContentDTO.setSearchCondition("orderContent");
		model.addAttribute("orderContent", orderContentService.selectAll(orderContentDTO));
		
		return "orderInfo";
	}
	
	
	
}
