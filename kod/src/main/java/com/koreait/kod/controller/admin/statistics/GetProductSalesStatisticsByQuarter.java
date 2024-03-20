package com.koreait.kod.controller.admin.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetProductSalesStatisticsByQuarter {
	@Autowired
	ProductService	productService;

	public String getProductSalesStatisticsByQuarter(ProductDTO productDTO,Model model,MemberDTO adminDTO,HttpSession session) {
		
		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
			return "common/error";
		}
		
		productDTO.setSearchCondition("getProductSalesStatisticsByQuarter");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		
		return "admin/Statistics/ProductSalesStatisticsByQuarter";
	}
}
