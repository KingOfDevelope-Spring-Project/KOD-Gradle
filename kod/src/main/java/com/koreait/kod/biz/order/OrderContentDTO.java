package com.koreait.kod.biz.order;

import java.util.Date;

import lombok.Data;

@Data
public class OrderContentDTO {
	private int orderContentID; //상품별 주문번호
	private int orderListID; // 주문번호
	private int productID; // 상품번호
	private int orderContentCnt; // 주문개수 (구매개수)
	private int productPrice; // 상품가격
	private String productName; // 상품명
	private String productCategory; // 상품 카테고리
	private String productImg;  // 상품이미지
	private String memberID; // 회원ID
	private int categoryID; 
	
	private int couponID;
	private String couponName;
	private int couponDiscountRate;
	private int couponDiscountMaxPrice;
	private int discountPrice; // 할인금액
	private int paymentPrice; // 상품 결제금액
	private Date orderListDate;
	private String searchCondition;
	 
}
