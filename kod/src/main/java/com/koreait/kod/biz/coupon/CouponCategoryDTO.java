package com.koreait.kod.biz.coupon;

import lombok.Data;

@Data
public class CouponCategoryDTO {

	int couponCategoryID; // 쿠폰 카테고리 PK
	int couponID; // 쿠폰PK
	int categoryID; // 카테고리 PK

}
