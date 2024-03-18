package com.koreait.kod.biz.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("couponCategoryService")
public class CouponCategoryServiceImpl implements CouponCategoryService {
	
	@Autowired
	CouponCategoryDAO couponCategoryDAO;

	@Override
	public List<CouponCategoryDTO> selectAll(CouponCategoryDTO couponCategoryDTO) {
		return couponCategoryDAO.selectAll(couponCategoryDTO);
	}

	@Override
	public CouponCategoryDTO selectOne(CouponCategoryDTO couponCategoryDTO) {
		return couponCategoryDAO.selectOne(couponCategoryDTO);
	}

	@Override
	public boolean insert(CouponCategoryDTO couponCategoryDTO) {
		return couponCategoryDAO.insert(couponCategoryDTO);
	}

	@Override
	public boolean update(CouponCategoryDTO couponCategoryDTO) {
		return couponCategoryDAO.update(couponCategoryDTO);
	}

	@Override
	public boolean delete(CouponCategoryDTO couponCategoryDTO) {
		return couponCategoryDAO.delete(couponCategoryDTO);
	}

}
