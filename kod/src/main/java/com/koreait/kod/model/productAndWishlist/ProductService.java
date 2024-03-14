package com.koreait.kod.model.productAndWishlist;

import java.util.List;

public interface ProductService {
	List<ProductDTO> selectAll(ProductDTO productDTO);
	ProductDTO selectOne(ProductDTO productDTO);
	
	boolean insert(ProductDTO productDTO);
	boolean update(ProductDTO productDTO);
	boolean delete(ProductDTO productDTO);
}
