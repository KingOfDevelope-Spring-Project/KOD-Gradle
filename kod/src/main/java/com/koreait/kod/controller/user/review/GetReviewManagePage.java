package com.koreait.kod.controller.user.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.review.ReviewDTO;
import com.koreait.kod.biz.review.ReviewService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

@Controller
public class GetReviewManagePage {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/getReviewManagePage")
	public String getReviewManagePage(ReviewDTO reviewDTO,Model model) {
		
		model.addAttribute("reviewDatas", reviewService.selectAll(reviewDTO));
		
		/*
		 * 요청주체가 관리자일 경우와 사용자일경우 반환페이지 구분 필요
		 */
		
		

		return "admin/review/reviewManage";
	}
}
