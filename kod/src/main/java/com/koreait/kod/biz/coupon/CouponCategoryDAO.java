package com.koreait.kod.biz.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("couponCategoryDAO")
public class CouponCategoryDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL="";
	private static final String SELECTONE="";
	private static final String INSERT="INSERT INTO COUPON_CATEGORY(COUPON_ID,CATEGORY_ID) VALUES (?,?)";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<CouponCategoryDTO> selectAll(CouponCategoryDTO couponCategoryDTO) {
		return null;
	}

	public CouponCategoryDTO selectOne(CouponCategoryDTO couponCategoryDTO) {
		return null;
	}

	public boolean insert(CouponCategoryDTO couponCategoryDTO) {
		int result = jdbcTemplate.update(INSERT, couponCategoryDTO.getCouponID(),couponCategoryDTO.getCategoryID());
		if(result <=0) {
			return false;
		}
		return true;
	}

	public boolean update(CouponCategoryDTO couponCategoryDTO) {
		return false;
	}

	public boolean delete(CouponCategoryDTO couponCategoryDTO) {
		return false;
	}

}
