package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginPage {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/loginPage")
	public String loginPage(MemberDTO memberDTO,HttpSession session) {
		
		System.out.println("[로그:정현진] 로그인페이지 들어옴");
		
		String memberID = (String)session.getAttribute("memberID");
		
		
		if(memberID==null) {
			return "user/login/loginPage";
		}
		return "redirect:/logout";
	}
	
	
	
	
	
	
}
