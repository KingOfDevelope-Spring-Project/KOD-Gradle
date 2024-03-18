package com.koreait.kod.biz.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("couponStatusService")
public class CouponStatusServiceImpl implements CouponStatusService {
	
	@Autowired
	CouponStatusDAO couponStatusDAO;

	@Override
	public List<CouponStatusDTO> selectAll(CouponStatusDTO couponStatusDTO) {
		return couponStatusDAO.selectAll(couponStatusDTO);
	}

	@Override
	public CouponStatusDTO selectOne(CouponStatusDTO couponStatusDTO) {
		return couponStatusDAO.selectOne(couponStatusDTO);
	}

	@Override
	public boolean insert(CouponStatusDTO couponStatusDTO) {
		return couponStatusDAO.insert(couponStatusDTO);
	}

	@Override
	public boolean update(CouponStatusDTO couponStatusDTO) {
		return couponStatusDAO.update(couponStatusDTO);
	}

	@Override
	public boolean delete(CouponStatusDTO couponStatusDTO) {
		return couponStatusDAO.delete(couponStatusDTO);
	}
}
