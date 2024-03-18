package com.koreait.kod.controller.admin.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.biz.coupon.CouponStatusDTO;
import com.koreait.kod.biz.coupon.CouponStatusService;
import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IssueCouponByGrade {

	@Autowired
	CouponService couponService;
	@Autowired
	CouponStatusService couponStatusService;
	@Autowired
	MemberService memberService;
	
	@PostMapping("/issueCouponByGrade")
	public String issueCouponByGrade(CouponDTO couponDTO,CouponStatusDTO couponStatusDTO,MemberDTO memberDTO,Model model,HttpSession session) {
		
		MemberDTO adminDTO = (MemberDTO)session.getAttribute("adminDTO");
		if(!adminDTO.getMemberGrade().equals("ADMIN")) {
			return "common/error";
		}
		
		/*
		 * 등급별 쿠폰발급 한글코딩
		 * 쿠폰을 발급하려는데 등급별로 쿠폰을 발급하려고 한다.
		 * 1. 등급별 회원목록을 조회한다. -> memberService
		 
		 * ※ 트랜잭션 단위(2,3)
		 * 2. 쿠폰을 생성한다. -> couponService
		 * 3. 등급별 회원목록에게 쿠폰을 발급한다. -> couponStatusService
			 * 3-1. 쿠폰을 발급하기 위해서는 쿠폰테이블과 쿠폰상태 테이블이 함께 추가 되어야한다. 
			 * 3-2. 쿠폰테이블에 데이터를 먼저 추가하여 쿠폰PK값을 생성하자 -> 쿠폰생성
			 * 3-3. 생선된 쿠폰PK값과 회원ID를 설정하여 쿠폰상태 테이블에 회원별 쿠폰을 발급하자
			 * 3-3-1. 쿠폰상테 테이블에 데이터를 삽입하기 위해서는 회원 ID가 필요한데 위에서 조회한 회원목록에서 반환받은 회원ID를 활용하자.
		 * 
		 * 
		 */
		
		
		// 쿠폰생성
		boolean flag = couponService.insert(couponDTO);
		couponDTO.setSearchCondition("getCouponID");
		int couponID = couponService.selectOne(couponDTO).getCouponID();
		if(!flag) {
			model.addAttribute("msg", "쿠폰발급 오류");
			return "common/goback";
		}
		
		// 등급별 회원목록 조회
		memberDTO.setSearchCondition("getMembersByGrade");
		memberDTO.setMemberGrade(memberDTO.getMemberGrade());
		List<MemberDTO> memberDatas = memberService.selectAll(memberDTO);
		
		// 회원별 쿠폰 발급
		for (MemberDTO data : memberDatas) {
			couponStatusDTO.setMemberID(data.getMemberID());
			couponStatusDTO.setCouponID(couponID);
			couponStatusService.insert(couponStatusDTO); 
		}
		
		return "admin/CouponIssue";
	}
}
