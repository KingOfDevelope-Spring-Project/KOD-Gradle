package com.koreait.kod.controller.user.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapPage {
	
	@GetMapping("/mapPage")
	public String mapPage() {
		
		return "common/map";
	}

}
