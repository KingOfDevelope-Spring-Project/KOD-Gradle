package com.koreait.kod.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinPage {

	@GetMapping("/joinPage")
	public String joinPage() {
		
		
		return "user/member/join";
	}
}
