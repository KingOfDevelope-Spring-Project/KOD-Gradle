package com.koreait.kod.controller.admin.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.biz.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetCouponList {
	
	@Autowired
	CouponService couponService;

	@GetMapping("/getAllCouponList")
	public String getAllCouponList(CouponDTO couponDTO,Model model,HttpSession session) {
	
		MemberDTO adminDTO = (MemberDTO)session.getAttribute("adminDTO");
		if(!adminDTO.getMemberGrade().equals("ADMIN")) {
			return "common/error";
		}
		
		model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
		
		
		return "admin/CouponList";
	}
	
	
}
