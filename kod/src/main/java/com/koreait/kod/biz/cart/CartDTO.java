package com.koreait.kod.biz.cart;

import lombok.Data;

@Data
public class CartDTO {
	private int cartID; // 장바구니 PK
	private int productID; // 상품번호
	private int cartProductCnt; // 장바구니에 담긴 상품 수량
	private int productPrice; // 상품 가격
	private int productTotalPrice; // 상품 총계
	private String productName; // 상품 이름
	private String productImg; 
	private String memberID; 
	private String paymentProvider; // 신용카드 or 카카오페이 or 네이버페이 ...
	private int payCk; // 상품 구매 or 장바구니 구매
	private String searchCondition;
	private int imageID;
	private int categoryID;
		// TODO Auto-generated method stub
		
	}
	

