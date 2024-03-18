package com.koreait.kod.biz.productAndWishList;

import java.util.List;

public interface CategoryService {
	List<CategoryDTO> selectAll(CategoryDTO categoryDTO);
	CategoryDTO selectOne(CategoryDTO categoryDTO);
	
	boolean insert(CategoryDTO categoryDTO);
	boolean update(CategoryDTO categoryDTO);
	boolean delete(CategoryDTO categoryDTO);
}
