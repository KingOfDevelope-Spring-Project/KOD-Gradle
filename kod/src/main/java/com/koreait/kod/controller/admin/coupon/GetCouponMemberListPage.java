package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetCouponMemberListPage {

	@GetMapping("/getCouponMemberListPage")
	public String getCouponMemberList() {
		
		return "admin/coupon/couponMemberList";
	}
}
