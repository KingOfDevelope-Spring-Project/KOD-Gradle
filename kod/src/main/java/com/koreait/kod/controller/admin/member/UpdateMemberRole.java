package com.koreait.kod.controller.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

public class UpdateMemberRole {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/updateMemberRole")
	public String updateMemberRole(MemberDTO memberDTO, Model model) {
		
		memberDTO.setSearchCondition("updateMemberRole");
		memberService.update(memberDTO);
		
		return "admin/member/memberRecoveryPage";
	}

}
