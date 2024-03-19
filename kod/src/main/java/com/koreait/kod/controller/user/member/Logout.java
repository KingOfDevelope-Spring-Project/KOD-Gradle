package com.koreait.kod.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class Logout {
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("memberID");
		
		return "user/main";
	}
}
