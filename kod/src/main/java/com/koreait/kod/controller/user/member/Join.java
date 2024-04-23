package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;
import com.koreait.kod.controller.util.PasswordEncryptor;

@Controller
public class Join {
	
	@Autowired
	MemberService memberService;
	@Autowired
	PasswordEncryptor passwordEncryptor;

	@PostMapping("/join")
	public String join(
			MemberDTO memberDTO,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			@RequestParam("day") String day,
			@RequestParam("phoneNumberPrefix") String phoneNumberPrefix,
			@RequestParam("phoneNumberMiddle") String phoneNumberMiddle,
			@RequestParam("phoneNumberSuffix") String phoneNumberSuffix,
			@RequestParam("emailUsername") String emailUsername,
			@RequestParam("emailDomain") String emailDomain) {
		System.out.println("[로그:정현진] 회원가입 실행");
		System.out.println("[로그:정현진] memberDTO : "+memberDTO);
		
		String birth = year+month+day;
		System.out.println("[로그:정현진] 전화번호 : "+birth);
		memberDTO.setMemberBirth(birth);
		
		String phoneNumber = phoneNumberPrefix+phoneNumberMiddle+phoneNumberSuffix;
		System.out.println("[로그:정현진] 전화번호 : "+phoneNumber);
		memberDTO.setMemberPhoneNumber(phoneNumber);
		
		String email = emailUsername+emailDomain;
		System.out.println("[로그:정현진] 이메일 : "+email);
		memberDTO.setMemberEmail(email);
		
		System.out.println("[로그:정현진] 사용자가 입력한 비밀번호 : "+memberDTO.getMemberPW());
		memberDTO.setMemberPW(passwordEncryptor.encrypt(memberDTO.getMemberPW()));
		
		memberService.insert(memberDTO);
		// 인증 메일 보내기 메서드
//		mailsender.mailSendWithUserKey(userVO.getUser_email(), userVO.getUser_id(), request);


		
		
		return "redirect:/";
	}
}
