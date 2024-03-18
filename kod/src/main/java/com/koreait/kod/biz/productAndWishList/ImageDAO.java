package com.koreait.kod.biz.productAndWishList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("imageDAO")
public class ImageDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL="";
	private static final String SELECTONE="";
	private static final String INSERT="";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<ImageDTO> selectAll(ImageDTO imageDTO) {
		return null;
	}

	public ImageDTO selectOne(ImageDTO imageDTO) {
		return null;
	}

	public boolean insert(ImageDTO imageDTO) {
		return false;
	}

	public boolean update(ImageDTO imageDTO) {
		return false;
	}

	public boolean delete(ImageDTO imageDTO) {
		return false;
	}

}
