package com.koreait.kod.biz.coupon;

import java.util.List;

public interface CouponStatusService {
	
	List<CouponStatusDTO> selectAll(CouponStatusDTO couponStatusDTO);
	CouponStatusDTO selectOne(CouponStatusDTO couponStatusDTO);
	
	boolean insert(CouponStatusDTO couponStatusDTO);
	boolean update(CouponStatusDTO couponStatusDTO);
	boolean delete(CouponStatusDTO couponStatusDTO);
}
