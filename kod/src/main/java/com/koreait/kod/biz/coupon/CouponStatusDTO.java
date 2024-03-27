package com.koreait.kod.biz.coupon;

import java.util.Date;

import lombok.Data;

@Data
public class CouponStatusDTO {

	private int couponStatusID; // 쿠폰상태 PK
	private int couponID; // 쿠폰 PK
	private String memberID; // 회원 ID
	private Date couponIssueDate; // 쿠폰 발행일
	private Date couponExpireDate; // 쿠폰 만료일
	private int orderContentID; // 주문상세 PK
	
	
	private String couponName; // 쿠폰 이름 
	private String couponContent; // 쿠폰 설명
	private String couponCode; // 쿠폰 코드
	private String couponType; // 쿠폰 타입
	private int couponDiscountRate; // 할인율
	
	private String searchCondition;
	
}
