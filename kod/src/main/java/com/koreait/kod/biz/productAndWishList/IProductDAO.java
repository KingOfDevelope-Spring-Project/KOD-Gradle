package com.koreait.kod.biz.productAndWishList;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IProductDAO {
	
	public List<ProductDTO> selectAll(Map<String, Object> map);
}
