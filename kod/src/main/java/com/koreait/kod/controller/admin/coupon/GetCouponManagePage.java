package com.koreait.kod.controller.admin.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;

@Controller
public class GetCouponManagePage {

	@Autowired
	CouponService couponService;
	
	@GetMapping("/getCouponManagePage")
	public String getCouponManagePage(CouponDTO couponDTO,Model model) {
		
		// 총 발급된 쿠폰 개수, 자동발행쿠폰개수, 관리자발행쿠폰개수, 발급된 쿠폰목록
		model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
		
		return "admin/coupon/couponManage";
	}
}
