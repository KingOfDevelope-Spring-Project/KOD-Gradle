package com.koreait.kod.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KakaoLogin {
	
	@PostMapping("/kakaoLogin")
	public String kakaoLogin() {
		
		
		
		
		return "user/main";
	}

}
