package com.koreait.kod.biz.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("cartDAO")
public class CartDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL="";
	private static final String SELECTONE="";
	private static final String INSERT="";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<CartDTO> selectAll(CartDTO cartDTO) {
		return null;
	}

	public CartDTO selectOne(CartDTO cartDTO) {
		return null;
	}

	public boolean insert(CartDTO cartDTO) {
		return false;
	}

	public boolean update(CartDTO cartDTO) {
		return false;
	}

	public boolean delete(CartDTO cartDTO) {
		return false;
	}
}
