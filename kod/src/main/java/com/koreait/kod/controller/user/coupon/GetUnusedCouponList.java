package com.koreait.kod.controller.user.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetUnusedCouponList {
	@Autowired
	CouponService couponService;

	@GetMapping("/getUnusedCouponList")
	public String getUnusedCouponList(CouponDTO couponDTO,Model model,HttpSession session) {
		
		couponDTO.setSearchCondition("getUsedCouponListOfMember");
		model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
		
		return "user/coupon/UnusedCouponList";
	}
	
}
