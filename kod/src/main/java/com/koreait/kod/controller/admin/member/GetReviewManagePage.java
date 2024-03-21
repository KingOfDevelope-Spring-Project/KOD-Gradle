package com.koreait.kod.controller.admin.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetReviewManagePage {

	@GetMapping("/getReviewManagePage")
	public String getReviewManagePage() {
		
		
		return "admin/review/reviewManage";
	}
}
