package com.koreait.kod.controller.admin.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;

@Controller
public class GetCouponMemberListPage {

	@Autowired
	CouponService couponService;
	
	@GetMapping("/getCouponMemberListPage")
	public String getCouponMemberList(CouponDTO couponDTO,Model model) {
		
		couponDTO.setSearchCondition("getCouponMemberList");
		model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
		
		return "admin/coupon/couponMemberList";
	}
}
