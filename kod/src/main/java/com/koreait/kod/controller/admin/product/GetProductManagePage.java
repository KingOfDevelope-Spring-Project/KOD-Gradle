package com.koreait.kod.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

@Controller
public class GetProductManagePage {
	
	@Autowired
	ProductService productService;

	@GetMapping("/getProductManagePage")
	public String getProductManagePage(ProductDTO productDTO,Model model) {
		
		// 상품정보(상품ID,카테고리,브랜드,이름,재고,상품가격) -> 재고부족 데이터는 뷰에서 정렬을통해 순서대로 얻을 수 있음
		// 만약 재고가 5개 이하라면 css 적용 등을 통해 뷰에서 구현가능한 영역임, 일 주문건수은 필요가 없어보임
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		
		
		
		
		return "admin/product/productManage";
	}
}
