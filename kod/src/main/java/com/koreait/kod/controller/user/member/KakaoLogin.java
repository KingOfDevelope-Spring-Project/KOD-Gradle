package com.koreait.kod.controller.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class KakaoLogin {

	@Autowired
	LoginService loginService;

	@PostMapping("/getKakaoAuthUrl")
	public @ResponseBody String getKakaoAuthUrl() {
	    System.out.println("[로그:정현진] getKakaoAuthUrl 들어옴");

	    String reqUrl = "https://kauth.kakao.com/oauth/authorize?"
		    	  	  + "client_id=${1054698_REST_API_a20b0a5327457efd072d19a6a022a4fe}"
		    		  + "&redirect_uri=${http://localhost:8088/auth_kakao}"
		    		  + "&response_type=code";

	    System.out.println("[로그:정현진] reqUrl : " + reqUrl);
	    return reqUrl;
	}

	@PostMapping("/auth_kakao")
	public String oauthKakao(
	        @RequestParam(value = "code",required = false) String code
	        , HttpSession session, RedirectAttributes rttr) throws Exception {

		System.out.println("[로그:정현진] oauthKakao 들어옴");
	    System.out.println("[로그:정현진] code : " + code);
	    System.out.println("[로그:정현진] session : " + session);
	    System.out.println("[로그:정현진] rttr : " + rttr);
		
	    log.info("code : " + code);
	    String view = loginService.getUserInfo(code, session);
	    System.out.println("[로그:정현진] view : "+view); // user/main

	    return view;
	}
	
}
