package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetIssueCouponPage {
	
	@GetMapping("/getIssueCouponPage")
	public String getIssueCouponPage() {
		System.out.println("[로그:정현진] 쿠폰 발행 페이지 들어옴");
		return "admin/couponIssue";
		
	}
}
