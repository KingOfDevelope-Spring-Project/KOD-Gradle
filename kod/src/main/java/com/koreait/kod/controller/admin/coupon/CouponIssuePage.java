package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

@Controller
public class CouponIssuePage { // 쿠폰 발행 페이지 이동
	
	@GetMapping("/couponIssuePage")
	@LoginCheck(checkRole = Role.ADMIN)
	public String couponIssuePage() {
		
		System.out.println("[로그:정현진] 쿠폰 발행 페이지 들어옴");
		return "admin/coupon/couponIssue";
	}
}
