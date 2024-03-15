package com.koreait.kod.biz.order;

import lombok.Data;

@Data
public class OrderContentDTO {
	private int orderContentID;
	private int orderListID;
	private int productID;
	private int orderContentCnt;
	private int productPrice;
	private String productName;
	private String productCategory; 
	private String productImg;
	private String memberID;
	private String searchCondition;
	
}
