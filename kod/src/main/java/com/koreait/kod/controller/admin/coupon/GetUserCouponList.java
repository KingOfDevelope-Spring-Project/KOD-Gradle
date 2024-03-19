package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetUserCouponList {

	@GetMapping("/getUserCouponList")
	public String getUserCouponList() {
		
		return "admin/userCouponList";
	}
	
}
