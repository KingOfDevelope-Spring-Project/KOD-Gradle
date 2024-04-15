package com.koreait.kod.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetRequestMemberRestorationPage {

	@GetMapping("/getRequestMemberRestorationPage")
	public String getRequestMemberRestorationPage() {
		
		return "memberRestorationPage";
	}
	
}
