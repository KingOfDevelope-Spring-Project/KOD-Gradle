package com.koreait.kod.biz.productAndWishList;

import java.util.List;

public interface WishListService {
	List<WishListDTO> selectAll(WishListDTO wishListDTO);
	WishListDTO selectOne(WishListDTO wishListDTO);
	
	boolean insert(WishListDTO wishListDTO);
	boolean update(WishListDTO wishListDTO);
	boolean delete(WishListDTO wishListDTO);
}
