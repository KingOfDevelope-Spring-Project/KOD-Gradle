package com.koreait.kod.controller.user.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;
import com.koreait.kod.biz.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetAddressListPage {
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/getAddressList")
	public @ResponseBody String getAddressList(AddressDTO addressDTO,MemberDTO memberDTO,Model model,HttpSession session) {
		
		try {
			addressDTO.setMemberID((String)session.getAttribute("memberID"));
		} catch (Exception e) {
			System.out.println("[로그:정현진] memberID is null");
		}
		
		model.addAttribute("addressDatas", addressService.selectAll(addressDTO));
		
		return "user/mypage/memberAddress";
	}
}
