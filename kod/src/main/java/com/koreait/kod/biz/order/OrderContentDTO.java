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
	private String productName; 
	private String productCategory;
	private String productImg; 
	private String memberID; 
	private int categoryID;
	
	private String couponName;
	private int couponDiscountRate;
	private String couponCheck;
	private Date orderListDate;
	private String searchCondition;
	
}
