package com.koreait.kod.controller.user.orderAndPay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.cart.CartDTO;
import com.koreait.kod.biz.cart.CartService;
import com.koreait.kod.biz.order.OrderContentDTO;
import com.koreait.kod.biz.order.OrderContentService;
import com.koreait.kod.biz.order.OrderListDTO;
import com.koreait.kod.biz.order.OrderListService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AsyncPayment {
	
	@Autowired
	OrderListService orderListService;
	@Autowired
	OrderContentService orderContentService;
	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;

	@PostMapping("/asyncPayment")
	public boolean asyncPayment(List<ProductDTO> productList,ProductDTO productDTO,OrderListDTO orderListDTO,OrderContentDTO orderContentDTO,CartDTO cartDTO,Model model,HttpSession session) {
		
		orderListDTO.setMemberID((String)session.getAttribute("memberID"));
		boolean flag = orderListService.insert(orderListDTO);
		
		if(flag==true) { // 주문번호가 생성되었다면
			for (ProductDTO product : productList) { // 구매로직 실행
				orderListDTO.setMemberID((String)session.getAttribute("memberID"));
				int orderListID = orderListService.selectOne(orderListDTO).getOrderListID();
				orderContentDTO.setOrderListID(orderListID);
				orderContentDTO.setProductID(product.getProductID());
				orderContentDTO.setOrderContentCnt(product.getProductCnt());
				flag = orderContentService.insert(orderContentDTO);
				
				// 구매한 상품 재고변경
				ProductDTO data = new ProductDTO();
				data.setProductCnt(orderContentDTO.getOrderContentCnt());
				data.setProductID(orderContentDTO.getProductID());
				productService.update(productDTO);
				if(product.getPurchaseType()==0) { // 선택구매 일 경우 선택상품 장바구니 비우기
					cartDTO.setProductID(orderContentDTO.getProductID());
					cartDTO.setMemberID((String)session.getAttribute("memberID"));
					cartDTO.setSearchCondition("개별상품삭제");
					flag = cartService.delete(cartDTO);
				}// if(선택구매일 경우)
			}// for(구매로직)
		} // if(주문번호가 생성되었다면)
		
		return flag;
	}
}
