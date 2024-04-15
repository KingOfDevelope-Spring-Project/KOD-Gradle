package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberWithdrawal {

	@Autowired
	MemberService memberService;
	
	@PostMapping("/memberWithdrawal")
	public String memberWithdrawal(MemberDTO memberDTO,HttpSession session) {
		
		memberDTO.setMemberID((String)session.getAttribute("memberID"));
		boolean flag = memberService.delete(memberDTO);
		
		if(!flag) {
			return "/user/main/failMemberWithdrawal";
		}
		return "/user/main/successMemberWithdrawal";
	}
	
}
