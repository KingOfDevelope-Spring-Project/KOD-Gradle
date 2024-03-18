package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetIssueCouponPage {
	
	@GetMapping("/getIssueCouponPage")
	public String getIssueCouponPage() {
		
		return "admin/couponIsuue";
		
	}
}
