package com.koreait.kod.biz.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("couponStatusDAO")
public class CouponStatusDAO {

	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL="";
	private static final String SELECTONE="";
	private static final String INSERT="INSERT INTO COUPON_STATUS (MEMBER_ID, COUPON_ID, COUPON_EXPIRE_DATE) VALUES (?, ?, DATE_ADD(NOW(), INTERVAL ? DAY))";
	// 쿠폰 사용했을 때 주문상세값 변경
	private static final String UPDATE="UPDATE COUPON_STATUS SET ORDERCONTENT_ID=? WHERE COUPON_ID=? AND MEMBER_ID= ?";
	private static final String DELETE="";

	public List<CouponStatusDTO> selectAll(CouponStatusDTO couponStatusDTO) {
		return null;
	}

	public CouponStatusDTO selectOne(CouponStatusDTO couponStatusDTO) {
		return null;
	}

	public boolean insert(CouponStatusDTO couponStatusDTO) {
		int result= jdbcTemplate.update(INSERT,couponStatusDTO.getMemberID(),couponStatusDTO.getCouponID(),couponStatusDTO.getCouponUseDate());
		
		if(result <=0) {
			return false;
		}
		return true;
	}

	public boolean update(CouponStatusDTO couponStatusDTO) {
		int result= jdbcTemplate.update(UPDATE,couponStatusDTO.getOrderContentID(),couponStatusDTO.getCouponID(),couponStatusDTO.getMemberID());
		
		if(result <= 0) {			
			return false;
		}
		return true;
	}

	public boolean delete(CouponStatusDTO couponStatusDTO) {
		return false;
	}
}

