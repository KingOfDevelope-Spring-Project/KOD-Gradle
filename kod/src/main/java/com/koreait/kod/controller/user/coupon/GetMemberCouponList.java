package com.koreait.kod.controller.user.coupon;
//
//import java.lang.reflect.Method;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.servlet.HandlerMapping;
//
//import com.koreait.kod.biz.coupon.CouponDTO;
//import com.koreait.kod.biz.coupon.CouponService;
//import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheckWithRoles;
//import com.koreait.kod.controller.util.LoginCheckAspect.Role;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Controller
//public class GetMemberCouponList { // 회원별  쿠폰목록 조회
//    @Autowired
//    CouponService couponService; // CouponService 의존주입
//
//    @GetMapping("/getMemberCouponList") // GET 요청
//    @LoginCheckWithRoles(value = {Role.ADMIN, Role.USER}) // LoginCheckWithRoles 어노테이션을 사용하여 권한 확인
//    public String getMemberCouponList(@RequestParam("couponStatus") String couponStatus, Model model) {
//        /*
//         * RequestContextHolder를 사용하여 현재 요청에 대한 정보에 액세스할 수 있음
//         * 해당 요청의 메소드 서명을 추출하여 isAdmin 값 결정에 활용
//         */
//        System.out.println("[로그:정현진] /getMemberCouponList 들어옴");
//
//        CouponDTO couponDTO = new CouponDTO();
//
//        if(couponStatus.equals("unused")) {
//            couponDTO.setSearchCondition("unUsedCoupon");
//        }
//        else if(couponStatus.equals("used")) {
//            couponDTO.setSearchCondition("usedCoupon");
//        }
//        else { // 만료
//            couponDTO.setSearchCondition("expiredCoupon");
//        }
//
//        couponDTO.setSearchCondition("getMemberCouponList"); // CouponDTO에 검색 조건 설정
//        model.addAttribute("couponDatas", couponService.selectAll(couponDTO)); // CouponService를 사용하여 쿠폰 데이터 조회 후 모델에 추가
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        Method handlerMethod = (Method) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
//        LoginCheckWithRoles loginCheckWithRoles = handlerMethod.getAnnotation(LoginCheckWithRoles.class);
//        boolean isAdmin = loginCheckWithRoles.isAdmin();
//
//        // isAdmin 값에 따라 반환할 뷰 결정
//        if (isAdmin) {
//            return "admin/coupon/couponMemberList"; // 관리자인 경우 사용되지 않은 쿠폰 목록 페이지로 이동
//        } 
//        else {
//            return "user/coupon/memberCouponList"; // 일반 사용자인 경우 회원별 쿠폰 목록 페이지로 이동
//        }
//    }
//}






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetMemberCouponList {
   
   @Autowired
   CouponService couponService;

   @GetMapping("/getIssuedCouponListPage") // 쿠폰 발행 목록
   public String getIssuedCouponList(CouponDTO couponDTO,Model model,HttpSession session) {
      System.out.println("[로그:정현진] 쿠폰목록 페이지 들어옴");
      
      couponDTO.setSearchCondition("searchCoupon");
      model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
      
      return "admin/coupon/couponList";
   }
   
}








