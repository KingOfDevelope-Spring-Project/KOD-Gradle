package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/loginPage",method = RequestMethod.GET)
	public String loginPage(MemberDTO memberDTO,HttpSession session) {
		
		System.out.println("[로그:정현진] 로그인페이지 들어옴");
		
		String memberID = (String)session.getAttribute("memberID");
		
		if(memberID==null) {
			return "user/login/loginPage";
		}
		return "redirect/logout";
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("memberID");
		
		return "user/main";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
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
		if(memberDTO.getMemberGrade().equals("ADMIN")) {
			System.out.println("[로그:정현진] 관리자로그인 성공");
			session.setAttribute("adminDTO", memberDTO);
			return "admin/adminMain";
		}
		System.out.println("[로그:정현진] 사용자로그인 성공");
		session.setAttribute("memberID", memberDTO.getMemberID()); 
		return "user/main";
	}
	
	
}
