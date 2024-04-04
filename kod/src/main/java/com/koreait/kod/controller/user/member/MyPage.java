package com.koreait.kod.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPage {

	@GetMapping("/myPage")
	public String myPage() {
		
		return "/user/mypage/mypage";
	}
	
}
