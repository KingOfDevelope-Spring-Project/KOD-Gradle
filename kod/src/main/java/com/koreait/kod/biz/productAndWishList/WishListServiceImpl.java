package com.koreait.kod.biz.productAndWishList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wishListService")
public class WishListServiceImpl implements WishListService{
	@Autowired 
	WishListDAO wishListDAO;

	@Override
	public List<WishListDTO> selectAll(WishListDTO wishListDTO) {
		return wishListDAO.selectAll(wishListDTO);
	}

	@Override
	public WishListDTO selectOne(WishListDTO wishListDTO) {
		return wishListDAO.selectOne(wishListDTO);
	}

	@Override
	public boolean insert(WishListDTO wishListDTO) {
		return wishListDAO.insert(wishListDTO); 
	} 

	@Override
	public boolean update(WishListDTO wishListDTO) {
		return wishListDAO.update(wishListDTO);
	}

	@Override
	public boolean delete(WishListDTO wishListDTO) {
		return wishListDAO.delete(wishListDTO);
	}
}
