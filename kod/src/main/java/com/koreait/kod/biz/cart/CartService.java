package com.koreait.kod.biz.cart;

import java.util.List;

public interface CartService {
	List<CartDTO> selectAll(CartDTO cartDTO);
	CartDTO selectOne(CartDTO cartDTO);
	
	boolean insert(CartDTO cartDTO);
	boolean update(CartDTO cartDTO);
	boolean delete(CartDTO cartDTO);
}
