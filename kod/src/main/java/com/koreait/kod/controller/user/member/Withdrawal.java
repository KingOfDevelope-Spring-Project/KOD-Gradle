package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Withdrawal {

	@Autowired
	MemberService memberService;
	
	@GetMapping("/withDrawal")
	public String withdraw(MemberDTO memberDTO, HttpSession session) {
		System.out.println("회원탈퇴 컨트롤러 : "+session.getAttribute("memberID"));
		memberDTO.setMemberID((String)session.getAttribute("memberID"));
		System.out.println("회원탈퇴 컨트롤러 : "+memberDTO);
		memberDTO.setSearchCondition("memberUpdateRoleUnregister");
		memberService.update(memberDTO);
		return "redirect:logout";
	}
}
