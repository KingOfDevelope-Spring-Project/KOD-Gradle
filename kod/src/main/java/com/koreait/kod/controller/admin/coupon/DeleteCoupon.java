package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteCoupon {
	
	@PostMapping("/deleteCoupon")
	public String deleteCoupon() {
		
		return "admin/coupon/couponList";
	}
}
