package com.koreait.kod.biz.cart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("cartDAO")
public class CartDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired
	
			// 장바구니 전체 상품 조회
	private static final String SELECTALL="SELECT "
			+ "C.CART_ID, "
			+ "C.PRODUCT_ID, "
			+ "P.PRODUCT_NAME, "
			+ "P.PRODUCT_PRICE, "
			+ "SUM(P.PRODUCT_PRICE * CART_PRODUCT_CNT) AS SUM_PRODUCT_PRICE, "
			+ "C.CART_PRODUCT_CNT, "
			+ "MIN(I.IMAGE_ID) AS IMAGE_ID,"
			+ "(SELECT IMAGE_URL "
			+ "FROM IMAGE "
			+ "WHERE IMAGE_ID = MIN(I.IMAGE_ID)) AS IMAGE_URL "
			+ "FROM CART C JOIN PRODUCT P ON P.PRODUCT_ID = C.PRODUCT_ID "
			+ "LEFT JOIN IMAGE I ON P.PRODUCT_ID = I.PRODUCT_ID "
			+ "WHERE C.MEMBER_ID = ? "
			+ "GROUP BY C.CART_ID,C.PRODUCT_ID,P.PRODUCT_NAME,P.PRODUCT_PRICE";
	
			// 장바구니 개별상품 조회
	private static final String SELECTONE=
			"SELECT "
			+ "C.CART_ID, "
			+ "C.PRODUCT_ID, "
			+ "P.PRODUCT_NAME, "
			+ "P.PRODUCT_PRICE, "
			+ "SUM(P.PRODUCT_PRICE * CART_PRODUCT_CNT) AS SUM_PRODUCT_PRICE, "
			+ "C.CART_PRODUCT_CNT, "
			+ "MIN(I.IMAGE_ID) AS IMAGE_ID, "
			+ "(SELECT "
			+ "IMAGE_URL "
			+ "FROM IMAGE "
			+ "WHERE IMAGE_ID = MIN(I.IMAGE_ID)) AS IMAGE_URL "
			+ "FROM CART C "
			+ "JOIN PRODUCT P ON P.PRODUCT_ID = C.PRODUCT_ID "
			+ "LEFT JOIN IMAGE I ON P.PRODUCT_ID = I.PRODUCT_ID "
			+ "WHERE C.PRODUCT_ID = ? "
			+ "GROUP BY C.CART_ID, C.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_PRICE ";
			// 장바구니 상품추가
	private static final String INSERT="INSERT INTO CART(MEMBER_ID,PRODUCT_ID,CART_PRODUCT_CNT) VALUES (?,?,?)";
			// 장바구니 상품 수량변경
	private static final String UPDATE="UPDATE CART SET CART_PRODUCT_CNT = ? WHERE CART_ID=?";
			// 장바구니 상품 개별삭제
	private static final String DELETE_EACH_PRODUCT="DELETE "
			+ "FROM CART "
			+ "WHERE CART_ID=?";
			// 장바구니 상품 삭제
	private static final String DELETE_ALL="DELETE"
			+ " FROM CART "
			+ "WHERE MEMBER_ID = ?";

	public List<CartDTO> selectAll(CartDTO cartDTO) {
		System.out.println("[로그:정현진] SELECTALL들어옴 ");
		Object[] args = { cartDTO.getMemberID() };
		try {
		return jdbcTemplate.query(SELECTALL, args, new CartRowMapper());
		} 
		catch (Exception e) {
			return null;
		}
	}
	public CartDTO selectOne(CartDTO cartDTO) {
		System.out.println("[로그:정현진] cartDTO.getProductID() : "+cartDTO.getProductID());
		Object[] args= {cartDTO.getProductID()};
		try {
			return jdbcTemplate.queryForObject(SELECTONE,args,new CartRowMapper());
		}catch(Exception e) {
			return null;
		}
		
	}
	public boolean insert(CartDTO cartDTO) {
		int result = jdbcTemplate.update(INSERT,cartDTO.getMemberID(),cartDTO.getProductID(),cartDTO.getCartProductCnt());
		if(result <=0) {
			return false;			
		}
		return true;
	}

	public boolean update(CartDTO cartDTO) {
		int result= jdbcTemplate.update(UPDATE,cartDTO.getCartProductCnt(),cartDTO.getCartID());
		if(result<=0) {			
			return false;
		}
		return true;
	}

	public boolean delete(CartDTO cartDTO) {
		int result = 0;
		
		if(cartDTO.getSearchCondition().equals("DELETE_EACH_PRODUCT")) {
			result = jdbcTemplate.update(DELETE_EACH_PRODUCT,cartDTO.getCartID());
		}
		else if(cartDTO.getSearchCondition().equals("DELETE_ALL")) {
			result=jdbcTemplate.update(DELETE_ALL,cartDTO.getMemberID());
		}
		if(result <= 0) {
		return false;
		}
		return true;
	}
	
}

class CartRowMapper implements RowMapper<CartDTO>{

	@Override
	public CartDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] CartRowMapper 들어옴");
		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartID(rs.getInt("CART_ID"));
		cartDTO.setProductID(rs.getInt("PRODUCT_ID"));
		cartDTO.setProductName(rs.getString("PRODUCT_NAME"));
		cartDTO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		cartDTO.setProductTotalPrice(rs.getInt("SUM_PRODUCT_PRICE"));
		cartDTO.setCartProductCnt(rs.getInt("CART_PRODUCT_CNT"));
		cartDTO.setImageID(rs.getInt("IMAGE_ID"));
		cartDTO.setProductImg(rs.getString("IMAGE_URL"));
		return cartDTO;
	}
	
}
