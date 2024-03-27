package com.koreait.kod.controller.user.coupon;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetMemberCouponList { // 회원별  쿠폰목록 조회
    @Autowired
    CouponService couponService; // CouponService 의존주입
    @Autowired
    MemberService memberService;

    @GetMapping("/getMemberCouponList") // GET 요청
    public String getMemberCouponList(CouponDTO couponDTO,MemberDTO memberDTO, Model model, HttpSession session) {
        
        couponDTO.setSearchCondition("getMemberCouponList"); // CouponDTO에 검색 조건 설정
        model.addAttribute("couponDatas", couponService.selectAll(couponDTO)); // CouponService를 사용하여 쿠폰 데이터 조회 후 모델에 추가
        
        memberDTO.setMemberID((String)session.getAttribute("memberID"));
        
        if (memberService.selectOne(memberDTO).getMemberRole()=="ADMIN") {
            return "admin/coupon/couponMemberList"; // 관리자인 경우 사용되지 않은 쿠폰 목록 페이지로 이동
        } 
        else {
            return "user/coupon/memberCouponList"; // 일반 사용자인 경우 회원별 쿠폰 목록 페이지로 이동
        }
    }
}