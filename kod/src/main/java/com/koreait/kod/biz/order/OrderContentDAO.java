package com.koreait.kod.biz.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("orderContentDAO")
public class OrderContentDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL=
			  "SELECT  "
			+ "OC.ORDERCONTENT_ID,  "
			+ "OC.ORDERLIST_ID,  "
			+ "OC.ORDERCONTENT_CNT,  "
			+ "OC.PRODUCT_ID,  "
			+ "P.PRODUCT_NAME,  "
			+ "P.PRODUCT_PRICE,  "
			+ "P.CATEGORY_ID,  "
			+ "C.COUPON_ID, "
			+ "C.COUPON_NAME,  "
			+ "C.COUPON_DISCOUNT_RATE,  "
			+ "C.COUPON_DISCOUNT_MAX_PRICE,  "
			+ "O.ORDERLIST_DATE,  "
			+ "(SELECT  "
			+ "IMAGE_URL FROM IMAGE I  "
			+ "WHERE I.PRODUCT_ID = P.PRODUCT_ID  "
			+ "ORDER BY IMAGE_ID LIMIT 1) AS IMAGE_URL  "
			+ "FROM ORDERCONTENT OC  "
			+ "JOIN  "
			+ "ORDERLIST O ON O.ORDERLIST_ID = OC.ORDERLIST_ID  "
			+ "INNER JOIN  "
			+ "PRODUCT P ON P.PRODUCT_ID = OC.PRODUCT_ID  "
			+ "LEFT JOIN  "
			+ "COUPON_STATUS CS ON CS.ORDERCONTENT_ID = OC.ORDERCONTENT_ID  "
			+ "LEFT JOIN  "
			+ "COUPON C ON C.COUPON_ID = CS.COUPON_ID  "
			+ "WHERE OC.ORDERLIST_ID = ? "
			+ "GROUP BY  "
			+ "OC.ORDERCONTENT_ID,  "
			+ "C.COUPON_ID";
	
	private static final String SELECTALL_GET_ORDERCONTENT_ID=
			"SELECT ORDERCONTENT_ID FROM ORDERCONTENT WHERE ORDERLIST_ID=?";
	
	
	private static final String SELECTONE="";
	private static final String INSERT="INSERT INTO ORDERCONTENT (ORDERLIST_ID, PRODUCT_ID, ORDERCONTENT_CNT) VALUES (?,?,?)";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<OrderContentDTO> selectAll(OrderContentDTO orderContentDTO) {
		Object[] args= {orderContentDTO.getOrderListID()};
		if(orderContentDTO.getSearchCondition().equals("orderDatas")) {
			System.out.println("[로그:정현진] OrderContentDAO ,orderDatas 들어옴");
			System.out.println("[로그:정현진] 주문번호ID : "+orderContentDTO.getOrderListID());
			try {
				return jdbcTemplate.query(SELECTALL,args,new OrderContentRowMapper());
			}catch(Exception e) {
				return null;
			}
		}
		else if(orderContentDTO.getSearchCondition().equals("getOrderContentID")) {
			return jdbcTemplate.query(SELECTALL_GET_ORDERCONTENT_ID,args, new OrderContentRowMapperGetOrderContentID());
		}
		return null;
		
	}

	public OrderContentDTO selectOne(OrderContentDTO orderContentDTO) {
		return null;
	}

	public boolean insert(OrderContentDTO orderContentDTO) {
		int result = jdbcTemplate.update(INSERT, orderContentDTO.getOrderListID(),orderContentDTO.getProductID(),
				orderContentDTO.getOrderContentCnt());
		if(result<=0) {			
			return false;
		}
		return true;
	}

	public boolean update(OrderContentDTO orderContentDTO) {
		return false;
	}

	public boolean delete(OrderContentDTO orderContentDTO) {
		return false;
	}
}

class OrderContentRowMapper implements RowMapper<OrderContentDTO> {
	@Override
	public OrderContentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] OrderContentRowMapper 들어옴");
		OrderContentDTO orderContentDTO= new OrderContentDTO();
		orderContentDTO.setOrderContentID(rs.getInt("ORDERCONTENT_ID"));
		orderContentDTO.setOrderListID(rs.getInt("ORDERLIST_ID"));
		orderContentDTO.setOrderContentCnt(rs.getInt("ORDERCONTENT_CNT"));
		orderContentDTO.setProductID(rs.getInt("PRODUCT_ID"));
		orderContentDTO.setProductName(rs.getString("PRODUCT_NAME"));
		orderContentDTO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		orderContentDTO.setCategoryID(rs.getInt("CATEGORY_ID"));
		orderContentDTO.setCouponID(rs.getInt("COUPON_ID"));
		orderContentDTO.setCouponName(rs.getString("COUPON_NAME"));
		orderContentDTO.setCouponDiscountRate(rs.getInt("COUPON_DISCOUNT_RATE"));
		orderContentDTO.setCouponDiscountMaxPrice(rs.getInt("COUPON_DISCOUNT_MAX_PRICE"));
		orderContentDTO.setOrderListDate(rs.getDate("ORDERLIST_DATE"));
		orderContentDTO.setProductImg(rs.getString("IMAGE_URL"));
		System.out.println("[로그:정현진] orderContentDTO : "+orderContentDTO);
		return orderContentDTO;
	}
}
class OrderContentRowMapperGetOrderContentID implements RowMapper<OrderContentDTO> {
	@Override
	public OrderContentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		OrderContentDTO orderContentDTO= new OrderContentDTO();
		orderContentDTO.setOrderContentID(rs.getInt("ORDERCONTENT_ID"));
		System.out.println("[로그:정현진] ORDERCONTENT_ID : "+rs.getInt("ORDERCONTENT_ID"));
		return orderContentDTO;
	}
}