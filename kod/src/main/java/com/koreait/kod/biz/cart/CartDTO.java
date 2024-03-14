package com.koreait.kod.biz.cart;

import lombok.Data;

@Data
public class CartDTO {
	private int cartID;
	private int productID;
	private int cartProductCnt;
	private int productPrice;
	private int productTotalPrice;
	private String productName;
	private String productImg;
	private String memberID;
	private String paymentProvider; // 신용카드 or 카카오페이 or 네이버페이 ...
	private int paymentType; // 상품 구매 or 장바구니 구매
	private String searchCondition;
	
}
