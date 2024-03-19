package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetCouponManagePage {

	@GetMapping("/getCouponManagePage")
	public String getCouponManage() {
		
		
		
		return "admin/coupon/couponManage";
	}
}
