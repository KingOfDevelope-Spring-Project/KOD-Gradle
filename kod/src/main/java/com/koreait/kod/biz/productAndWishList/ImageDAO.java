package com.koreait.kod.biz.productAndWishList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("imageDAO")
public class ImageDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL="SELECT IMAGE_ID,IMAGE_URL FROM IMAGE WHERE PRODUCT_ID=?";
	private static final String SELECTONE="";
	private static final String INSERT_PRODUCT_IMAGE="INSERT INTO IMAGE(PRODUCT_ID,IMAGE_URL,IMAGE_CATEGORY)VALUES(?,?,'PRODUCT')";
	private static final String UPDATE="UPDATE IMAGE SET IMAGE_URL = ? WHERE IMAGE_ID = ?";
	private static final String DELETE="";

	public List<ImageDTO> selectAll(ImageDTO imageDTO) {
		Object[] args= {imageDTO.getProductID() };
		try {
			return jdbcTemplate.query(SELECTALL,args, new imageRowMapper());
		}catch(Exception e) {
			return null;
		}
	}

	public ImageDTO selectOne(ImageDTO imageDTO) {
		return null;
	}

	public boolean insert(ImageDTO imageDTO) {
		int result = jdbcTemplate.update(INSERT_PRODUCT_IMAGE,imageDTO.getProductID(),imageDTO.getImageUrl());
		if(result <= 0) {
			return false;
		}
		return true;
	}

	public boolean update(ImageDTO imageDTO) {
		int result= jdbcTemplate.update(UPDATE,imageDTO.getImageUrl(),imageDTO.getImageID());
		if(result<=0) {			
			return false;
		}
		return true;
	}

	public boolean delete(ImageDTO imageDTO) {
		return false;
	}

}

class imageRowMapper implements RowMapper<ImageDTO>{

	@Override
	public ImageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ImageDTO imageDTO=new ImageDTO();
		imageDTO.setImageID(rs.getInt("IMAGE_ID"));
		imageDTO.setImageUrl(rs.getString("IMAGE_URL"));
		return imageDTO;
	}
	
}
