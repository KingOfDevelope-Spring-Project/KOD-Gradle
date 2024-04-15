package com.koreait.kod.biz.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("orderListDAO")
public class OrderListDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	// 주문 내역 최신순 출력
	private static final String SELECTALL="SELECT "
			+ "ORDERLIST_ID, "
			+ "MEMBER_ID, "
			+ "ORDERLIST_DATE "
			+ "FROM ORDERLIST "
			+ "WHERE MEMBER_ID=? "
			+ "ORDER BY ORDERLIST_ID DESC";
	private static final String SELECTONE_CHECK_MAX_ID="SELECT MAX(ORDERLIST_ID) AS MAX_ID FROM ORDERLIST WHERE MEMBER_ID=?";
	private static final String SELECTONE_GET_ORDER_COUNTS_FOR_YESTERDAY_AND_TODAY=
			"SELECT "
			+ "    SUM(IF(DATE(ORDERLIST_DATE) = CURDATE(), 1, 0)) AS ORDER_CNT_TODAY, "
			+ "    SUM(IF(DATE(ORDERLIST_DATE) = DATE_SUB(CURDATE(), INTERVAL 1 DAY), 1, 0)) AS ORDER_CNT_YESTERDAY "
			+ "FROM ORDERLIST";
	
	private static final String INSERT="INSERT INTO ORDERLIST (MEMBER_ID, ORDERLIST_DATE) VALUES(?, NOW())";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<OrderListDTO> selectAll(OrderListDTO orderListDTO) {
		Object args[] = {orderListDTO.getMemberID()};
		try {
			return jdbcTemplate.query(SELECTALL, args, new OrderListRowMapper());
		}catch(Exception e) {
			return null;
		}
	}

	public OrderListDTO selectOne(OrderListDTO orderListDTO) {
	    if (orderListDTO.getSearchCondition().equals("orderCountsForYesterdayAndToday")) {
	        try {
	            return jdbcTemplate.queryForObject(SELECTONE_GET_ORDER_COUNTS_FOR_YESTERDAY_AND_TODAY, new OrderListRowMapperGetOrderCounts());
	        } catch (Exception e) {
	            return null; 
	        }
	    } else if (orderListDTO.getSearchCondition().equals("check_max_id")) {
	        try {
	            Object[] args = { orderListDTO.getMemberID() };
	            return jdbcTemplate.queryForObject(SELECTONE_CHECK_MAX_ID, args, new OrderListRowMapperCheckMaxID());
	        } 
	        catch (Exception e) {
	            return null; 
	        }
	    } else {
	        return null;
	    }
	}

	public boolean insert(OrderListDTO orderListDTO) {
		int result = jdbcTemplate.update(INSERT,orderListDTO.getMemberID());
		if(result<=0) {
			return false;			
		}
		return true;
	}

	public boolean update(OrderListDTO orderListDTO) {
		return false;
	}

	public boolean delete(OrderListDTO orderListDTO) {
		return false;
	}
}




//상품ID 반환 - 상품등록시 활용 (상품이미지 등록 & 상품명 중복확인)
class OrderListRowMapperGetOrderCounts implements RowMapper<OrderListDTO>{

	@Override
	public OrderListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderListDTO orderListDTO= new OrderListDTO();
		System.out.println("오늘 주문건수 : "+rs.getInt("ORDER_CNT_TODAY") );
		System.out.println("어제 주문건수 : "+rs.getInt("ORDER_CNT_YESTERDAY") );
		orderListDTO.setOrderListCntToday(rs.getInt("ORDER_CNT_TODAY"));
		orderListDTO.setOrderListCntYesterday(rs.getInt("ORDER_CNT_YESTERDAY"));
		return orderListDTO;
	}
	
}

class OrderListRowMapper implements RowMapper<OrderListDTO>{

	@Override
	public OrderListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderListDTO orderListDTO = new OrderListDTO();
		orderListDTO.setOrderListID(rs.getInt("ORDERLIST_ID"));
		orderListDTO.setMemberID(rs.getString("MEMBER_ID"));
		orderListDTO.setOrderListDate(rs.getDate("ORDERLIST_DATE"));
		
		return orderListDTO;
	}
	
}

class OrderListRowMapperCheckMaxID implements RowMapper<OrderListDTO>{

	@Override
	public OrderListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] OrderListRowMapperCheckMaxID 들어옴");
		OrderListDTO orderListDTO = new OrderListDTO();
		orderListDTO.setOrderListID(rs.getInt("MAX_ID"));
		System.out.println("[로그:정현진] 생성된 주문번호 : "+rs.getInt("MAX_ID"));
		return orderListDTO;
	}
	
}