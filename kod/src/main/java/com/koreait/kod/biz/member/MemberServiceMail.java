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

    private static final String senderEmail= "ab01036615138@gmail.com";

    
    public void CreateMail(MemberDTO memberDTO){
    	System.out.println("회원 정보"+memberDTO);
    	

    	  MimeMessage message = javaMailSender.createMimeMessage();

          try {
              message.setFrom(senderEmail);
              message.setRecipients(MimeMessage.RecipientType.TO, senderEmail);
              message.setSubject("회원 복구 신청");
              String body = "";
              body += "<h3>" +" 복구신청 회원 이름 [ " + memberDTO.getMemberName() + " ]"+"</h3>";
              body += "<h3>" +" 복구신청 회원 아이디 [ " + memberDTO.getMemberID() + " ]"+"</h3>";
              body += "<h3>" +" 복구신청 회원 전화번호 [ " + memberDTO.getMemberPhoneNumber() + " ]"+"</h3>";
              body += "<h3>" +" 복구신청 회원 이메일 [ " + memberDTO.getMemberEmail() + " ]"+"</h3>";
              message.setText(body,"UTF-8", "html");
          } catch (MessagingException e) {
              e.printStackTrace();
          }
         
        javaMailSender.send(message);
      }

    }
