package com.koreait.kod.controller.admin.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.biz.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetCouponList {
	
	@Autowired
	CouponService couponService;

	@GetMapping("/getIssuedCouponListPage") // 쿠폰 발행 목록
	public String getIssuedCouponList(CouponDTO couponDTO,Model model,HttpSession session) {
		System.out.println("[로그:정현진] 쿠폰목록 페이지 들어옴");
		
		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
			return "common/error";
		}
		
		model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
		
		return "admin/coupon/couponList";
	}
	
	
}
