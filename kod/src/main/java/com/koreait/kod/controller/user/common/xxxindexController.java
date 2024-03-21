package com.koreait.kod.controller.user.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class xxxindexController {

	@RequestMapping(value = "/1111",method = RequestMethod.GET)
	public String index() {
		
		return "/mainPage";
	}
}

/*
 * forward"는 서버 내에서의 이동이며, 
 * "redirect"는 클라이언트에게 새로운 URL로 이동하라는 명령을 보내는 것입니다. 
 * 따라서 "forward"는 서버에서 직접적인 요청 처리를 하고, 
 * "redirect"는 클라이언트에게 새로운 URL로 이동하라는 명령을 보내고 해당 URL을 요청
 */
