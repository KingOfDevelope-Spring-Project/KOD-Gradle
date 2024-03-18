package com.koreait.kod.biz.order;

import java.util.Date;

import lombok.Data;

@Data
public class OrderListDTO {
	private int orderListID; // 주문번호
	private Date orderListDate; // 주문일시
	private String memberID; // 회원 ID
	private String couponID; // 쿠폰테이블 PK
}
