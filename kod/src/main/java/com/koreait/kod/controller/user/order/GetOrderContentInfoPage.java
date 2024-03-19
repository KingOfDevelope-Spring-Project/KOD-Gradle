package com.koreait.kod.controller.user.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.order.OrderContentDTO;
import com.koreait.kod.biz.order.OrderContentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetOrderContentInfoPage {
	@Autowired
	private OrderContentService orderContentService;

	@GetMapping("/getOrderContentInfoPage")
	public String getOrderContent(OrderContentDTO orderContentDTO, Model model, HttpSession session) {
		
		//세션에 저장된 memberID 가져오기
		orderContentDTO.setMemberID((String)session.getAttribute("memberID"));
		orderContentDTO.setSearchCondition("orderContent");
		model.addAttribute("orderContent", orderContentService.selectAll(orderContentDTO));
		
		return "orderInfo";
	}
}
