package com.koreait.kod.biz.productAndWishList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository("productDAO")
public class ProductDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL_QUARTERLY_STATISTICS=
			"SELECT  "
			+ "    SALES_STATISTICS.YEAR AS 'YEAR', "
			+ "    SALES_STATISTICS.QUARTER AS 'QUARTER', "
			+ "    SALES_STATISTICS.PRODUCT_ID, " 
			+ "    SALES_STATISTICS.PRODUCT_NAME AS 'PRODUCT_NAME', "
			+ "    SALES_STATISTICS.PRODUCT_PRICE AS 'PRODUCT_PRICE', "
			+ "    SALES_STATISTICS.TOTAL_PRODUCT_CNT AS 'TOTAL_PRODUCT_CNT', "
			+ "    SALES_STATISTICS.TOTAL_PRODUCT_SALES AS 'TOTAL_PRODUCT_SALES', "
			+ "    TOTAL_QUARTERLY_SALES.TOTAL_QUARTERLY_SALES "
			+ "FROM ( "
			+ "    SELECT  "
			+ "        YEAR(OL.ORDERLIST_DATE) AS 'YEAR', "
			+ "        QUARTER(OL.ORDERLIST_DATE) AS 'QUARTER', "
			+ "        P.PRODUCT_ID, "
			+ "        P.PRODUCT_NAME, "
			+ "        P.PRODUCT_PRICE, "
			+ "        SUM(OC.ORDERCONTENT_CNT) AS 'TOTAL_PRODUCT_CNT', "
			+ "        SUM(P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT) AS 'TOTAL_PRODUCT_SALES' "
			+ "    FROM  "
			+ "        ORDERLIST OL "
			+ "    JOIN  "
			+ "        ORDERCONTENT OC ON OL.ORDERLIST_ID = OC.ORDERLIST_ID "
			+ "    JOIN  "
			+ "        PRODUCT P ON OC.PRODUCT_ID = P.PRODUCT_ID "
			+ "    GROUP BY  "
			+ "        YEAR(OL.ORDERLIST_DATE), "
			+ "        QUARTER(OL.ORDERLIST_DATE), "
			+ "        P.PRODUCT_ID, "
			+ "        P.PRODUCT_NAME, "
			+ "        P.PRODUCT_PRICE "
			+ ") AS SALES_STATISTICS "
			+ "JOIN ( "
			+ "    SELECT  "
			+ "        YEAR(OL.ORDERLIST_DATE) AS 'YEAR', "
			+ "        QUARTER(OL.ORDERLIST_DATE) AS 'QUARTER', "
			+ "        SUM(P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT) AS 'TOTAL_QUARTERLY_SALES' "
			+ "    FROM  "
			+ "        ORDERLIST OL "
			+ "    JOIN  "
			+ "        ORDERCONTENT OC ON OL.ORDERLIST_ID = OC.ORDERLIST_ID "
			+ "    JOIN  "
			+ "        PRODUCT P ON OC.PRODUCT_ID = P.PRODUCT_ID "
			+ "    GROUP BY  "
			+ "        YEAR(OL.ORDERLIST_DATE), "
			+ "        QUARTER(OL.ORDERLIST_DATE) "
			+ ") AS TOTAL_QUARTERLY_SALES ON SALES_STATISTICS.YEAR = TOTAL_QUARTERLY_SALES.YEAR AND SALES_STATISTICS.QUARTER = TOTAL_QUARTERLY_SALES.QUARTER "
			+ "ORDER BY  "
			+ "    SALES_STATISTICS.YEAR, "
			+ "    SALES_STATISTICS.QUARTER, "
			+ "    SALES_STATISTICS.TOTAL_PRODUCT_SALES DESC";
	
	private static final String SELECTALL_YEAR_PRODUCT_SALES_RANKING=
			"SELECT  "
			+ "    YEAR(OL.ORDERLIST_DATE) AS 'YEAR', "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_PRICE, "
			+ "    SUM(OC.ORDERCONTENT_CNT) AS 'PRODUCT_SALES_QUANTITY', "
			+ "    SUM(P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT) AS 'PRODUCT_SALES_REVENUE' "
			+ "FROM  "
			+ "    ORDERLIST OL "
			+ "JOIN  "
			+ "    ORDERCONTENT OC ON OL.ORDERLIST_ID = OC.ORDERLIST_ID "
			+ "JOIN  "
			+ "    PRODUCT P ON OC.PRODUCT_ID = P.PRODUCT_ID "
			+ "GROUP BY  "
			+ "    YEAR(OL.ORDERLIST_DATE), "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_PRICE "
			+ "ORDER BY  "
			+ "    YEAR(OL.ORDERLIST_DATE), "
			+ "    `PRODUCT_SALES_REVENUE` DESC";
	
	
	private static final String SELECTONE="SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NAME=?";
	private static final String INSERT="INSERT INTO "
			+ "PRODUCT (PRODUCT_NAME,PRODUCT_BRAND,PRODUCT_PRICE,PRODUCT_INFO,PRODUCT_STOCK,CATEGORY_ID) "
			+ "VALUES (?,?,?,?,?,?)";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<ProductDTO> selectAll(ProductDTO productDTO) {
		if(productDTO.getSearchCondition().equals("QUARTER_STATISTICS")) {
			System.out.println("[로그:정현진] ProductDAO 분기통계 들어옴");
			return jdbcTemplate.query(SELECTALL_QUARTERLY_STATISTICS, new ProductRowMapperQuarterStatistics());
		}
		else {
			return null;
		}
	}

	public ProductDTO selectOne(ProductDTO productDTO) {
		try {
			Object[] args = { productDTO.getProductName() };
			return jdbcTemplate.queryForObject(SELECTONE, args, new productRowMapper());
		} catch (Exception e) {
			return null;
		}
	}

	public boolean insert(ProductDTO productDTO) {
		int result=jdbcTemplate.update(INSERT,productDTO.getProductName(),productDTO.getProductBrand()
				,productDTO.getProductPrice(),productDTO.getProductInfo(),productDTO.getProductStock()
				,productDTO.getCategoryID());
		if(result <=0) {
			return false;		
		}
		return true;
	}

	public boolean update(ProductDTO productDTO) {
		return false;
	}

	public boolean delete(ProductDTO productDTO) {
		return false;
	}
}



// 분기 통계
class ProductRowMapperQuarterStatistics implements org.springframework.jdbc.core.RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] ProductRowMapperQuarterStatistics 들어옴");
		ProductDTO data = new ProductDTO();
		data.setYear(rs.getInt("YEAR")); // 년
		data.setQuarter(rs.getInt("QUARTER")); // 분기
		data.setProductID(rs.getInt("PRODUCT_ID")); // 상품ID
		data.setProductName(rs.getString("PRODUCT_NAME")); // 상품명
		data.setProductPrice(rs.getInt("PRODUCT_PRICE")); // 상품가격
		data.setProductSalesQuantity(rs.getInt("PRODUCT_SALES_QUANTITY")); // 상품판매수량
		data.setProductSalesRevenue(rs.getInt("PRODUCT_SALES_REVENUE")); // 상품매출
		data.setQuarterlyRevenue(rs.getInt("QUARTER_REVENUE")); // 분기 매출
		return data;
	}
}

class productRowMapper implements RowMapper<ProductDTO>{

	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO productDTO= new ProductDTO();
		productDTO.setProductID(rs.getInt("PRODUCT_ID"));
		return productDTO;
	}
	
}
