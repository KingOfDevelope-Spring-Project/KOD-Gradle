package com.koreait.kod.controller.user.orderAndPay;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.productAndWishList.ProductDTO;

@Controller
public class GetKakaoPayPage {

	@GetMapping("/getKakaoPayPage")
	public String getKakaoPayPage(List<ProductDTO> productList,Model model) {

		if(productList == null || productList.size()==0) { // 구매할 상품들이 없다면
			return "common/error";
		}
		
		List<ProductDTO> productDatas = new ArrayList<ProductDTO>();
		for (ProductDTO product : productList) {
			ProductDTO data = new ProductDTO();
			data.setProductID(product.getProductID());
			data.setProductCnt(product.getProductCnt());
			data.setPurchaseType(product.getPurchaseType());
			productDatas.add(data);
		}
		model.addAttribute("productDatas", productDatas);
		
		
		
		return "user/pay/kakaoPay";
	}
	
}
