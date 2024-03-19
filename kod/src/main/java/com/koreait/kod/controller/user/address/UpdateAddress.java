package com.koreait.kod.controller.user.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateAddress {
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/updateAddress")
	public String updateAddress(AddressDTO addressDTO,Model model,HttpSession session) {
		
		try {
			addressDTO.setMemberID((String)session.getAttribute("memberID"));
		} catch (Exception e) {
			System.out.println("[로그:정현진] memberID is null");
		}
		
		boolean flag = addressService.update(addressDTO);
		if (!flag) { 
			model.addAttribute("msg", "주소변경 실패");
			return "common/goback";
		}
		return "user/mypage/mypage";
	}
}
