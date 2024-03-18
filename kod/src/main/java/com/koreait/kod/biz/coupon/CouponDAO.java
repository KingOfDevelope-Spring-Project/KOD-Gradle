package com.koreait.kod.biz.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("couponDAO")
public class CouponDAO {

	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired
	// 쿠폰 전체 조회
	private static final String SELECTALL="SELECT COUPON_ID,"
			+ "COUPON_CONTENT,"
			+ "COUPON_DISCOUNT_RATE,"
			+ "COUPON_DISCOUNT_MAX,"
			+ "COUPON_NAME,COUPON_CODE,"
			+ "COUPON_USE_DATE,"
			+ "COUPON_TYPE,"
			+ "COUPON_MIN_PRICE FROM COUPON";
	// 쿠폰 개별 조회	
	private static final String SELECTONE="SELECT COUPON_ID," 
			+ "COUPON_CONTENT,"
			+ "COUPON_DISCOUNT_RATE,"
			+ "COUPON_DISCOUNT_MAX,COUPON_NAME,"
			+ "COUPON_CODE,"
			+ "COUPON_USE_DATE,"
			+ "COUPON_TYPE,"
			+ "COUPON_MIN_PRICE FROM COUPON "
			+ "WHERE COUPON_ID = ?";
	// 쿠폰 추가 	
	private static final String INSERT="INSERT INTO COUPON"
			+ "(COUPON_CONTENT,"
			+ "COUPON_DISCOUNT_RATE,"
			+ "COUPON_DISCOUNT_MAX,"
			+ "COUPON_NAME,"
			+ "COUPON_CODE,"
			+ "COUPON_USE_DATE,"
			+ "COUPON_TYPE,"
			+ "COUPON_MIN_PRICE) VALUES('?',?,?,'?',FLOOR(RAND()* 100000000),?,'?',?)";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<CouponDTO> selectAll(CouponDTO couponDTO) {
		return null;
	}

	public CouponDTO selectOne(CouponDTO couponDTO) {
		return null;
	}

	public boolean insert(CouponDTO couponDTO) {
		return false;
	}

	public boolean update(CouponDTO couponDTO) {
		return false;
	}

	public boolean delete(CouponDTO couponDTO) {
		return false;
	}
}
