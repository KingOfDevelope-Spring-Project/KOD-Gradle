package com.koreait.kod.biz.coupon;

import java.util.List;

public interface CouponService {
	
	List<CouponDTO> selectAll(CouponDTO couponDTO);
	CouponDTO selectOne(CouponDTO couponDTO);
	
	boolean insert(CouponDTO couponDTO);
	boolean update(CouponDTO couponDTO);
	boolean delete(CouponDTO couponDTO);
}
