package com.koreait.kod.controller.admin.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetMemberManagePage {

	@GetMapping("/getMemberManagePage")
	public String getMemberManagePage() {
		
		
		return "admin/member/memberManage";
	}
}
