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
 
	private static final String SELECTALL="SELECT "
			+ "OC.ORDERCONTENT_ID, "
			+ "OC.ORDERLIST_ID, "
			+ "OC.ORDERCONTENT_CNT, "
			+ "OC.PRODUCT_ID, "
			+ "P.PRODUCT_NAME, "
			+ "P.PRODUCT_PRICE, "
			+ "P.CATEGORY_ID, "
			+ "(SELECT IMAGE_URL FROM IMAGE I "
			+ "WHERE I.PRODUCT_ID = P.PRODUCT_ID "
			+ "ORDER BY IMAGE_ID LIMIT 1) AS IMAGE_URL "
			+ "FROM ORDERCONTENT OC "
			+ "INNER JOIN PRODUCT P ON P.PRODUCT_ID = OC.PRODUCT_ID "
			+ "WHERE OC.ORDERLIST_ID = ? "
			+ "GROUP BY "
			+ "OC.ORDERCONTENT_ID, "
			+ "OC.ORDERLIST_ID, "
			+ "OC.ORDERCONTENT_CNT, "
			+ "OC.PRODUCT_ID, "
			+ "P.PRODUCT_NAME, "
			+ "P.PRODUCT_PRICE, "
			+ "P.CATEGORY_ID ";
	private static final String SELECTONE="";
	private static final String INSERT="INSERT INTO ORDERCONTENT (ORDERLIST_ID, PRODUCT_ID, ORDERCONTENT_CNT) VALUES (?,?,?)";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<OrderContentDTO> selectAll(OrderContentDTO orderContentDTO) {
		Object[] args= {orderContentDTO.getOrderListID()};
		try {
			return jdbcTemplate.query(SELECTALL,args,new OrderContentRowMapper());
		}catch(Exception e) {
			return null;
		}
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
		
		OrderContentDTO orderContentDTO= new OrderContentDTO();
		
		orderContentDTO.setOrderContentID(rs.getInt("ORDERCONTENT_ID"));
		orderContentDTO.setOrderListID(rs.getInt("ORDERLIST_ID"));
		orderContentDTO.setOrderContentCnt(rs.getInt("ORDERCONTENT_CNT"));
		orderContentDTO.setProductID(rs.getInt("PRODUCT_ID"));
		orderContentDTO.setProductName(rs.getString("PRODUCT_NAME"));
		orderContentDTO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		orderContentDTO.setCategoryID(rs.getInt("CATEGORY_ID"));
		orderContentDTO.setProductImg(rs.getString("IMAGE_URL"));
		
		return orderContentDTO;
	}
	
}
