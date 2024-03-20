package com.koreait.kod.controller.user.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RemoveAddress {
	@Autowired
	private AddressService addressService;
		
	@PostMapping("/deleteAddress")
	public String deleteAddress(AddressDTO addressDTO, Model model,HttpSession session) {

		try {
			addressDTO.setMemberID((String)session.getAttribute("memberID"));
		} catch (Exception e) {
			System.out.println("[로그:정현진] memberID is null");
		}
		
		boolean flag = addressService.delete(addressDTO);
		if (!flag) { 
			model.addAttribute("msg", "주소삭제 실패");
			return "common/goback";
		}
		return "user/mypage/mypage";
	}
}
