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

	private static final String SELECTALL=
			"";
			
	private static final String SELECTONE_GET_ORDER_COUNTS_FOR_YESTERDAY_AND_TODAY=
			"SELECT "
			+ "    SUM(IF(DATE(ORDERLIST_DATE) = CURDATE(), 1, 0)) AS ORDER_COUNTS_TODAY, "
			+ "    SUM(IF(DATE(ORDERLIST_DATE) = DATE_SUB(CURDATE(), INTERVAL 1 DAY), 1, 0)) AS ORDER_COUNTS_YESTERDAY "
			+ "FROM ORDERLIST";
	
	private static final String INSERT="";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<OrderListDTO> selectAll(OrderListDTO orderListDTO) {
			return null;
	}

	public OrderListDTO selectOne(OrderListDTO orderListDTO) {
		return jdbcTemplate.queryForObject(SELECTONE_GET_ORDER_COUNTS_FOR_YESTERDAY_AND_TODAY, new OrderListRowMapperGetOrderCounts());
	}

	public boolean insert(OrderListDTO orderListDTO) {
		return false;
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
		orderListDTO.setOrderListCountsToday(rs.getInt("ORDER_COUNTS_TODAY"));
		orderListDTO.setOrderListCountsYesterday(rs.getInt("ORDER_COUNTS_YESTERDAY"));
		return orderListDTO;
	}
	
}