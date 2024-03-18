package com.koreait.kod.biz.coupon;

import java.util.Date;

import lombok.Data;

@Data
public class CouponStatusDTO {

	private int couponStatusID;
	private int couponID;
	private String memberID;
	private String CouponIsUsed;
	private Date couponIssueDate;
	private Date couponExpireDate;
	
}
