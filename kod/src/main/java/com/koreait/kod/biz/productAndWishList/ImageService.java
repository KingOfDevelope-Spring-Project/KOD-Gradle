package com.koreait.kod.biz.productAndWishList;

import java.util.List;

public interface ImageService {
	
	List<ImageDTO> selectAll(ImageDTO imageDTO);
	ImageDTO selectOne(ImageDTO imageDTO);
	
	boolean insert(ImageDTO imageDTO);
	boolean update(ImageDTO imageDTO);
	boolean delete(ImageDTO imageDTO);

}
