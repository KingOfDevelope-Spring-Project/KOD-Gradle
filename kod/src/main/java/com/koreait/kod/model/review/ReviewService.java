package com.koreait.kod.model.review;

import java.util.List;

public interface ReviewService {
	List<ReviewDTO> selectAll(ReviewDTO reviewDTO);
	ReviewDTO selectOne(ReviewDTO reviewDTO);
	
	boolean insert(ReviewDTO reviewDTO);
	boolean update(ReviewDTO reviewDTO);
	boolean delete(ReviewDTO reviewDTO);
}
