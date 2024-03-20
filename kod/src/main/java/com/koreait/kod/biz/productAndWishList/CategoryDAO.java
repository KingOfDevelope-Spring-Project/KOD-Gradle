package com.koreait.kod.biz.productAndWishList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("categoryDAO")
public class CategoryDAO {
	
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL="SELECT CATEGORY_ID,CATEGORY_TYPE FROM CATEGORY";
	private static final String SELECTONE="SELECT CATEGORY_ID,CATEGORY_TYPE FROM CATEGORY WHERE CATEGORY_ID = ?";
	private static final String INSERT="INSERT INTO CATEGORY (CATEGORY_TYPE) VALUES (?)";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<CategoryDTO> selectAll(CategoryDTO categoryDTO) {
		try {
		return jdbcTemplate.query(SELECTALL, new CategoryRowMapper());
		}catch(Exception e) {
			return null;
		}
	}

	public CategoryDTO selectOne(CategoryDTO categoryDTO) {
		try{
			Object [] args= {categoryDTO.getCategoryID() };
		return jdbcTemplate.queryForObject(SELECTONE, args, new CategoryRowMapper());
		}catch (Exception e) {
		return null;
		}
	}

	public boolean insert(CategoryDTO categoryDTO) {
		int result = jdbcTemplate.update(INSERT,categoryDTO.getCategoryType());
		if(result <= 0 ) {
			return false;
		}
		return true;
	}

	public boolean update(CategoryDTO categoryDTO) {
		return false;
	}

	public boolean delete(CategoryDTO categoryDTO) {
		return false;
	}

}

class CategoryRowMapper implements RowMapper<CategoryDTO>{

	@Override
	public CategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.setCategoryID(rs.getInt("CATEGORY_ID"));
		categoryDTO.setCategoryType(rs.getString("CATEGORY_TYPE"));
		return categoryDTO;
	}
}	
	
	
