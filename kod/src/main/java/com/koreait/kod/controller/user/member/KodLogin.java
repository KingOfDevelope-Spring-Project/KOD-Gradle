package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class KodLogin {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/login")
	public String login(MemberDTO memberDTO,Model model,HttpSession session) {
		
		System.out.println("[로그:정현진] 로그인요청 들어옴");
		
		// 로그인 요청
		memberDTO.setSearchCondition("login");
		System.out.println("[로그:정현진] memberID = "+memberDTO.getMemberID());
		System.out.println("[로그:정현진] memberPW = "+memberDTO.getMemberPW());
		
		memberDTO = memberService.selectOne(memberDTO);
		
		// 로그인 실패 시 처리로직
		if(memberDTO == null) {
			System.out.println("[로그:정현진] 로그인 실패");
			model.addAttribute("msg", "로그인 실패");
			return "common/goback";
		}
		
		// 로그인 성공 시 처리로직
		if(memberDTO.getMemberRole().equals("ADMIN")) {
			System.out.println("[로그:정현진] 관리자로그인 성공");
			session.setAttribute("adminDTO", memberDTO);
			return "admin/adminMain";
		}
		System.out.println("[로그:정현진] 사용자로그인 성공");
		session.setAttribute("memberID", memberDTO.getMemberID()); 
		return "user/main";
	}
}
