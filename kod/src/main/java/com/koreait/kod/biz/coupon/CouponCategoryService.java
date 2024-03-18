package com.koreait.kod.biz.coupon;

import java.util.List;

public interface CouponCategoryService {

	List<CouponCategoryDTO> selectAll(CouponCategoryDTO couponCategoryDTO);
	CouponCategoryDTO selectOne(CouponCategoryDTO couponCategoryDTO);
	boolean insert(CouponCategoryDTO couponCategoryDTO);
	boolean update(CouponCategoryDTO couponCategoryDTO);
	boolean delete(CouponCategoryDTO couponCategoryDTO);
}
