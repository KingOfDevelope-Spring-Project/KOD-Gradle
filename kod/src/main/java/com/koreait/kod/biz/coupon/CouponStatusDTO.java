package com.koreait.kod.biz.coupon;

import java.util.Date;

import lombok.Data;

@Data
public class CouponStatusDTO {

	private int couponStatusID; // 쿠폰상태 PK
	private int couponID; // 쿠폰 PK
	private String memberID; // 회원 ID
	private String CouponIsUsed; // 쿠폰 사용여부 체크
	private Date couponIssueDate; // 쿠폰 발행일
	private Date couponExpireDate; // 쿠폰 만료일
	
}
