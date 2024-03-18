package com.koreait.kod.controller.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChangeMembeGrade {

	@Autowired
	MemberService memberService;
	
	@PostMapping("/changeMemberGrade")
	public String changeMemberGrade(List<MemberDTO> memberDatas,MemberDTO memberDTO,Model model,HttpSession session) {
		
		MemberDTO adminDTO = (MemberDTO)session.getAttribute("adminDTO");
		if(!adminDTO.getMemberGrade().equals("ADMIN")) {
			return "common/error";
		}
		
		for (MemberDTO data : memberDatas) {
			data.setSearchCondition("changeMemberGrade");
			memberService.update(data);
		}
		
		return "admin/MemberList";
	}
}
