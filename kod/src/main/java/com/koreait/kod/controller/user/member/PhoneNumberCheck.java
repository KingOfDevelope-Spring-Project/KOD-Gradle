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
	
    @Autowired
    MemberService memberService;
    
    @GetMapping("/phoneNumberCheck")
    public @ResponseBody String phoneNumberCheck(MemberDTO memberDTO , Random rand , Message message) {
        System.out.println("[로그:구본승] 인증번호 발송 들어옴");
        
        
     
        int randNum = rand.nextInt(900000) + 100000;
        
        String memberPhoneNumber = memberDTO.getMemberPhoneNumber();
        System.out.println("전화번호 인증 : " + memberPhoneNumber);
        // 폰에는 우리페이지에 나눠서 입력받는전화번호를 합성하여 요청
       
        String authNumber = String.valueOf(randNum);
        System.out.println("랜덤 인증번호 : " + authNumber);
        
        
        
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSYIJZKHSBNFC8A", "XAGL0XDW5MWDBMWSRRCMXYKSP7GOA6UE", "https://api.coolsms.co.kr"); // Message
      
        message.setFrom("01036615138");
        message.setTo(memberPhoneNumber);
        message.setText(authNumber);

        
        try { // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
        	messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) { // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
        	System.out.println(exception.getFailedMessageList());
        	System.out.println(exception.getMessage());
        } catch (Exception exception) {
        	System.out.println(exception.getMessage());
        }
        
        
        // 논리형 변수 flag에 false저장
       // boolean flag=false;
        
        // 만약 MemberDAO 에서 반환받은mDTO가 값이null 이면 flag변수에 true 저장 
        if(message!=null) {
        	System.out.println("[본승]로그  message "+message);
        	System.out.println("[본승]로그 랜덤 인증번호 "+authNumber);
        	return authNumber;
        	//flag=true;
        }
       // System.out.println("[본승]로그 메시지 전송 상태 "+flag);
        return null;
      
      
    }

}
