package com.koreait.kod.model.productAndWishlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements ProductService{
	@Autowired 
	ProductDAO productDAO;

	@Override
	public List<ProductDTO> selectAll(ProductDTO productDTO) {
		return productDAO.selectAll(productDTO);
	}

	@Override
	public ProductDTO selectOne(ProductDTO productDTO) {
		return productDAO.selectOne(productDTO);
	}

	@Override
	public boolean insert(ProductDTO productDTO) {
		return productDAO.insert(productDTO); 
	} 

	@Override
	public boolean update(ProductDTO productDTO) {
		return productDAO.update(productDTO);
	}

	@Override
	public boolean delete(ProductDTO productDTO) {
		return productDAO.delete(productDTO);
	}
}
