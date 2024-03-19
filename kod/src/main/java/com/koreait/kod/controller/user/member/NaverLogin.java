package com.koreait.kod.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NaverLogin {

	@PostMapping("/naverLogin")
	public String socialLogin() {
		
		
		
		
		return "user/main";
	}
}
