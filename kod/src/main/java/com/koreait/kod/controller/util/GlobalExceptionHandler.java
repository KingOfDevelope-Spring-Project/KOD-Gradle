package com.koreait.kod.controller.util;

import java.nio.file.AccessDeniedException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(CustomAccessDeniedException ex,Model model) {
    	System.out.println("[로그:정현진] handleAccessDeniedException 들어옴");
    	/*
    	 * 예외처리 한글코딩
    	 * 요청한 주체가 사용자라면 메인페이지로 이동
    	 * 관리자라면 관리자메인페이지로 이동시켜야함
    	 * 
    	 * 1. 요청한 주체가 누구인지 받아와야함
    	 * 2. 요청한 주체에 따라 뷰에게 페이지 주소를 달리 보내야함 -> 뷰에게 반환하는 데이터는 model에 담아야함
    	 * 	2-1.  사용자 또는 관리자에 대한 정보를 model에 담아야 한다면 
    	 *  AccessDeniedException의 생성자를 이용하여 정보를 전달할 수 있음
    	 * 3. 뷰는 href의 값을 변수로 지정해야함 -> js로 해결해야함을 의미
    	 * 
    	 */
    	String role = ex.getRole();
        // 여기서 role을 이용하여 사용자 또는 관리자에 대한 처리를 수행하고 model에 추가합니다.
    	System.out.println("[로그:정현진] 요청한 주체 : "+role);
        model.addAttribute("role", role);
        
        return "common/error"; 
    }
}


