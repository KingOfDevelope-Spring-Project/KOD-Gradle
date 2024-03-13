package com.koreait.kod.controller.user.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.kod.model.address.AddressDTO;
import com.koreait.kod.model.address.AddressService;

@Controller
public class AddressDeleteController {

	@Autowired
	private AddressService addressService;
	
	@RequestMapping("/addressDelete")
	public String addressDelete(AddressDTO addressDTO, Model model) {
		System.out.println("[로그 : 조형련] AddressDeleteAction 시작");
		
		// Command 객체 멤버변수명과
		// 요청 파라미터 이름이 같은지 확인할것!

		/*[조형련] 받아온 값이 null이 아니거나, 공백이아니라면 */
		if (addressDTO.getXxx() != null && !addressDTO.getXxx().isEmpty()) {
			try {		/*[조형련] try-catch를 이용한 ID값 검증*/
				int adrsId = Integer.parseInt(addressDTO.getXxx()); /*[조형련] 문자열 타입으로 저장한 PK값을 정수형으로 변환 */
				addressDTO.setAdrsID(adrsId); /*[조형련] 해당 객체에 받아온 정보를 저장*/
				//		        System.out.println(addressDTO);
			} catch (NumberFormatException e) {
				e.printStackTrace(); 
				//		        System.out.println("addressDelete tryCatch 이유 : PK에러)");
			}
		} 
		else {
		}

		if (!addressService.delete(addressDTO)) { 
			model.addAttribute("msg", "삭제에 실패했습니다.");
			return "goback";
		} 
		System.out.println("[로그 : 조형련] AddressDeleteAction 끝");
		return "redirect:myPage";
	}
	
}
