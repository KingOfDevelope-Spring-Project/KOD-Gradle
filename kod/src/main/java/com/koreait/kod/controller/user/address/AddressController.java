package com.koreait.kod.controller.user.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.kod.biz.address.AddressDTO;
import com.koreait.kod.biz.address.AddressService;
import com.koreait.kod.biz.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller("/address/*")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "/select",method = RequestMethod.GET)
	public @ResponseBody String getMemberAddress(AddressDTO addressDTO,MemberDTO memberDTO,Model model,HttpSession session) {
		
		addressDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		model.addAttribute("addressDatas", addressService.selectAll(addressDTO));
		
		return "memberAddressPage";
	}
	
	@RequestMapping(value = "insert",method = RequestMethod.POST)
	public String addressInsert(AddressDTO addressDTO,Model model,HttpSession session) {
		
		addressDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		
		boolean flag = addressService.insert(addressDTO);
		if (!flag) { 
			model.addAttribute("msg", "주소등록 실패");
			return "goback";
		}
		return "redirect:myPage";
	}
	
	@RequestMapping(value = "update",method = RequestMethod.POST)
	public String addressUpdate(AddressDTO addressDTO,Model model,HttpSession session) {
		
		addressDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		
		boolean flag = addressService.update(addressDTO);
		if (!flag) { 
			model.addAttribute("msg", "주소변경 실패");
			return "goback";
		}
		return "redirect:myPage";
	}
	
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	public String addressDelete(AddressDTO addressDTO, Model model,HttpSession session) {

		addressDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		
		boolean flag = addressService.delete(addressDTO);
		if (!flag) { 
			model.addAttribute("msg", "주소삭제 실패");
			return "goback";
		}
		return "redirect:myPage";
	}
}
