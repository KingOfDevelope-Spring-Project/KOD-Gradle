package com.koreait.kod.controller.admin.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;
import com.koreait.kod.biz.order.OrderListDTO;
import com.koreait.kod.biz.order.OrderListService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetAdminMainPage {
	@Autowired
	ProductService productService;
	@Autowired
	OrderListService orderListService;
	@Autowired
	MemberService memberService;
	
	@GetMapping("/getAdminMainPage")
	public String getStatisticsMain(ProductDTO productDTO,OrderListDTO orderListDTO,MemberDTO memberDTO,Model model,HttpSession session) {
		
		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
			return "common/error";
		}
		
		// 분기매출
		productDTO.setSearchCondition("quarterlyRevenueFor2Years");
		model.addAttribute("quarterlyRevenueDatas", productService.selectAll(productDTO));
		
		// 월간매출
		productDTO.setSearchCondition("monthlyRevenueFor1Year");
		model.addAttribute("monthlyRevenueDatas", productService.selectAll(productDTO));
		
		// 일간매출
		productDTO.setSearchCondition("dailyRevenueFor30Days");
		model.addAttribute("dailyRevenueDatas", productService.selectAll(productDTO));

		// 어제 오늘 주문건수
		orderListDTO.setSearchCondition("orderCountsForYesterdayAndToday");
		model.addAttribute("orderListData", orderListService.selectAll(orderListDTO));
		
		// 총회원수
		memberDTO.setSearchCondition("memberCounts");
		model.addAttribute("memberData", memberService.selectOne(memberDTO));
		
		return "admin/adminMain";
	}
}


//분기별 매출 금액(현재년도, 이전년도) o
//월별 주문건수(라인 그래프, x : 1~31일, y : 주문건수) o 
//일매출 o
//총 회원수
//일 주문건수
//전일 주문건수
