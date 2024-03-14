package com.koreait.kod.model.wishlist;

import java.util.List;

public interface WishlistService {
	List<WishlistDTO> selectAll(WishlistDTO wishListDTO);
	WishlistDTO selectOne(WishlistDTO wishListDTO);
	
	boolean insert(WishlistDTO wishListDTO);
	boolean update(WishlistDTO wishListDTO);
	boolean delete(WishlistDTO wishlistDTO);
}
