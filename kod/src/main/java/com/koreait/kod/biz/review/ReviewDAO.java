package com.koreait.kod.biz.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("reviewDAO")
public class ReviewDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

    private static final String SELECTALL="SELECT "
            + "R.REVIEW_ID, "
            + "R.REVIEW_TITLE, "
            + "R.REVIEW_CONTENT, "
            + "R.REVIEW_DATE, "
            + "R.REVIEW_SCORE,"
            + "R.MEMBER_ID"
            + "P.PRODUCT_NAME"
            + "FROM REVIEW R"
            + "JOIN PRODUCT P"
            + "ON P.PRODUCT_ID=R.PRODUCT_ID";
        
	private static final String SELECTONE="";
	private static final String INSERT="";
	private static final String UPDATE="";
    private static final String DELETE="DELETE FROM REVIEW WHARE REVIEW_ID=?";

	public List<ReviewDTO> selectAll(ReviewDTO reviewDTO) {
        try {
        return jdbcTemplate.query(SELECTALL, new ReviewRowMapper());
        }catch (Exception e) {
            return null;
        }
	}

	public ReviewDTO selectOne(ReviewDTO reviewDTO) {
		return null;
	}

	public boolean insert(ReviewDTO reviewDTO) {
		return false;
	}

	public boolean update(ReviewDTO reviewDTO) {
		return false;
	}

	public boolean delete(ReviewDTO reviewDTO) {
        int result = jdbcTemplate.update(DELETE,reviewDTO.getReviewID());
        if(result <=0) {
            return false;
        }
        return true;
    }
}


class ReviewRowMapper implements RowMapper<ReviewDTO> {

    @Override
    public ReviewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewID(rs.getInt("REVIEW_ID"));
        reviewDTO.setReviewTitle(rs.getString("REVIEW_TITLE"));
        reviewDTO.setReviewContent(rs.getString("REVIEW_CONTENT"));
        reviewDTO.setReviewDate(rs.getDate("REVIEW_DATE"));
        reviewDTO.setReviewScore(rs.getInt("REVIEW_SCORE"));
        reviewDTO.setMemberID(rs.getString("MEMBER_ID"));
        reviewDTO.setProductName(rs.getInt("PRODUCT_NAME"));
        return reviewDTO;
    }
    
}
