package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;
import com.koreait.kod.controller.util.PasswordEncryptor;

import jakarta.servlet.http.HttpSession;

@Controller
public class KodLogin {
	
	@Autowired
	MemberService memberService;
    @Autowired
    PasswordEncryptor passwordEncryptor;
	
	@PostMapping("/kodLogin")
	public String login(MemberDTO memberDTO,Model model,HttpSession session) {
		
		System.out.println("[로그:정현진] 로그인요청 들어옴");
		
		memberDTO.setSearchCondition("getEncryptPW");
		String encryptedPasswordFromDatabase  = memberService.selectOne(memberDTO).getMemberPW();
		System.out.println("[로그:정현진] encryptedPasswordFromDatabase : "+encryptedPasswordFromDatabase);
		String encryptedPassword = passwordEncryptor.encrypt(memberDTO.getMemberPW());
		System.out.println("[로그:정현진] encryptedPassword : "+encryptedPassword);
		
		if(encryptedPassword.equals(encryptedPasswordFromDatabase)) {
			System.out.println("[로그:정현진] 비밀번호 일치");
		}
		else {
			System.out.println("[로그:정현진] 비밀번호 불일치");
		}
		
		// 로그인 요청
		memberDTO.setMemberPW(encryptedPassword);
		memberDTO.setSearchCondition("login");
		System.out.println("[로그:정현진] memberID = "+memberDTO.getMemberID());
		System.out.println("[로그:정현진] memberPW = "+memberDTO.getMemberPW());
		
		memberDTO = memberService.selectOne(memberDTO);
		
		
		// 로그인 실패 시 처리로직
		if(memberDTO == null) {
			System.out.println("[로그:정현진] 로그인 실패");
			model.addAttribute("msg", "로그인 실패");
			return "common/goback";
		}
		
		
		/*
		 * 회원 탈퇴신청 유무 확인로직 추가
		 * 로그인 요청한 회원이 탈퇴신청한 회원이라면 return 복구신청 확인 페이지로 이동
		 * 
		 * 탈퇴신청한 회원이 아니라면
		 * 로그인 성공
		 * 
		 */

		
		// 로그인 성공 시 처리로직
		session.setAttribute("memberID", memberDTO.getMemberID()); // 세션에 회원ID 저장
		if(!(memberDTO.getMemberRole().equals("NOT_PERMITTED"))) { // 사용자가 권한이 있는 상태라면 
			if(memberDTO.getMemberRole().equals("ADMIN")) {
				System.out.println("[로그:정현진] 관리자로그인 성공");
//				session.setAttribute("memberID", memberDTO.getMemberID());
				return "redirect:/getAdminMainPage";
			}
			else if(memberDTO.getMemberRole().equals("UNREGISTER")) {
		        System.out.println("틸퇴계정 로그인 성공");
		        session.setAttribute("memberID", memberDTO.getMemberID());
		        session.setAttribute("member", memberDTO);         
		        System.out.println("틸퇴계정 아이디 정보"+memberDTO);
		        System.out.println("틸퇴계정 아이디"+memberDTO.getMemberID());
		        System.out.println("틸퇴계정 상태"+memberDTO.getMemberRole());
		        System.out.println("틸퇴계정 이메일"+memberDTO.getMemberEmail());
		        System.out.println("틸퇴계정 이름"+memberDTO.getMemberName());
		        System.out.println("틸퇴계정 상태"+memberDTO.getMemberRole());
		        return "user/mypage/accountRecovery";
		    }
		    else if(memberDTO.getMemberRole().equals("RECOVERY_PENDING")) {
		        System.out.println("복구신청 상태 로그인 진입");
		        model.addAttribute("msg","아이디 복구신청 상태입니다.\n아이디가 복구되는 시간은 매일 오전 9시입니다.");
		        System.out.println("메시지"+model);
		        return "common/alert";
		    }

			
			System.out.println("[로그:정현진] 사용자로그인 성공");
//			session.setAttribute("memberID", memberDTO.getMemberID()); 
			return "redirect:/";
		}
		return "redirect:/getRequestMemberRestorationPage";
		
	}
}
