package com.koreait.kod.controller.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

public class DeleteMember {
	
	@Autowired
	MemberService memberService;

	@PostMapping("/deleteMember")
	public String deleteMember(MemberDTO memberDTO, Model model) {
		
		memberService.delete(memberDTO);
		
		return "admin/member/memberList";
	}
}
