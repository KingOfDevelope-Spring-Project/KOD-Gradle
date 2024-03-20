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
	// 사용한 쿠폰 조회	(관리자)
	private static final String SELECTALL_SEARCH_USED_COUPON="SELECT DISTINCT "
			+ "C.COUPON_NAME,"
			+ "C.COUPON_CODE,"
			+ "C.COUPON_CONTENT,"
			+ "C.COUPON_DISCOUNT_RATE,"
			+ "CS.COUPON_ISSUE_DATE,"
			+ "CS.MEMBER_ID,"
			+ "O.ORDERLIST_DATE,"
			+ "CS.COUPON_STATUS_ID "
			+ "FROM COUPON C JOIN COUPON_"
			+ "STATUS CS ON C.COUPON_ID  = CS.COUPON_ID "
			+ "JOIN ORDERLIST O ON O.MEMBER_ID  = CS.MEMBER_ID "
			+ "WHERE CS.COUPON_ISUSED ='USED_COUPON'";
	// 미사용 쿠폰 조회
	private static final String SELECTALL_SEARCH_UNUSED_COUPON="SELECT "
			+ "C.COUPON_NAME,"
			+ "C.COUPON_CODE,"
			+ "C.COUPON_CONTENT,"
			+ "C.COUPON_DISCOUNT_RATE,"
			+ "CS.COUPON_ISSUE_DATE,"
			+ "CS.MEMBER_ID,"
			+ "CS.COUPON_STATUS_ID "
			+ "FROM COUPON C "
			+ "JOIN COUPON_STATUS CS ON C.COUPON_ID = CS.COUPON_ID "
			+ "WHERE CS.COUPON_ISUSED = 'UNUSED_COUPON'";
	// 쿠폰 개별조회
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

	public List<CouponDTO> selectAll(CouponDTO couponDTO) {
		try {
		if(couponDTO.getSearchCondition().equals("searchUsedCoupon")) {
			return jdbcTemplate.query(SELECTALL_SEARCH_USED_COUPON, new CouponRowMapper1());
		}else if(couponDTO.getSearchCondition().equals("searchUnusedCoupon")) {
			return jdbcTemplate.query(SELECTALL_SEARCH_UNUSED_COUPON, new CouponRowMapper2());
		}else if (couponDTO.getSearchCondition().equals("issuedCouponList")) {
			return jdbcTemplate.query(SELECTALL,new CouponRowMapper()); 
		}
		else {
			return null;
		}
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
		return false;
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

class CouponRowMapper1 implements RowMapper<CouponDTO>{

	@Override
	public CouponDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponDTO couponDTO=new CouponDTO();
		couponDTO.setCouponName(rs.getString("COUPON_NAME"));
		couponDTO.setCouponCode(rs.getString("COUPON_CODE"));
		couponDTO.setCouponContent(rs.getString("COUPON_CONTENT"));
		couponDTO.setCouponDiscountRate(rs.getInt("COUPON_DISCOUNT_RATE"));
		couponDTO.setCouponIssueDate(rs.getDate("COUPON_ISSUE_DATE"));
		couponDTO.setMemberID(rs.getString("MEMBER_ID"));
		couponDTO.setOrderListDate(rs.getDate("ORDERLIST_DATE"));
		couponDTO.setCouponStatus_ID(rs.getInt("COUPON_STATUS_ID"));
		return couponDTO;
	}
	
}

class CouponRowMapper2 implements RowMapper<CouponDTO>{
	
	@Override
	public CouponDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponDTO couponDTO=new CouponDTO();
		couponDTO.setCouponName(rs.getString("COUPON_NAME"));
		couponDTO.setCouponCode(rs.getString("COUPON_CODE"));
		couponDTO.setCouponContent(rs.getString("COUPON_CONTENT"));
		couponDTO.setCouponDiscountRate(rs.getInt("COUPON_DISCOUNT_RATE"));
		couponDTO.setCouponIssueDate(rs.getDate("COUPON_ISSUE_DATE"));
		couponDTO.setMemberID(rs.getString("MEMBER_ID"));
		couponDTO.setCouponStatus_ID(rs.getInt("COUPON_STATUS_ID"));
		return couponDTO;
	}
}

class CouponRowMapper3 implements RowMapper<CouponDTO>{

	@Override
	public CouponDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] RowMapper 들어옴");
		CouponDTO couponDTO=new CouponDTO();
		couponDTO.setCouponID(rs.getInt("COUPON_ID"));
		couponDTO.setCouponContent(rs.getString("COUPON_CONTENT"));
		couponDTO.setCouponDiscountRate(rs.getInt("COUPON_DISCOUNT_RATE"));
		couponDTO.setCouponName(rs.getString("COUPON_NAME"));
		couponDTO.setCouponCode(rs.getString("COUPON_CODE"));
		couponDTO.setCouponUseDate(rs.getInt("COUPON_USE_DATE"));
		couponDTO.setCouponType(rs.getString("COUPON_TYPE"));
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
