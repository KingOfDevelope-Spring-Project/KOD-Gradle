package com.koreait.kod.biz.order;

import java.util.Date;

import lombok.Data;

@Data
public class OrderListDTO {
	private int orderListID; // 주문번호
	private Date orderListDate; // 주문일시
	private int orderListCntToday; // 오늘 주문건수
	private int orderListCntYesterday; // 어제 주문건수
	private String memberID; // 회원 ID
	private String searchCondition;
}
