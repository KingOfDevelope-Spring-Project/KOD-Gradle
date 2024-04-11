package com.koreait.kod.controller.admin.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.controller.admin.excel.service.ExcelOfProductService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ExcelController {
	@Autowired
	ExcelOfProductService excelOfProductService;
	
	@GetMapping("/excel/product")
	public void getProductExcel(HttpServletResponse response, ProductDTO productDTO) {
		try {
			excelOfProductService.download(response, productDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}