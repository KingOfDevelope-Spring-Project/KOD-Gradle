package com.koreait.kod.controller.user.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;
import com.koreait.kod.biz.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping("/getMemberAddress")
	public @ResponseBody String getMemberAddress(AddressDTO addressDTO,MemberDTO memberDTO,Model model,HttpSession session) {
		
		addressDTO.setMemberID((String)session.getAttribute("memberID"));
		model.addAttribute("addressDatas", addressService.selectAll(addressDTO));
		
		return "memberAddressPage";
	}
	
	@PostMapping("/addressInsert")
	public String insertAddress(AddressDTO addressDTO,Model model,HttpSession session) {
		
		addressDTO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = addressService.insert(addressDTO);
		if (!flag) { 
			model.addAttribute("msg", "주소등록 실패");
			return "goback";
		}
		return "redirect:myPage";
	}
	
	@PostMapping("/addressUpdate")
	public String updateAddress(AddressDTO addressDTO,Model model,HttpSession session) {
		
		addressDTO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = addressService.update(addressDTO);
		if (!flag) { 
			model.addAttribute("msg", "주소변경 실패");
			return "goback";
		}
		return "redirect:myPage";
	}
	
	@PostMapping("/addressDelete")
	public String deleteAddress(AddressDTO addressDTO, Model model,HttpSession session) {

		addressDTO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = addressService.delete(addressDTO);
		if (!flag) { 
			model.addAttribute("msg", "주소삭제 실패");
			return "goback";
		}
		return "redirect:myPage";
	}
}
