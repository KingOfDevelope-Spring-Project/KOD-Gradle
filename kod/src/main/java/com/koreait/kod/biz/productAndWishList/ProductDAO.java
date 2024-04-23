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
	
	
	// 분기통계
	private static final String SELECTALL_QUARTERLY_STATISTICS=
			"SELECT   "
			+ "    SALES_STATISTICS.YEAR, "
			+ "    SALES_STATISTICS.QUARTER,  "
			+ "    SALES_STATISTICS.PRODUCT_ID, "
			+ "    SALES_STATISTICS.PRODUCT_NAME, "
			+ "    SALES_STATISTICS.PRODUCT_PRICE, "
			+ "    SALES_STATISTICS.PRODUCT_SALES_QUANTITY, "
			+ "	   QUARTERLY_SALES.TOTAL_PRODUCT_SALES_QUANTITY_FOR_QUARTER, "
			+ "    FLOOR(SALES_STATISTICS.PRODUCT_SALES_REVENUE) AS 'PRODUCT_SALES_REVENUE',  "
			+ "    FLOOR(QUARTERLY_SALES.QUARTERLY_REVENUE) as 'QUARTERLY_REVENUE' "
			+ "FROM (  "
			+ "    SELECT   "
			+ "        YEAR(OL.ORDERLIST_DATE) AS 'YEAR',  "
			+ "        QUARTER(OL.ORDERLIST_DATE) AS 'QUARTER',  "
			+ "        P.PRODUCT_ID,  "
			+ "        P.PRODUCT_NAME,  "
			+ "        P.PRODUCT_PRICE,  "
			+ "        SUM(OC.ORDERCONTENT_CNT) AS 'PRODUCT_SALES_QUANTITY',  "
			+ "        SUM((P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT) - (P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT * IFNULL(C.COUPON_DISCOUNT_RATE, 0) / 100)) AS 'PRODUCT_SALES_REVENUE'  "
			+ "    FROM   "
			+ "        ORDERLIST OL  "
			+ "    JOIN   "
			+ "        ORDERCONTENT OC ON OL.ORDERLIST_ID = OC.ORDERLIST_ID  "
			+ "    JOIN   "
			+ "        PRODUCT P ON OC.PRODUCT_ID = P.PRODUCT_ID  "
			+ "    LEFT JOIN   "
			+ "        COUPON_STATUS CS ON OC.ORDERCONTENT_ID = CS.ORDERCONTENT_ID  "
			+ "    LEFT JOIN   "
			+ "        COUPON C ON CS.COUPON_ID = C.COUPON_ID  "
			+ "    GROUP BY   "
			+ "        YEAR(OL.ORDERLIST_DATE),  "
			+ "        QUARTER(OL.ORDERLIST_DATE),  "
			+ "        P.PRODUCT_ID,  "
			+ "        P.PRODUCT_NAME,  "
			+ "        P.PRODUCT_PRICE  "
			+ ") AS SALES_STATISTICS  "
			+ "LEFT JOIN (  "
			+ "    SELECT   "
			+ "        YEAR(OL.ORDERLIST_DATE) AS 'YEAR',  "
			+ "        QUARTER(OL.ORDERLIST_DATE) AS 'QUARTER',  "
			+ "        FLOOR(SUM((P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT) - (P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT * IFNULL(C.COUPON_DISCOUNT_RATE, 0) / 100))) AS 'QUARTERLY_REVENUE', "
			+ "        SUM(OC.ORDERCONTENT_CNT) AS 'TOTAL_PRODUCT_SALES_QUANTITY_FOR_QUARTER' "
			+ "    FROM   "
			+ "        ORDERLIST OL  "
			+ "    JOIN   "
			+ "        ORDERCONTENT OC ON OL.ORDERLIST_ID = OC.ORDERLIST_ID  "
			+ "    JOIN   "
			+ "        PRODUCT P ON OC.PRODUCT_ID = P.PRODUCT_ID  "
			+ "    LEFT JOIN   "
			+ "        COUPON_STATUS CS ON OC.ORDERCONTENT_ID = CS.ORDERCONTENT_ID  "
			+ "    LEFT JOIN   "
			+ "        COUPON C ON CS.COUPON_ID = C.COUPON_ID  "
			+ "    GROUP BY   "
			+ "        YEAR(OL.ORDERLIST_DATE),  "
			+ "        QUARTER(OL.ORDERLIST_DATE)  "
			+ ") AS QUARTERLY_SALES ON SALES_STATISTICS.YEAR = QUARTERLY_SALES.YEAR AND SALES_STATISTICS.QUARTER = QUARTERLY_SALES.QUARTER  "
			+ "ORDER BY   "
			+ "    SALES_STATISTICS.YEAR,  "
			+ "    SALES_STATISTICS.QUARTER,  "
			+ "    SALES_STATISTICS.PRODUCT_SALES_REVENUE DESC";
	
	// 분기매출 for 2year
	private static final String SELECTALL_QUARTERLY_REVENUE_FOR_2YEARS=
			"SELECT  "
			+ "    YEAR(OL.ORDERLIST_DATE) AS 'YEAR', "
			+ "    QUARTER(OL.ORDERLIST_DATE) AS 'QUARTER', "
			+ "    FLOOR(SUM((P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT) - (P.PRODUCT_PRICE * OC.ORDERCONTENT_CNT * IFNULL(C.COUPON_DISCOUNT_RATE, 0) / 100))) AS 'QUARTERLY_REVENUE' "
			+ "FROM  "
			+ "    ORDERLIST OL "
			+ "JOIN  "
			+ "    ORDERCONTENT OC ON OL.ORDERLIST_ID = OC.ORDERLIST_ID "
			+ "JOIN  "
			+ "    PRODUCT P ON OC.PRODUCT_ID = P.PRODUCT_ID "
			+ "LEFT JOIN  "
			+ "    COUPON_STATUS CS ON OC.ORDERCONTENT_ID = CS.ORDERCONTENT_ID "
			+ "LEFT JOIN  "
			+ "    COUPON C ON CS.COUPON_ID = C.COUPON_ID "
			+ "WHERE  "
			+ "    OL.ORDERLIST_DATE >= DATE_SUB(CURDATE(), INTERVAL 2 YEAR) "
			+ "    AND OL.ORDERLIST_DATE <= CURDATE()   "
			+ "GROUP BY  "
			+ "    YEAR(OL.ORDERLIST_DATE), "
			+ "    QUARTER(OL.ORDERLIST_DATE) "
			+ "ORDER BY  "
			+ "    YEAR(OL.ORDERLIST_DATE), "
			+ "    QUARTER(OL.ORDERLIST_DATE)";
	
	// 월간매출 for 1year (13개월치 조회)
	private static final String SELECTALL_MONTHLY_REVENUE_FOR_1YEAR=
			"SELECT  "
			+ "    YEAR(ol.ORDERLIST_DATE) AS 'YEAR', "
			+ "    MONTH(ol.ORDERLIST_DATE) AS 'MONTH', "
			+ "    FLOOR(SUM((OC.ORDERCONTENT_CNT * p.PRODUCT_PRICE) - (OC.ORDERCONTENT_CNT * p.PRODUCT_PRICE * IFNULL(c.COUPON_DISCOUNT_RATE, 0) / 100))) AS 'MONTHLY_REVENUE' "
			+ "FROM  "
			+ "    ORDERLIST ol "
			+ "INNER JOIN  "
			+ "    ORDERCONTENT OC ON ol.ORDERLIST_ID = OC.ORDERLIST_ID "
			+ "INNER JOIN  "
			+ "    PRODUCT p ON OC.PRODUCT_ID = p.PRODUCT_ID "
			+ "LEFT JOIN  "
			+ "    COUPON_STATUS cs ON OC.ORDERCONTENT_ID = cs.ORDERCONTENT_ID "
			+ "LEFT JOIN  "
			+ "    COUPON c ON cs.COUPON_ID = c.COUPON_ID "
			+ "WHERE  "
			+ "    ol.ORDERLIST_DATE >= DATE_FORMAT(CURDATE() - INTERVAL 12 MONTH, '%Y-%m-01')  "
			+ "    AND ol.ORDERLIST_DATE < DATE_FORMAT(CURDATE() + INTERVAL 1 MONTH, '%Y-%m-01') "
			+ "GROUP BY  "
			+ "    YEAR(ol.ORDERLIST_DATE), "
			+ "    MONTH(ol.ORDERLIST_DATE) "
			+ "ORDER BY  "
			+ "    YEAR(ol.ORDERLIST_DATE), "
			+ "    MONTH(ol.ORDERLIST_DATE)";
	
	// 일간매출 for 30days
	private static final String SELECTALL_DAILY_REVENUE_FOR_30DAYS=
			"SELECT  "
			+ "    YEAR(date_range.date) AS 'YEAR', "
			+ "    MONTH(date_range.date) AS 'MONTH', "
			+ "    DAY(date_range.date) AS 'DAY', "
			+ "    COALESCE( "
			+ "        FLOOR( "
			+ "            SUM( "
			+ "                CASE "
			+ "                    WHEN (OC.ORDERCONTENT_CNT * p.PRODUCT_PRICE * IFNULL(c.COUPON_DISCOUNT_RATE, 0) / 100) "
			+ "                         > c.COUPON_DISCOUNT_MAX_PRICE "
			+ "                    THEN "
			+ "                        (OC.ORDERCONTENT_CNT * p.PRODUCT_PRICE) - c.COUPON_DISCOUNT_MAX_PRICE "
			+ "                    ELSE "
			+ "                        (OC.ORDERCONTENT_CNT * p.PRODUCT_PRICE)  "
			+ "                         - (OC.ORDERCONTENT_CNT * p.PRODUCT_PRICE * IFNULL(c.COUPON_DISCOUNT_RATE, 0) / 100) "
			+ "                END "
			+ "            ) "
			+ "        ), "
			+ "        0 "
			+ "    ) AS 'DAILY_REVENUE' "
			+ "FROM  "
			+ "    (SELECT CURDATE() - INTERVAL (TEN_DIGIT.i + ONE_DIGIT.i) DAY AS date "
			+ "     FROM "
			+ "         (SELECT 0 AS i UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL "
			+ "          SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS ONE_DIGIT "
			+ "     CROSS JOIN "
			+ "         (SELECT 0 AS i UNION ALL SELECT 10 UNION ALL SELECT 20 UNION ALL SELECT 30) AS TEN_DIGIT) AS date_range "
			+ "LEFT JOIN  "
			+ "    ORDERLIST ol ON DATE(ol.ORDERLIST_DATE) = date_range.date "
			+ "LEFT JOIN  "
			+ "    ORDERCONTENT OC ON ol.ORDERLIST_ID = OC.ORDERLIST_ID "
			+ "LEFT JOIN  "
			+ "    PRODUCT p ON OC.PRODUCT_ID = p.PRODUCT_ID "
			+ "LEFT JOIN  "
			+ "    COUPON_STATUS cs ON OC.ORDERCONTENT_ID = cs.ORDERCONTENT_ID "
			+ "LEFT JOIN  "
			+ "    COUPON c ON cs.COUPON_ID = c.COUPON_ID "
			+ "WHERE  "
			+ "    date_range.date >= CURDATE() - INTERVAL 29 DAY  "
			+ "    AND date_range.date < CURDATE()+1 "
			+ "GROUP BY  "
			+ "    YEAR(date_range.date), "
			+ "    MONTH(date_range.date), "
			+ "    DAY(date_range.date) "
			+ "ORDER BY  "
			+ "    YEAR(date_range.date), "
			+ "    MONTH(date_range.date), "
			+ "    DAY(date_range.date)";

	// 년간 판매랭킹
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
	
	// 전체 상품목록
	private static final String SELECTALL_ALL_PRODUCTS_DATAS=
			"SELECT  "
			+ "    C.CATEGORY_TYPE, "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_STOCK "
			+ "FROM  "
			+ "    PRODUCT AS P "
			+ "INNER JOIN  "
			+ "    CATEGORY AS C ON P.CATEGORY_ID = C.CATEGORY_ID";
	
	private static final String SELECTALL_ORDER_CNT_AND_REVENUE=
			"SELECT "
			+ "    COUNT(CASE WHEN MONTH(ol.ORDERLIST_DATE) = MONTH(CURRENT_DATE()) THEN oc.ORDERCONTENT_ID END) AS CURRENT_MONTH_ORDER_CNT, "
			+ "    SUM(CASE WHEN MONTH(ol.ORDERLIST_DATE) = MONTH(CURRENT_DATE()) THEN p.PRODUCT_PRICE * oc.ORDERCONTENT_CNT END) AS CURRENT_MONTH_REVENUE, "
			+ "    COUNT(CASE WHEN YEAR(ol.ORDERLIST_DATE) = YEAR(CURRENT_DATE()) THEN oc.ORDERCONTENT_ID END) AS CURRENT_YEAR_ORDER_CNT, "
			+ "    SUM(CASE WHEN YEAR(ol.ORDERLIST_DATE) = YEAR(CURRENT_DATE()) THEN p.PRODUCT_PRICE * oc.ORDERCONTENT_CNT END) AS CURRENT_YEAR_REVENUE "
			+ "FROM "
			+ "    ORDERLIST ol "
			+ "JOIN "
			+ "    ORDERCONTENT oc ON ol.ORDERLIST_ID = oc.ORDERLIST_ID "
			+ "JOIN "
			+ "    PRODUCT p ON oc.PRODUCT_ID = p.PRODUCT_ID "
			+ "WHERE "
			+ "    YEAR(ol.ORDERLIST_DATE) = YEAR(CURRENT_DATE()) ";
	
	// 
	private static final String SELECTALL_WISH_RANKING_BY_PRODUCTS_LOGIN = // 메인페이지 인기상품 추천 - 로그인상태
			"SELECT  "
			+ "     CASE WHEN WL.PRODUCT_ID IS NULL THEN 0 ELSE 1 END AS ISWISHED,  "
			+ "     ROWNUM AS RANK,  "
			+ "     RANKED_PRODUCTS.PRODUCT_ID,  "
			+ "     RANKED_PRODUCTS.PRODUCT_BRAND,  "
			+ "     RANKED_PRODUCTS.PRODUCT_NAME,  "
			+ "     RANKED_PRODUCTS.PRODUCT_CATEGORY,  "
			+ "     RANKED_PRODUCTS.PRODUCT_PRICE,  "
			+ "     RANKED_PRODUCTS.PRODUCT_IMG,  "
			+ "     RANKED_PRODUCTS.WISHLIST_COUNT  "
			+ " FROM (  "
			+ "     SELECT  "
			+ "         P.PRODUCT_ID,  "
			+ "         P.PRODUCT_BRAND,  "
			+ "         P.PRODUCT_NAME,  "
			+ "         P.PRODUCT_CATEGORY,  "
			+ "         P.PRODUCT_PRICE,  "
			+ "         P.PRODUCT_IMG,  "
			+ "         COUNT(W.WISHLIST_ID) AS WISHLIST_COUNT  "
			+ "     FROM PRODUCT P  "
			+ "     LEFT JOIN WISHLIST W ON P.PRODUCT_ID = W.PRODUCT_ID  "
			+ "     GROUP BY  "
			+ "         P.PRODUCT_ID,  "
			+ "         P.PRODUCT_BRAND,  "
			+ "         P.PRODUCT_NAME,  "
			+ "         P.PRODUCT_CATEGORY,  "
			+ "         P.PRODUCT_PRICE,  "
			+ "         P.PRODUCT_IMG  "
			+ "     ORDER BY COUNT(W.WISHLIST_ID) DESC "
			+ " ) RANKED_PRODUCTS  "
			+ " LEFT JOIN (  "
			+ "     SELECT PRODUCT_ID  "
			+ "     FROM WISHLIST  "
			+ "     WHERE MEMBER_ID = ? "
			+ " ) WL ON RANKED_PRODUCTS.PRODUCT_ID = WL.PRODUCT_ID "
			+ " WHERE ROWNUM <= 7";
	
	private static final String SELECTALL_WISH_RANKING_BY_PRODUCTS_LOGOUT = // 메인페이지 인기상품 추천 - 로그아웃상태
			  "  SELECT  "
			  + "   0 AS ISWISHED,  "
			  + "   RANKED_PRODUCTS.WISHLIST_RANK,  "
			  + "   RANKED_PRODUCTS.PRODUCT_ID,  "
			  + "   RANKED_PRODUCTS.PRODUCT_NAME,  "
			  + "   RANKED_PRODUCTS.PRODUCT_BRAND,  "
			  + "   RANKED_PRODUCTS.PRODUCT_PRICE,  "
			  + "   RANKED_PRODUCTS.PRODUCT_CATEGORY,  "
			  + "   RANKED_PRODUCTS.PRODUCT_IMG "
			  + "FROM (  "
			  + "   SELECT  "
			  + "       P.PRODUCT_ID,  "
			  + "       P.PRODUCT_NAME,  "
			  + "       P.PRODUCT_BRAND,  "
			  + "       P.PRODUCT_PRICE,  "
			  + "       P.PRODUCT_CATEGORY,  "
			  + "       P.PRODUCT_IMG,  "
			  + "       ROW_NUMBER() OVER (ORDER BY COUNT(W.WISHLIST_ID) DESC) AS WISHLIST_RANK  "
			  + "   FROM PRODUCT P  "
			  + "   LEFT JOIN WISHLIST W ON P.PRODUCT_ID = W.PRODUCT_ID  "
			  + "   GROUP BY  "
			  + "       P.PRODUCT_ID,  "
			  + "       P.PRODUCT_NAME,  "
			  + "       P.PRODUCT_BRAND,  "
			  + "       P.PRODUCT_PRICE,  "
			  + "       P.PRODUCT_CATEGORY,  "
			  + "       P.PRODUCT_IMG  "
			  + "   ORDER BY WISHLIST_RANK DESC NULLS LAST "
			  + ") RANKED_PRODUCTS  "
			  + "WHERE RANKED_PRODUCTS.WISHLIST_RANK <= 7 "
			  + "ORDER BY RANKED_PRODUCTS.WISHLIST_RANK ASC ";
	
	private static final String SELECTALL_WISH_RANKING_BY_AGE = // 메인페이지&상품상세페이지 연령별 인기상품 추천
			"SELECT "
			+ "  AGE_RANGE, "
			+ "  PRODUCT_ID, "
			+ "  PRODUCT_IMG, "
			+ "  PRODUCT_CATEGORY, "
			+ "  PRODUCT_NAME, "
			+ "  PRODUCT_PRICE, "
			+ "  WISH_TOTAL_COUNT "
			+ "FROM ( "
			+ "  SELECT "
			+ "    CASE "
			+ "      WHEN AGE >= 10 AND AGE < 20 THEN '10대' "
			+ "      WHEN AGE >= 20 AND AGE < 30 THEN '20대' "
			+ "      WHEN AGE >= 30 AND AGE < 40 THEN '30대' "
			+ "      WHEN AGE >= 40 AND AGE < 50 THEN '40대' "
			+ "      ELSE '50대 이상' "
			+ "    END AS AGE_RANGE, "
			+ "    PRODUCT_ID, "
			+ "    PRODUCT_IMG, "
			+ "    PRODUCT_CATEGORY, "
			+ "    PRODUCT_NAME, "
			+ "    PRODUCT_PRICE, "
			+ "    COUNT(WISHLIST_ID) AS WISH_TOTAL_COUNT "
			+ "  FROM ( "
			+ "    SELECT "
			+ "      M.MEMBER_ID, "
			+ "      P.PRODUCT_ID, "
			+ "      P.PRODUCT_NAME, "
			+ "      P.PRODUCT_CATEGORY, "
			+ "      P.PRODUCT_IMG, "
			+ "      P.PRODUCT_PRICE, "
			+ "      W.WISHLIST_ID, "
			+ "      TRUNC(MONTHS_BETWEEN(SYSDATE, M.MEMBER_BIRTH) / 12) AS AGE "
			+ "    FROM MEMBER M "
			+ "    JOIN WISHLIST W ON M.MEMBER_ID = W.MEMBER_ID "
			+ "    JOIN PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "  ) Q "
			+ "  WHERE AGE >= ? AND AGE < ? "
			+ "  GROUP BY "
			+ "    CASE "
			+ "      WHEN AGE >= 10 AND AGE < 20 THEN '10대' "
			+ "      WHEN AGE >= 20 AND AGE < 30 THEN '20대' "
			+ "      WHEN AGE >= 30 AND AGE < 40 THEN '30대' "
			+ "      WHEN AGE >= 40 AND AGE < 50 THEN '40대' "
			+ "      ELSE '50대 이상' "
			+ "    END, PRODUCT_ID, PRODUCT_NAME, PRODUCT_IMG, PRODUCT_CATEGORY, PRODUCT_PRICE "
			+ "  ORDER BY COUNT(WISHLIST_ID) DESC "
			+ ") "
			+ "WHERE ROWNUM <= 6 ";
	
	/*
	 * selectAll에서 상품별 재고를 가져온 후 재고값을 가진 hidden 속성의 div태그로 작성 후
	 * 재고가 0인 상품의 상품정보를 그레이스케일CSS 작업 후
	 * 상품목록 중 재고가 0인 상품 반복문을 통해 delete 기능 수행
	 */
	private static final String SELECTALL_WISHLIST_BY_MEMBER = // 위시리스트 페이지
			  "SELECT "
			+ "    M.MEMBER_NAME, "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_IMG, "
			+ "    P.PRODUCT_STOCK " // 상품재고 - 품절상품제거 로직에서 사용
			+ "FROM  "
			+ "    WISHLIST W "
			+ "JOIN "
			+ "    MEMBER M ON W.MEMBER_ID = M.MEMBER_ID "
			+ "JOIN "
			+ "    PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE M.MEMBER_ID=? ";
	
	private static final String SELECTALL_WISHLIST_BY_MEMBER_ISWISHED = // 상품목록페이지 - 로그인상태
			"SELECT "
					+ "    CASE WHEN WL.PRODUCT_ID IS NULL THEN 0 ELSE 1 END AS ISWISHED, "
					+ "    P.PRODUCT_ID, "
					+ "    P.PRODUCT_BRAND, "
					+ "    P.PRODUCT_NAME, "
					+ "    P.PRODUCT_CATEGORY, "
					+ "    P.PRODUCT_PRICE, "
					+ "    P.PRODUCT_IMG "
					+ "FROM "
					+ "    PRODUCT P "
					+ "LEFT OUTER JOIN ( "
					+ "    SELECT PRODUCT_ID "
					+ "    FROM WISHLIST "
					+ "    WHERE MEMBER_ID=? "
					+ ") WL "
					+ "ON P.PRODUCT_ID = WL.PRODUCT_ID ";
	// 상품 검색에 따른 페이지
	private static final String SELECTALL_WISH_SEARCH = 
			"SELECT "
			+ " p.PRODUCT_ID, "
			+ " PRODUCT_NAME, "
			+ " PRODUCT_BRAND, "
			+ " PRODUCT_PRICE, "
			+ " PRODUCT_INFO, "
			+ " PRODUCT_CATEGORY, "
			+ " PRODUCT_IMG, "
			+ " PRODUCT_STOCK, "
			+ " w.MEMBER_ID, "
			+ " w.WISHLIST_ID "
			+ " FROM PRODUCT p "
			+ " LEFT OUTER JOIN WISHLIST w "
			+ "	ON P.PRODUCT_ID = W.PRODUCT_ID "
			+ "	AND W.MEMBER_ID = ? "
			+ " WHERE UPPER(p.PRODUCT_CATEGORY)"
			+ " LIKE UPPER('%'||?||'%') "
			+ " OR UPPER(p.PRODUCT_NAME) "
			+ " LIKE UPPER('%'||?||'%') ";
	
	// 카테고리별 상품추천 - 로그아웃상태
//	private static final String SELECTALL_PRODUCT_CATEGORY_WISH_LOGOUT =
//			"SELECT "
//			+ "	0 AS ISWISHED, "
//			+ "	PRODUCT_ID, "
//			+ "	PRODUCT_NAME, "
//			+ "	PRODUCT_CATEGORY, "
//			+ "	PRODUCT_PRICE, "
//			+ "	PRODUCT_IMG "
//			+ "FROM PRODUCT "
//			+ "WHERE PRODUCT_CATEGORY=? "
//			+ "AND ROWNUM <= 4 ";
	
	//카테고리별 상품추천 - 로그인 상태
//	private static final String SELECTALL_PRODUCT_CATEGORY_WISH_LOGIN =
//			"SELECT "
//			+ "    CASE WHEN W.PRODUCT_ID IS NOT NULL THEN 1 ELSE 0 END AS ISWISHED, "
//			+ "    P.PRODUCT_ID, "
//			+ "    P.PRODUCT_NAME, "
//			+ "    P.PRODUCT_BRAND, "
//			+ "    P.PRODUCT_CATEGORY, "
//			+ "    P.PRODUCT_PRICE, "
//			+ "    P.PRODUCT_IMG "
//			+ "FROM PRODUCT P "
//			+ "LEFT JOIN WISHLIST W ON P.PRODUCT_ID = W.PRODUCT_ID "
//			+ "AND W.MEMBER_ID = ? "
//			+ "WHERE P.PRODUCT_CATEGORY = ? "
//			+ "AND ROWNUM <= 4";
	
	private static final String SELECTONE_GET_PRODUCT_NAME=
			"SELECT PRODUCT_ID, PRODUCT_NAME FROM PRODUCT WHERE PRODUCT_ID=?";
	private static final String SELECTONE_GET_PRODUCT_PRICE=
			"SELECT PRODUCT_PRICE FROM PRODUCT WHERE PRODUCT_ID=?";
	
	
	private static final String SELECTONE_PRODUCT_DETAIL_LOGIN = // 상품상세페이지 - 로그인상태
			"SELECT "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_INFO, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_STOCK, "
			+ "    P.PRODUCT_IMG, "
			+ "    ( "
			+ "        SELECT COUNT(W.WISHLIST_ID) "
			+ "        FROM WISHLIST W "
			+ "        WHERE W.PRODUCT_ID = P.PRODUCT_ID "
			+ "    ) AS WISH_TOTAL_CNT, " // 상품별 찜 수량 (WISH_TOTAL_CNT)
			+ "    MAX(CASE WHEN W.PRODUCT_ID IS NOT NULL THEN 1 ELSE 0 END) AS ISWISHED "
			+ "FROM "
			+ "    MEMBER M "
			+ "JOIN "
			+ "    PRODUCT P ON P.PRODUCT_ID = ? "
			+ "LEFT JOIN "
			+ "    WISHLIST W ON W.MEMBER_ID = M.MEMBER_ID AND W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE "
			+ "    M.MEMBER_ID = ? "
			+ "GROUP BY "
			+ "    P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_BRAND, P.PRODUCT_PRICE, P.PRODUCT_INFO, "
			+ "    P.PRODUCT_CATEGORY, P.PRODUCT_STOCK, P.PRODUCT_IMG ";
	
	private static final String SELECTONE_PRODUCT_DETAIL_LOGOUT = // 상품상세페이지 - 로그아웃 상태
			"SELECT "
			+ "	0 AS ISWISHED, "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_INFO, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_STOCK, "
			+ "    P.PRODUCT_IMG, "
			+ "    ( "
			+ "        SELECT COUNT(W.WISHLIST_ID) "
			+ "        FROM WISHLIST W "
			+ "        WHERE W.PRODUCT_ID = P.PRODUCT_ID "
			+ "    ) AS WISH_TOTAL_CNT, "
			+ "    MAX(CASE WHEN W.PRODUCT_ID IS NOT NULL THEN 1 ELSE 0 END) AS ISWISHED "
			+ "FROM "
			+ "    PRODUCT P "
			+ "LEFT JOIN "
			+ "    WISHLIST W ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE "
			+ "    P.PRODUCT_ID = ? "
			+ "GROUP BY "
			+ "    P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_BRAND, P.PRODUCT_PRICE, P.PRODUCT_INFO, "
			+ "    P.PRODUCT_CATEGORY, P.PRODUCT_STOCK, P.PRODUCT_IMG ";
	
	private static final String SELECTONE_WISHLIST_CNT_BY_MEMBER = // 헤더페이지 - 회원별 위시리스트 수량
			"SELECT COUNT(WISHLIST_ID) AS WISHLIST_CNT FROM WISHLIST WHERE MEMBER_ID=? ";
	
	/*찜여부 확인*/
	private static final String SELECTONE_IS_PRODUCT_IN_WISHLIST = 
			"SELECT WISHLIST_ID "
			+ "FROM WISHLIST "
			+ "WHERE MEMBER_ID=? AND PRODUCT_ID=? ";
	
	private static final String SELECTONE_WISH_TOTAL_CNT = // 상품상세페이지 - 해당상품 찜 총수량
			"SELECT "
			+ "	COUNT(W.WISHLIST_ID) AS WISH_TOTAL_CNT  "
			+ "FROM WISHLIST W "
			+ "JOIN PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE P.PRODUCT_ID=? ";
	
	// 회원 나이 계산 - 연령별 상품추천 로직 밑작업
	private static final String SELECTONE_MEMBER_AGE = // 상품상세페이지(연령별 상품추천로직과 함께 사용) - 회원 나이계산
			"SELECT "
			+ "	M.MEMBER_ID, "
			+ "	TRUNC(MONTHS_BETWEEN(SYSDATE, M.MEMBER_BIRTH)/12) AS AGE "
			+ "FROM MEMBER M "
			+ "WHERE M.MEMBER_ID= ? ";
	
	// 상품상세페이지 찜 여부 확인
	private static final String SELECTONE_CHECK_ISWISHED = // 상품상세페이지(연령별 상품추천로직과 함께 사용) - 회원 찜여부 확인
			  "SELECT "
			+ "    CASE WHEN WL.PRODUCT_ID IS NULL THEN 0 ELSE 1 END AS ISWISHED "
			+ "FROM "
			+ "    MEMBER M "
			+ "LEFT JOIN "
			+ "    WISHLIST WL ON M.MEMBER_ID = WL.MEMBER_ID AND WL.PRODUCT_ID = ? "
			+ "WHERE "
			+ "    M.MEMBER_ID = ? ";
	
	// KOD사이트에 가입한 회원들 중 가장 많은 나이대가 속한 연령대 상품추천로직 밑작업 - 상품상세페이지 하단에서 구현, 로그아웃상태
	private static final String SELECTONE_MOST_AGE_RANGE = // 상품상세페이지 (연령별 상품추천로직과 함께 사용) - 로그아웃상태
			"SELECT AGE_RANGE, MEMBER_COUNT FROM ( "
			+ "    SELECT  "
			+ "        CASE  "
			+ "            WHEN AGE >= 10 AND AGE < 20 THEN 10  "
			+ "            WHEN AGE >= 20 AND AGE < 30 THEN 20  "
			+ "            WHEN AGE >= 30 AND AGE < 40 THEN 30  "
			+ "            WHEN AGE >= 40 AND AGE < 50 THEN 40  "
			+ "            ELSE 50  "
			+ "        END AS AGE_RANGE,  "
			+ "        COUNT(MEMBER_ID) AS MEMBER_COUNT "
			+ "    FROM (  "
			+ "        SELECT  "
			+ "            MEMBER_ID,  "
			+ "            TRUNC(MONTHS_BETWEEN(SYSDATE, MEMBER_BIRTH) / 12) AS AGE  "
			+ "        FROM  "
			+ "            MEMBER  "
			+ "    ) AGE_DATA  "
			+ "    GROUP BY  "
			+ "        CASE  "
			+ "            WHEN AGE >= 10 AND AGE < 20 THEN 10  "
			+ "            WHEN AGE >= 20 AND AGE < 30 THEN 20  "
			+ "            WHEN AGE >= 30 AND AGE < 40 THEN 30  "
			+ "            WHEN AGE >= 40 AND AGE < 50 THEN 40  "
			+ "            ELSE 50  "
			+ "        END  "
			+ "    ORDER BY MEMBER_COUNT DESC, AGE_RANGE ASC "
			+ ") WHERE ROWNUM = 1";
	
	private static final String SELECTONE_GET_PRODUCT_ID=
			"SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NAME=?";
	
	private static final String SELECTONE_GET_PRODUCT_DATA="SELECT "
			+ "P.PRODUCT_ID, "
			+ "P.PRODUCT_NAME, "
			+ "P.PRODUCT_BRAND, "
			+ "P.PRODUCT_PRICE, "
			+ "P.PRODUCT_STOCK, "
			+ "P.PRODUCT_INFO, "
			+ "C.CATEGORY_TYPE "
			+ "FROM PRODUCT P "
			+ "JOIN "
			+ "CATEGORY C ON C.CATEGORY_ID=P.CATEGORY_ID "
			+ "WHERE "
			+ "P.PRODUCT_ID=?";
	private static final String INSERT=
			  "INSERT INTO "
			+ "PRODUCT (PRODUCT_NAME,PRODUCT_BRAND,PRODUCT_PRICE,PRODUCT_INFO,PRODUCT_STOCK,CATEGORY_ID) "
			+ "VALUES (?,?,?,?,?,?)";
	
	private static final String INSERT_WISHLIST_BY_PRODUCT = // 위시리스트 추가
			  "INSERT INTO WISHLIST (WISHLIST_ID,MEMBER_ID, PRODUCT_ID) "
			+ "VALUES ((SELECT NVL(MAX(WISHLIST_ID),0)+1 FROM WISHLIST),?, ?) ";
	
	private static final String UPDATE=
			"UPDATE PRODUCT SET PRODUCT_STOCK = PRODUCT_STOCK - ? WHERE PRODUCT_ID = ?";
	private static final String UPDATE_PRODUCT = "UPDATE "
			+ "PRODUCT "
			+ "SET "
			+ "PRODUCT_NAME=?, "
			+ "PRODUCT_PRICE =?, "
			+ "PRODUCT_INFO =?, "
			+ "PRODUCT_STOCK=?, "
			+ "CATEGORY_ID =? "
			+ "WHERE PRODUCT_ID =? ";
	private static final String DELETE=
			"DELETE FROM PRODUCT WHERE PRODUCT_ID=?";
	private static final String DELETE_WISHLIST_BY_PRODUCT = // 위시리스트 삭제
			  "DELETE FROM WISHLIST "
			+ "WHERE MEMBER_ID = ? AND PRODUCT_ID = ? ";
	
	/*
	  isWished 클래스를 가진 버튼이 클릭되면 
		 비동기적으로 상품을 추가하는 기능을 수행하고 싶어
		 isWished클래스를 가진 버튼이 클릭될때
		 해당 제품이 가진 isWished 변수의 값이 false라면 
		 데이터베이스에 있는 해당멤버의 위시리스트목록에 해당제품을 추가하고
		 true로 변경해준 뒤 하트에 빨강색을 칠해준다
		 만약 
		 회원이 iswished버튼을 클릭할 때
		 iswished의 값이 true라면
		 데이터베이스에 있는 해당멤버의 위시리스트목록에 있는 해당제품을 삭제하고
		 iswished의 값을 false로 변경해준뒤 색깔을 비워준다.
		 
		 jsp에서 비동기적으로 보여질 부분은 상품이미지 속 하트버튼
	 */

	public List<ProductDTO> selectAll(ProductDTO productDTO) {
		if(productDTO.getSearchCondition().equals("quarterlyStatistics")) {
			System.out.println("[로그:정현진] ProductDAO 분기통계 들어옴");
			return jdbcTemplate.query(SELECTALL_QUARTERLY_STATISTICS, new ProductRowMapperQuarterlyStatistics());
		}
		else if(productDTO.getSearchCondition().equals("quarterlyRevenueFor2Years")) {
			System.out.println("[로그:정현진] ProductDAO 분기 매출 들어옴");
			return jdbcTemplate.query(SELECTALL_QUARTERLY_REVENUE_FOR_2YEARS, new ProductRowMapperQuarterRevenue());
		}
		else if(productDTO.getSearchCondition().equals("monthlyRevenueFor1Year")) {
			System.out.println("[로그:정현진] ProductDAO 월간 매출 들어옴");
			return jdbcTemplate.query(SELECTALL_MONTHLY_REVENUE_FOR_1YEAR, new ProductRowMapperMonthlyRevenue());
		}
		else if(productDTO.getSearchCondition().equals("dailyRevenueFor30Days")) {
			System.out.println("[로그:정현진] ProductDAO 일간 매출 들어옴");
			return jdbcTemplate.query(SELECTALL_DAILY_REVENUE_FOR_30DAYS, new ProductRowMapperDailyRevenue());
		}
//		else if(productDTO.getSearchCondition().equals("allProductsDatas")) {
//			System.out.println("[로그:정현진] ProductDAO 상품목록 조회 들어옴");
//			return jdbcTemplate.query(SELECTALL_ALL_PRODUCTS_DATAS, new ProductRowMapperAllProductsDatas());
//		}
		else if(productDTO.getSearchCondition().equals("orderCntAndRevenue")) {
			System.out.println("[로그:정현진] ProductDAO 상품목록 조회 들어옴");
			return jdbcTemplate.query(SELECTALL_ORDER_CNT_AND_REVENUE, new ProductRowMapperOrderCntAndRevenue());
		}
		else {
			return null;
		}
	}

	public ProductDTO selectOne(ProductDTO productDTO) {
		try {
			if(productDTO.getSearchCondition().equals("getProductID")) { // 상품등록 시 사용
				Object[] args = { productDTO.getProductName() };
			return jdbcTemplate.queryForObject(SELECTONE_GET_PRODUCT_ID, args, new productRowMapperGetProductID());
			}
			else if(productDTO.getSearchCondition().equals("getProductName")) { // 결제 시 사용
				Object[] args = { productDTO.getProductID() };
			return jdbcTemplate.queryForObject(SELECTONE_GET_PRODUCT_NAME, args, new productRowMapperGetProductName());
			}
			else if(productDTO.getSearchCondition().equals("getProductData")) {
				Object[] args= {productDTO.getProductID() };
			return jdbcTemplate.queryForObject(SELECTONE_GET_PRODUCT_DATA,args,new productRowMapperGetProductData());
			}
			else if(productDTO.getSearchCondition().equals("getProductPrice")) {
				Object[] args= {productDTO.getProductID() };
				return jdbcTemplate.queryForObject(SELECTONE_GET_PRODUCT_PRICE,args,new productRowMapperGetProductPrice());
			}
		}catch(Exception e) {
			return null;
		}
		return null;
			
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
		int result;
		if(productDTO.getSearchCondition().equals("payment")) {
			result=jdbcTemplate.update(UPDATE,productDTO.getProductStock(),productDTO.getProductID());
		}
		else {
			result=jdbcTemplate.update(UPDATE_PRODUCT,productDTO.getProductName(),productDTO.getProductPrice(),
				productDTO.getProductInfo(),productDTO.getProductStock(),productDTO.getCategoryID(),productDTO.getProductID());
		}
		if(result <=0) {
			return false;
		}
		return true;
	}

	public boolean delete(ProductDTO productDTO) {
		return false;
	}
}


//분기 통계
class ProductRowMapperQuarterlyStatistics implements org.springframework.jdbc.core.RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] ProductRowMapperQuarterlyStatistics 들어옴");
		ProductDTO data = new ProductDTO();
		System.out.println("[로그:정현진] 년\t분기\t상품ID\t상품명\t상품가격\t상품판매수량\t분기동안상품판매수량합계\t상품매출\t분기매출\n"
				+rs.getInt("YEAR")+ "\t"
				+rs.getInt("QUARTER")+ "\t"
				+ rs.getInt("PRODUCT_ID")+"\t"
				+ rs.getString("PRODUCT_NAME")+"\t"
				+ rs.getInt("PRODUCT_PRICE")+"\t"
				+ rs.getInt("PRODUCT_SALES_QUANTITY")+"\t"
				+ rs.getInt("TOTAL_PRODUCT_SALES_QUANTITY_FOR_QUARTER")+"\t"
				+ rs.getInt("PRODUCT_SALES_REVENUE")+"\t"
				+ rs.getInt("QUARTERLY_REVENUE"));
		
		data.setYear(rs.getInt("YEAR")); // 년
		data.setQuarter(rs.getInt("QUARTER")); // 분기
		data.setProductID(rs.getInt("PRODUCT_ID")); // 상품ID
		data.setProductName(rs.getString("PRODUCT_NAME")); // 상품명
		data.setProductPrice(rs.getInt("PRODUCT_PRICE")); // 상품가격
		data.setProductSalesQuantity(rs.getInt("PRODUCT_SALES_QUANTITY")); // 상품판매수량
		data.setTotalProductSalesQuantityForQuarter(rs.getInt("TOTAL_PRODUCT_SALES_QUANTITY_FOR_QUARTER")); // 분기동안상품판매수량 합계
		data.setProductSalesRevenue(rs.getInt("PRODUCT_SALES_REVENUE")); // 상품매출
		data.setQuarterlyRevenue(rs.getInt("QUARTERLY_REVENUE")); // 분기 매출
		return data;
	}
}

//분기매출
class ProductRowMapperQuarterRevenue implements org.springframework.jdbc.core.RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] ProductRowMapperQuarterRevenue 들어옴");
		ProductDTO data = new ProductDTO();
		System.out.println("[로그:정현진] 년  분기  분기별매출 \n"+rs.getInt("YEAR")+"\t"+rs.getInt("QUARTER")+"\t"+rs.getInt("QUARTERLY_REVENUE"));
		data.setYear(rs.getInt("YEAR")); // 년
		data.setQuarter(rs.getInt("QUARTER")); // 분기
		data.setQuarterlyRevenue(rs.getInt("QUARTERLY_REVENUE")); // 분기 매출
		return data;
	}
}

//월간 매출
class ProductRowMapperMonthlyRevenue implements org.springframework.jdbc.core.RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] ProductRowMapperMonthlyRevenue 들어옴");
		ProductDTO data = new ProductDTO();
		System.out.println("[로그:정현진] 년  월  월간매출 \n"+rs.getInt("YEAR")+"\t"+rs.getInt("MONTH")+"\t"+rs.getInt("MONTHLY_REVENUE"));
		data.setYear(rs.getInt("YEAR")); // 년
		data.setMonth(rs.getInt("MONTH")); // 월
		data.setMonthlyRevenue(rs.getInt("MONTHLY_REVENUE")); // 월간 매출
		return data;
	}
}

//일간 매출
class ProductRowMapperDailyRevenue implements org.springframework.jdbc.core.RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] ProductRowMapperDailyRevenue 들어옴");
		ProductDTO data = new ProductDTO();
		System.out.println("[로그:정현진] 년  월  일  일간매출 \n"+rs.getInt("YEAR")+"\t"+rs.getInt("MONTH")+"\t"+rs.getInt("DAY")+"\t"+rs.getInt("DAILY_REVENUE"));
		data.setYear(rs.getInt("YEAR")); // 년
		data.setMonth(rs.getInt("MONTH")); // 월
		data.setDay(rs.getInt("DAY")); // 일
		data.setDailyRevenue(rs.getInt("DAILY_REVENUE")); // 일간 매출
		return data;
	}
}

//당월 주문수, 당월 매출, 당기 주문수, 당기 매출
class ProductRowMapperOrderCntAndRevenue implements org.springframework.jdbc.core.RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] ProductRowMapperOrderCntAndRevenue 들어옴");
		ProductDTO data = new ProductDTO();
		System.out.println("[로그:정현진] 당월주문수  당월매출  당기주문수  당기매출 \n"+rs.getInt("CURRENT_MONTH_ORDER_CNT")+"\t"+rs.getInt("CURRENT_MONTH_REVENUE")+"\t"+rs.getInt("CURRENT_YEAR_ORDER_CNT")+"\t"+rs.getInt("CURRENT_YEAR_REVENUE"));
		data.setMonthlyOrderCnt(rs.getInt("CURRENT_MONTH_ORDER_CNT")); // 당월 주문수
		data.setMonthlyRevenue(rs.getInt("CURRENT_MONTH_REVENUE")); // 당월 매출
		data.setYearlyOrderCnt(rs.getInt("CURRENT_YEAR_ORDER_CNT")); // 당기 주문수
		data.setAnualRevenue(rs.getInt("CURRENT_YEAR_REVENUE")); // 당기 매출
		return data;
	}
}

////상품 전체목록
//class ProductRowMapperAllProductsDatas implements org.springframework.jdbc.core.RowMapper<ProductDTO> {
//	@Override
//	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//		System.out.println("[로그:정현진] ProductRowMapperAllProductsDatas 들어옴");
//		ProductDTO data = new ProductDTO();
//		data.setProductCategory(rs.getString("CATEGORY_TYPE"));
//		data.setProductID(rs.getInt("PRODUCT_ID"));
//		data.setProductBrand(rs.getString("PRODUCT_BRAND"));
//		data.setProductName(rs.getString("PRODUCT_NAME"));
//		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
//		data.setProductStock(rs.getInt("PRODUCT_STOCK"));
//		return data;
//	}
//}

//상품ID 반환 - 상품등록시 활용 (상품이미지 등록 & 상품명 중복확인)
class productRowMapperGetProductID implements RowMapper<ProductDTO>{

	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO productDTO= new ProductDTO();
		System.out.println("[로그:정현진] productRowMapperGetProductID : "+rs.getInt("PRODUCT_ID"));
		productDTO.setProductID(rs.getInt("PRODUCT_ID"));
		return productDTO;
	}
}

// 상품명 반환 - 결제 시 활용
class productRowMapperGetProductName implements RowMapper<ProductDTO>{
	
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO productDTO= new ProductDTO();
		productDTO.setProductID(rs.getInt("PRODUCT_ID"));
		productDTO.setProductName(rs.getString("PRODUCT_NAME"));
		System.out.println("[로그:정현진] 상품ID : "+rs.getInt("PRODUCT_ID"));
		System.out.println("[로그:정현진] 상품명 : "+rs.getString("PRODUCT_NAME"));
		return productDTO;
	}
}
class productRowMapperGetProductPrice implements RowMapper<ProductDTO>{
	
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO productDTO= new ProductDTO();
		productDTO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		System.out.println("[로그:정현진] 상품가격 : "+rs.getInt("PRODUCT_PRICE"));
		return productDTO;
	}
}
class productRowMapperGetProductData implements RowMapper<ProductDTO> {

	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO productDTO=new ProductDTO();
		productDTO.setProductID(rs.getInt("PRODUCT_ID"));
		productDTO.setProductName(rs.getString("PRODUCT_NAME"));
		productDTO.setProductBrand(rs.getString("PRODUCT_BRAND"));
		productDTO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		productDTO.setProductStock(rs.getInt("PRODUCT_STOCK"));
		productDTO.setProductInfo(rs.getString("PRODUCT_INFO"));
		productDTO.setProductCategory(rs.getString("CATEGORY_TYPE"));
		
		return productDTO;
	}
	
}
