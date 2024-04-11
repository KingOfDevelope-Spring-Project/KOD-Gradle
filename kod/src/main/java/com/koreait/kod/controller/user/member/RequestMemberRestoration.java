package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;
import com.koreait.kod.biz.member.MemberServiceImpl;
import com.koreait.kod.biz.member.MemberServiceMail;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RequestMemberRestoration {

    private final MemberServiceMail memberServiceMail;
    private final MemberServiceImpl memberServiceImpl;
    @Autowired
    MemberService memberService;
  

                  
    @PostMapping("/requestMemberRestoration") 
    public String MailSend(MemberDTO memberDTO, Model model, HttpSession session){ 
    	System.out.println("메일 발송 메서드 들어옴");
    	// 등록된 회원이라면 메일을 보내줘, 아니라면 실패했다고 알려줘
    
    	memberDTO.setMemberID(((MemberDTO)session.getAttribute("member")).getMemberID());
    	memberDTO.setMemberName(((MemberDTO)session.getAttribute("member")).getMemberName());
    	memberDTO.setMemberEmail(((MemberDTO)session.getAttribute("member")).getMemberEmail());
    	memberDTO.setMemberPhoneNumber(((MemberDTO)session.getAttribute("member")).getMemberPhoneNumber());
    	memberDTO.setMemberRole(((MemberDTO)session.getAttribute("member")).getMemberRole());

   	
    	System.out.println("회원 아이디"+memberDTO.getMemberID());
    	System.out.println("회원 이메일"+memberDTO.getMemberEmail());
    	System.out.println("회원 상태"+memberDTO.getMemberRole());
    	System.out.println("회원 이름"+memberDTO.getMemberName());
    	System.out.println("회원 전화번호"+memberDTO.getMemberPhoneNumber());
    	
    

		memberServiceMail.CreateMail(memberDTO);
		memberDTO.setSearchCondition("memberUpdateRoleRecoveryPending");
		System.out.println("회원 상태" + memberDTO.getMemberRole());
		memberServiceImpl.update(memberDTO);
		return "redirect:logout";
    
    }

}