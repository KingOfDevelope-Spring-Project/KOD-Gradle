package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetMemberWithdrawalPage {

	@Autowired
	MemberService memberService;
	
	@GetMapping("/getMemberWithdrawalPage")
	public String getMemberWithdrawalPage(MemberDTO memberDTO,HttpSession session,Model model) {
		
		memberDTO.setMemberID((String)session.getAttribute("memberID"));
		model.addAttribute("memberDatas", memberService.selectOne(memberDTO));
		
		return "/user/mypage/memberWithdrawalPage";
	}
	
}
