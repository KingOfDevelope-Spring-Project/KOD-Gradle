package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberCouponListPage { // (관리자)회원별 쿠폰목록페이지 이동

	@GetMapping("/memberCouponListPage")
	public String memberCouponListPage() {
		
		return "admin/coupon/couponMemberList";
	}
	
}
