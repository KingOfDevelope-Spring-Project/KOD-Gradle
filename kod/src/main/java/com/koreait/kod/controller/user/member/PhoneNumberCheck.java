package com.koreait.kod.controller.user.member;



import java.io.PrintWriter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class PhoneNumberCheck {
	
    
    @GetMapping("/phoneNumberCheck")
    public @ResponseBody String phoneNumberCheck(MemberDTO memberDTO , Random rand , Message message) {
        System.out.println("[로그:구본승] 인증번호 발송 들어옴");
        
        
        // 랜덤 인증번호 생성 
        int randNum = rand.nextInt(900000) + 100000;

        // 회원 전화번호를 가져옴
        String memberPhoneNumber = memberDTO.getMemberPhoneNumber();
        System.out.println("전화번호 인증 : " + memberPhoneNumber);
       
        // 랜덤 인증번호를 문자열로 변환
        String authNumber = String.valueOf(randNum);
        System.out.println("랜덤 인증번호 : " + authNumber);
        
        
     // Nurigo SDK를 사용하여 SMS 전송을 위한 메시지 객체 생성
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSYIJZKHSBNFC8A", "XAGL0XDW5MWDBMWSRRCMXYKSP7GOA6UE", "https://api.coolsms.co.kr"); // Message
      
        // 보내는 사람 번호 설정
        message.setFrom("01036615138");
        // 받는 사람 번호 설정
        message.setTo(memberPhoneNumber);
        // 인증번호 설정
        message.setText(authNumber);

        
        try { // Nurigo SDK를 사용하여 SMS 메시지 전송
        	messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) { 
        	// 발송에 실패한 메시지 목록을 확인할 수 있습니다!
        	System.out.println(exception.getFailedMessageList());
        	System.out.println(exception.getMessage());
        } catch (Exception exception) {
        	System.out.println(exception.getMessage());
        }
           
		// 메시지가 성공적으로 전송되면 인증번호 반환
		if (message != null) {
			System.out.println("[본승]로그  message " + message);
			System.out.println("[본승]로그 랜덤 인증번호 " + authNumber);
			return authNumber;

		}
		return null;

	}

}
