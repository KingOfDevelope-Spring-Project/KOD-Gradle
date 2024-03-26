package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

@Controller
public class Join {
	
	@Autowired
	MemberService memberService;
	

	@PostMapping("/join")
	public String join(MemberDTO memberDTO) {
		
		System.out.println("[로그:정현진] 사용자가 입력한 비밀번호 : "+memberDTO.getMemberPW());
		
		memberService.insert(memberDTO);

		return "redirect:/";
	}
}
