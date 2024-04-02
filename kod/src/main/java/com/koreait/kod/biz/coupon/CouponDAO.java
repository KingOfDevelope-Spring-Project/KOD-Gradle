package com.koreait.kod.biz.coupon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("couponDAO")
public class CouponDAO {

	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired
	// 쿠폰 전체 조회
	private static final String SELECTALL="SELECT COUPON_ID,"
			+ "COUPON_CONTENT,"
			+ "COUPON_DISCOUNT_RATE,"
			+ "COUPON_NAME,"
			+ "COUPON_CODE,"
			+ "COUPON_USE_DATE,"
			+ "COUPON_TYPE "
			+ "FROM COUPON";
	// 쿠폰 사용여부 조회 (관리자)
	private static final String SELECTALL_SEARCH_COUPONS="SELECT "
			+ "C.COUPON_ID, "
			+ "C.COUPON_NAME, "
			+ "C.COUPON_CODE, "
			+ "C.COUPON_CONTENT, "
			+ "C.COUPON_DISCOUNT_RATE, "
			+ "C.COUPON_TYPE, "
			+ "CS.COUPON_ISSUE_DATE, "
			+ "CS.COUPON_EXPIRE_DATE, "
			+ "CS.ORDERCONTENT_ID, "
			+ "CS.MEMBER_ID "
			+ "FROM COUPON C "
			+ "JOIN COUPON_STATUS CS "
			+ "ON C.COUPON_ID = CS.COUPON_ID "
			+ "WHERE 1=1 AND MEMBER_ID=? ";
	// 쿠폰 개별조회
	private static final String SELECT_AVAILABLE_COUPONS_PRE_ORDER="SELECT "
			+ "C.COUPON_ID, "
			+ "C.COUPON_NAME, "
			+ "C.COUPON_CODE, "
			+ "C.COUPON_DISCOUNT_RATE, "
			+ "CS.COUPON_STATUS_ID "
			+ "FROM COUPON C "
			+ "JOIN COUPON_STATUS CS "
			+ "ON C.COUPON_ID =CS.COUPON_ID "
			+ "WHERE MEMBER_ID =? AND CS.ORDERCONTENT_ID IS NULL AND CS.COUPON_EXPIRE_DATE > NOW()"; 
	
	private static final String SELECTONE="SELECT COUPON_ID, "
			+ "COUPON_CONTENT, "
			+ "COUPON_DISCOUNT_RATE, "
			+ "COUPON_NAME, "
			+ "COUPON_CODE, "
			+ "COUPON_USE_DATE, "
			+ "COUPON_TYPE "
			+ "FROM COUPON "
			+ "WHERE COUPON_CODE=? ";
	private static final String SELECTONE_COUPON_ID=
			"SELECT COUPON_ID, "
			+ "FROM COUPON "
			+ "WHERE COUPON_CODE=? ";
	// 쿠폰 추가(코드 랜덤난수)
	private static final String INSERT_RANDOMCODE="INSERT INTO COUPON"
			+ "(COUPON_CONTENT,"
			+ "COUPON_DISCOUNT_RATE,"
			+ "COUPON_DISCOUNT_MAX_PRICE,"
			+ "COUPON_NAME,"
			+ "COUPON_CODE,"
			+ "COUPON_USE_DATE,"
			+ "COUPON_TYPE,"
			+ "COUPON_MIN_PRICE) VALUES(?,?,?,?,FLOOR(RAND()* 100000000),?,?,?)";
	// 쿠폰추가 (코드 직접입력)
	private static final String INSERT="INSERT INTO COUPON"
			+ "(COUPON_CONTENT,"
			+ "COUPON_DISCOUNT_RATE,"
			+ "COUPON_DISCOUNT_MAX_PRICE,"
			+ "COUPON_NAME,"
			+ "COUPON_CODE,"
			+ "COUPON_USE_DATE,"
			+ "COUPON_TYPE,"
			+ "COUPON_USE_MIN_PRICE) VALUES(?,?,?,?,?,?,?,?)";
	private static final String UPDATE="UPDATE COUPON SET "
			+ "COUPON_CONTENT =?, "
			+ "COUPON_DISCOUNT_RATE =?,"
			+ "COUPON_DISCOUNT_MAX=?, "
			+ "COUPON_NAME=?,"
			+ "COUPON_CODE =?, "
			+ "COUPON_USE_DATE =?,"
			+ "COUPON_TYPE=?,"
			+ "COUPON_USE_MIN_PRICE=? "
			+ "WHERE COUPON_ID =?";
	private static final String DELETE="DELETE FROM COUPON WHERE COUPON_ID=?";

	
	public List<CouponDTO> selectAll(CouponDTO couponDTO){
		StringBuilder builder=new StringBuilder(SELECTALL_SEARCH_COUPONS);
		Object[] args= {couponDTO.getMemberID()};
		if(couponDTO.getSearchCondition().equals("expiredCoupon")) {
			builder.append("AND CS.COUPON_EXPIRE_DATE < NOW()");
		}else if(couponDTO.getSearchCondition().equals("usedCoupon")) {
			builder.append("AND CS.ORDERCONTENT_ID IS NOT NULL AND CS.COUPON_EXPIRE_DATE > NOW()");
		}else if(couponDTO.getSearchCondition().equals("unUsedCoupon")) {
			builder.append("AND CS.ORDERCONTENT_ID IS NULL AND CS.COUPON_EXPIRE_DATE > NOW()");
		}else if(couponDTO.getSearchCondition().equals("searchCoupon")) {
			try {
			return jdbcTemplate.query(SELECTALL, new CouponRowMapper());
			}catch(Exception e) {
				return null;
			}
		}else if(couponDTO.getSearchCondition().equals("availableCoupon")) {
			try {
			return jdbcTemplate.query(SELECT_AVAILABLE_COUPONS_PRE_ORDER, args, new CouponRowMapperAvailableCoupon());
			}
			catch(Exception e) {
				return null;
			}
		}
		String query=builder.toString();
		System.out.println("쿼리상태 : "+query);
		try {
			return jdbcTemplate.query(query, args, new CouponRowMapperSearchByStatus());
		}catch(Exception e) {
			return null;
		}
	}

	public CouponDTO selectOne(CouponDTO couponDTO) {
		if(couponDTO.getSearchCondition().equals("getCouponID")) {
			System.out.println("[로그:정현진] CouponDAO 들어옴");
			System.out.println("[로그:정현진] 쿠폰코드 : "+couponDTO.getCouponCode());
			Object [] args= {couponDTO.getCouponCode() };
			return jdbcTemplate.queryForObject(SELECTONE, args, new CouponRowMapperGetCouponID());
		}
		else {
			return null;
		}
	}
	
	
	public boolean insert(CouponDTO couponDTO) {
		int result = jdbcTemplate.update(INSERT, couponDTO.getCouponContent(),couponDTO.getCouponDiscountRate(),
				couponDTO.getCouponDiscountMaxPrice(),couponDTO.getCouponName(),
				couponDTO.getCouponCode(),couponDTO.getCouponUseDate(),couponDTO.getCouponType(),couponDTO.getCouponUseMinPrice());
		if(result <=0) {
			return false;
		}
		return true;
	}
	public boolean update(CouponDTO couponDTO) {
		return false;
	}

	public boolean delete(CouponDTO couponDTO) {
		int result = jdbcTemplate.update(DELETE, couponDTO.getCouponID());
		if(result<=0) {
			return false;
		}
		return true;
	}
}

class CouponRowMapper implements RowMapper<CouponDTO>{
	@Override
	public CouponDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponDTO couponDTO=new CouponDTO();
		couponDTO.setCouponID(rs.getInt("COUPON_ID"));
		couponDTO.setCouponContent(rs.getString("COUPON_CONTENT"));
		couponDTO.setCouponName(rs.getString("COUPON_NAME"));
		couponDTO.setCouponCode(rs.getString("COUPON_CODE"));
		couponDTO.setCouponUseDate(rs.getInt("COUPON_USE_DATE"));
		couponDTO.setCouponType(rs.getString("COUPON_TYPE"));
		couponDTO.setCouponDiscountRate(rs.getInt("COUPON_DISCOUNT_RATE"));
		return couponDTO;
	}
}


class CouponRowMapperGetCouponID implements RowMapper<CouponDTO>{
	
	@Override
	public CouponDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] RowMapper 들어옴");
		CouponDTO couponDTO=new CouponDTO();
		couponDTO.setCouponID(rs.getInt("COUPON_ID"));
		return couponDTO;
	}
}


class CouponRowMapperSearchByStatus implements RowMapper<CouponDTO> {

	@Override
	public CouponDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponDTO couponDTO=new CouponDTO();
		couponDTO.setCouponID(rs.getInt("COUPON_ID"));
		couponDTO.setCouponName(rs.getString("COUPON_NAME"));
		couponDTO.setCouponCode(rs.getString("COUPON_CODE"));
		couponDTO.setCouponContent(rs.getString("COUPON_CONTENT"));
		couponDTO.setCouponDiscountRate(rs.getInt("COUPON_DISCOUNT_RATE"));
		couponDTO.setCouponType(rs.getString("COUPON_TYPE"));
		couponDTO.setCouponIssueDate(rs.getDate("COUPON_ISSUE_DATE"));
		couponDTO.setCouponExpireDate(rs.getDate("COUPON_EXPIRE_DATE"));
		couponDTO.setOrderContentID(rs.getInt("ORDERCONTENT_ID"));
		couponDTO.setMemberID(rs.getString("MEMBER_ID"));
		return couponDTO;
	}
	
}

class CouponRowMapperAvailableCoupon implements RowMapper<CouponDTO> {

	@Override
	public CouponDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponDTO couponDTO=new CouponDTO();
		couponDTO.setCouponID(rs.getInt("COUPON_ID"));
		couponDTO.setCouponName(rs.getString("COUPON_NAME"));
		couponDTO.setCouponCode(rs.getString("COUPON_CODE"));
		couponDTO.setCouponDiscountRate(rs.getInt("COUPON_DISCOUNT_RATE"));
		couponDTO.setCouponStatusID(rs.getInt("COUPON_STATUS_ID"));
		return couponDTO;
	}
	
	
}
