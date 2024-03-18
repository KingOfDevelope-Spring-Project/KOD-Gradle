package com.koreait.kod.biz.productAndWishList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryrServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO categoryDAO;

	@Override
	public List<CategoryDTO> selectAll(CategoryDTO categoryDTO) {
		return categoryDAO.selectAll(categoryDTO);
	}

	@Override
	public CategoryDTO selectOne(CategoryDTO categoryDTO) {
		return categoryDAO.selectOne(categoryDTO);
	}

	@Override
	public boolean insert(CategoryDTO categoryDTO) {
		return categoryDAO.insert(categoryDTO);
	}

	@Override
	public boolean update(CategoryDTO categoryDTO) {
		return categoryDAO.update(categoryDTO);
	}

	@Override
	public boolean delete(CategoryDTO categoryDTO) {
		return categoryDAO.delete(categoryDTO);
	}

}
