package com.koreait.kod.biz.coupon;

import java.util.Date;

import lombok.Data;

@Data
public class CouponDTO {
	
	private int couponID; // 쿠폰 PK
	private String couponName; // 쿠폰명
	private String couponContent; // 쿠폰 설명
	private String couponCode; // 쿠폰 코드
	private String couponType; // 쿠폰 타입
	private int couponDiscountRate; // 할인율
	private int couponDiscountMaxPrice; // 최대 할인가격
	private int couponUseMinPrice; // 쿠폰사용 최소 금액
	private int couponUseDate; // 쿠폰 사용기간
	
	private Date couponIssueDate;
	private String memberID;
	private int couponStatusID;
	private Date orderListDate;
	private Date couponExpireDate;
	private String searchCondition;
	private String couponStatus;
	private int categoryID;
	
	private int orderContentID;
	

}
