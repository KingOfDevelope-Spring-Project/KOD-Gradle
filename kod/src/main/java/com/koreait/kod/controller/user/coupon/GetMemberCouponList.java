package com.koreait.kod.controller.user.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;

@Controller
public class GetMemberCouponList { // 회원별  쿠폰목록 조회
    @Autowired
    CouponService couponService; // CouponService 의존주입

    @GetMapping("/getMemberCouponList") // GET 요청
    public String getMemberCouponList(@RequestParam("couponStatus") String couponStatus, Model model) {
        /*
         * RequestContextHolder를 사용하여 현재 요청에 대한 정보에 액세스할 수 있음
         * 해당 요청의 메소드 서명을 추출하여 isAdmin 값 결정에 활용
         */
        System.out.println("[로그:정현진] /getMemberCouponList 들어옴");

        CouponDTO couponDTO = new CouponDTO();

        if(couponStatus.equals("unused")) {
            couponDTO.setSearchCondition("unUsedCoupon");
        }
        else if(couponStatus.equals("used")) {
            couponDTO.setSearchCondition("usedCoupon");
        }
        else { // 만료
            couponDTO.setSearchCondition("expiredCoupon");
        }

        couponDTO.setSearchCondition("getMemberCouponList"); // CouponDTO에 검색 조건 설정
        model.addAttribute("couponDatas", couponService.selectAll(couponDTO)); // CouponService를 사용하여 쿠폰 데이터 조회 후 모델에 추가

        return "admin/coupon/couponMemberList"; // 관리자인 경우 사용되지 않은 쿠폰 목록 페이지로 이동
    }
}