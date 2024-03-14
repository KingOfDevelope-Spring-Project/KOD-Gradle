package com.koreait.kod.biz.order;

import java.util.Date;

import lombok.Data;

@Data
public class OrderListDTO {
	private int orderListID;
	private Date orderListDate;
	private String memberID;
}
