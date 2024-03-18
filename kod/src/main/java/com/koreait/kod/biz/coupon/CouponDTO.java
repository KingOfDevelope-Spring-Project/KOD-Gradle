package com.koreait.kod.biz.coupon;

import java.util.Date;

import lombok.Data;

@Data
public class CouponDTO {
	
	private int couponID;
	private String couponName;
	private String couponContent;
	private String couponCode;
	private String couponType;
	private int couponDiscountRate;
	private int couponDiscountMaxPrice;
	private int couponUseMinPrice;
	private Date couponUseDate;
	private String searchCondition;

}
