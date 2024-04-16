package com.koreait.kod.biz.member;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class MemberServiceMail {

    private final JavaMailSender javaMailSender;

    // 발신자 이메일 주소
    private static final String senderEmail= "ab01036615138@gmail.com";

    // 이메일 발송 메서드
    public void CreateMail(MemberDTO memberDTO){
    	System.out.println("회원 정보"+memberDTO);
    	
    	// MimeMessage를 생성
    	// Spring Framework 에서 제공하는 JavaMailsender 인터페이스를 사용하여 Mime형식의 이메일 메시지 객체를 생성하는 부분 
    	// Mime형식은 이메일의 복잡한 구조를 다룰수 있는 표준형식   이메일의 제목,본문,첨부파일등을 다룰수 있습니다. 
    	// javaMailSender 이메일을 발송하는 데 사용하는 javaMailSender 인터페이스의 구현체
    	MimeMessage message = javaMailSender.createMimeMessage();

          try {
             // 발신자 이메일 설정
        	  message.setFrom(senderEmail);
              // 수신자 이메일 설정
        	  message.setRecipients(MimeMessage.RecipientType.TO, senderEmail);
              // 이메일 제목 설정
              message.setSubject("회원 복구 신청");
              // 이메일 본문 설정
              String body = "";
              body += "<h3>" +" 복구신청 회원 이름 [ " + memberDTO.getMemberName() + " ]"+"</h3>";
              body += "<h3>" +" 복구신청 회원 아이디 [ " + memberDTO.getMemberID() + " ]"+"</h3>";
              body += "<h3>" +" 복구신청 회원 전화번호 [ " + memberDTO.getMemberPhoneNumber() + " ]"+"</h3>";
              body += "<h3>" +" 복구신청 회원 이메일 [ " + memberDTO.getMemberEmail() + " ]"+"</h3>";
              message.setText(body,"UTF-8", "html");
          } catch (MessagingException e) {
             // 예외 발생 시 출력
        	  e.printStackTrace();
          }
         
          // 이메일 전송
        javaMailSender.send(message);
      }

    }
