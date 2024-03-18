package com.koreait.kod.controller.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetAllMember {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/getAllMembers")
	public String getAllMembers(MemberDTO memberDTO,Model model,HttpSession session) {
		
		MemberDTO adminDTO = (MemberDTO)session.getAttribute("adminDTO");
		if(!adminDTO.getMemberGrade().equals("ADMIN")) {
			return "common/error";
		}
		memberDTO.setSearchCondition("getAllMembers");
		model.addAttribute("memberDatas", memberService.selectAll(memberDTO));
		return "admin/MemberList";
	}

}
