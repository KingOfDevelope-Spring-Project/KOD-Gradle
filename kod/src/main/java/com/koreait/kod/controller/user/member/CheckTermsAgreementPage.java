package com.koreait.kod.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckTermsAgreementPage {

	@GetMapping("/checkTermsAgreementPage")
	public String checkTermsAgreementPage() {
		
		return "/user/join/checkTermsAgreementPage";
	}
	
}
