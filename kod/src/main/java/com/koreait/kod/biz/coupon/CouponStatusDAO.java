package com.koreait.kod.biz.coupon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("couponStatusDAO")
public class CouponStatusDAO {

	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL_SEARCH_COUPON="SELECT "
			+ "C.COUPON_NAME, "
			+ "C.COUPON_CODE, "
			+ "C.COUPON_CONTENT, "
			+ "C.COUPON_DISCOUNT_RATE, "
			+ "C.COUPON_TYPE, "
			+ "CS.COUPON_ISSUE_DATE, "
			+ "CS.COUPON_EXPIRE_DATE, "
			+ "CS.ORDERCONTENT_ID, "
			+ "CS.MEMBER_ID "
			+ "FROM COUPON_STATUS CS "
			+ "JOIN COUPON C "
			+ "ON CS.COUPON_ID = C.COUPON_ID "
			+ "WHERE 1=1 AND MEMBER_ID=?";
	private static final String SELECTONE="";
	private static final String INSERT="INSERT INTO COUPON_STATUS(MEMBER_ID,COUPON_ID,COUPON_EXPIRE_DATE) VALUES (?,?,?)";
	// 쿠폰 사용했을 때 주문상세값 변경
	private static final String UPDATE="UPDATE COUPON_STATUS SET ORDERCONTENT_ID=? WHERE COUPON_STATUS_ID = ?";
	private static final String DELETE="";

	public List<CouponStatusDTO> selectAll(CouponStatusDTO couponStatusDTO) {
		
		StringBuilder builder=new StringBuilder(SELECTALL_SEARCH_COUPON);
		
		if(couponStatusDTO.getSearchCondition().equals("expiredCoupon")) {
			builder.append("AND CS.COUPON_EXPIRE_DATE < NOW()");
		}else if(couponStatusDTO.getSearchCondition().equals("usedCoupon")) {
			builder.append("AND CS.ORDERCONTENT_ID IS NOT NULL AND CS.COUPON_EXPIRE_DATE > NOW()");
		}else if(couponStatusDTO.getSearchCondition().equals("unUsedCoupon")) {
			builder.append("AND CS.ORDERCONTENT_ID IS NULL AND CS.COUPON_EXPIRE_DATE > NOW()");
		}
		String query=builder.toString();
		Object[] args= {couponStatusDTO.getMemberID()};
		try {
			return jdbcTemplate.query(query, args, new CouponStatusRowMapper());
		}catch(Exception e) {
			return null;
		}
	}

	public CouponStatusDTO selectOne(CouponStatusDTO couponStatusDTO) {
		return null;
	}

	public boolean insert(CouponStatusDTO couponStatusDTO) {
		int result= jdbcTemplate.update(INSERT,couponStatusDTO.getMemberID(),couponStatusDTO.getCouponID(),couponStatusDTO.getCouponExpireDate());
		
		if(result <=0) {
			return false;
		}
		return true;
	}

	public boolean update(CouponStatusDTO couponStatusDTO) {
		int result= jdbcTemplate.update(UPDATE,couponStatusDTO.getOrderContentID(),couponStatusDTO.getCouponStatusID());
		
		if(result <= 0) {			
			return false;
		}
		return true;
	}

	public boolean delete(CouponStatusDTO couponStatusDTO) {
		return false;
	}
}

class CouponStatusRowMapper implements RowMapper<CouponStatusDTO> {

	@Override
	public CouponStatusDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponStatusDTO couponStatusDTO=new CouponStatusDTO();
		couponStatusDTO.setCouponName(rs.getString("COUPON_NAME"));
		couponStatusDTO.setCouponCode(rs.getString("COUPON_CODE"));
		couponStatusDTO.setCouponContent(rs.getString("COUPON_CONTENT"));
		couponStatusDTO.setCouponDiscountRate(rs.getInt("COUPON_DISCOUNT_RATE"));
		couponStatusDTO.setCouponType(rs.getString("COUPON_TYPE"));
		couponStatusDTO.setCouponIssueDate(rs.getDate("COUPON_ISSUE_DATE"));
		couponStatusDTO.setCouponExpireDate(rs.getDate("COUPON_EXPIRE_DATE"));
		couponStatusDTO.setOrderContentID(rs.getInt("ORDERCONTENT_ID"));
		couponStatusDTO.setMemberID(rs.getString("MEMBER_ID"));
		return couponStatusDTO;
	}
	
}
