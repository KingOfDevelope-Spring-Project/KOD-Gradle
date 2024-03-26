package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

@Controller
public class AsyncCheckMemberID {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/asyncCheckMemberID")
	public boolean checkMemberID(MemberDTO memberDTO) {
		System.out.println("[로그:정현진] 아이디 중복체크 들어옴");
		System.out.println("[로그:정현진] 입력된 아이디 : "+memberDTO.getMemberID());
		return memberService.selectOne(memberDTO)==null; // 아이디 중복이면 false 중복아이디가 없을 경우 true반환 예정
	}
}
