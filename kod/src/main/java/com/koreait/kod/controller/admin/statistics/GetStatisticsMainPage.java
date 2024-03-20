package com.koreait.kod.controller.admin.statistics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetStatisticsMainPage {
	
	ProductService productService;
	
	public String getStatisticsMain(ProductDTO productDTO,Model model,HttpSession session) {
		
		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
			return "common/error";
		}
		
		productDTO.setSearchCondition("quarterlyRevenueFor2Years");
		model.addAttribute("quarterlyRevenueDatas", productService.selectAll(productDTO));
		productDTO.setSearchCondition("monthlyRevenueFor1Year");
		model.addAttribute("monthlyRevenueDatas", productService.selectAll(productDTO));
		productDTO.setSearchCondition("dailyRevenueFor30Days");
		model.addAttribute("dailyRevenueDatas", productService.selectAll(productDTO));

		
		return "admin/statisticsMain";
	}
}


//분기별 매출 금액(현재년도, 이전년도) o
//월별 주문건수(라인 그래프, x : 1~31일, y : 주문건수) o 
//일매출 o
//총 회원수
//일 주문건수
//전일 주문건수
