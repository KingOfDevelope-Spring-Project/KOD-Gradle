package com.koreait.kod.controller.admin.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.controller.admin.excel.service.ExcelOfMemberService;
import com.koreait.kod.controller.admin.excel.service.ExcelOfProductService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ExcelController {
	@Autowired
	ExcelOfProductService excelOfProductService;
	@Autowired
	ExcelOfMemberService excelOfMemberService;
	
	@GetMapping("/excel/product")
	public void getProductExcel(HttpServletResponse response, ProductDTO productDTO) {
		try {
			excelOfProductService.download(response, productDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/excel/member")
	public void getMemberExcel(HttpServletResponse response, MemberDTO memberDTO) {
		try {
			excelOfMemberService.download(response, memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}