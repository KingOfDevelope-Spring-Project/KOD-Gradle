package com.koreait.kod.biz.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService{
	@Autowired 
	ReviewDAO reviewDAO;

	@Override
	public List<ReviewDTO> selectAll(ReviewDTO reviewDTO) {
		return reviewDAO.selectAll(reviewDTO);
	}

	@Override
	public ReviewDTO selectOne(ReviewDTO reviewDTO) {
		return reviewDAO.selectOne(reviewDTO);
	}

	@Override
	public boolean insert(ReviewDTO reviewDTO) {
		return reviewDAO.insert(reviewDTO); 
	} 

	@Override
	public boolean update(ReviewDTO reviewDTO) {
		return reviewDAO.update(reviewDTO);
	}

	@Override
	public boolean delete(ReviewDTO reviewDTO) {
		return reviewDAO.delete(reviewDTO);
	}
}
