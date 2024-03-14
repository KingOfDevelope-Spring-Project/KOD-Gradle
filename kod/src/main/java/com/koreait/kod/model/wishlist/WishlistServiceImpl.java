package com.koreait.kod.model.wishlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class WishlistServiceImpl implements WishlistService{
	@Autowired 
	WishlistDAO wishlistDAO;

	@Override
	public List<WishlistDTO> selectAll(WishlistDTO wishlistDTO) {
		return wishlistDAO.selectAll(wishlistDTO);
	}

	@Override
	public WishlistDTO selectOne(WishlistDTO wishlistDTO) {
		return wishlistDAO.selectOne(wishlistDTO);
	}

	@Override
	public boolean insert(WishlistDTO wishlistDTO) {
		return wishlistDAO.insert(wishlistDTO); 
	} 

	@Override
	public boolean update(WishlistDTO wishlistDTO) {
		return wishlistDAO.update(wishlistDTO);
	}

	@Override
	public boolean delete(WishlistDTO wishlistDTO) {
		return wishlistDAO.delete(wishlistDTO);
	}
}
