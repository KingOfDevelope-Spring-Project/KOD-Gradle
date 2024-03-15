package com.koreait.kod.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderContentService")
public class OrderContentServiceImpl implements OrderContentService {
	@Autowired 
	OrderContentDAO orderContentDAO;

	@Override
	public List<OrderContentDTO> selectAll(OrderContentDTO orderContentDTO) {
		return orderContentDAO.selectAll(orderContentDTO);
	}

	@Override
	public OrderContentDTO selectOne(OrderContentDTO orderContentDTO) {
		return orderContentDAO.selectOne(orderContentDTO);
	}

	@Override
	public boolean insert(OrderContentDTO orderContentDTO) {
		return orderContentDAO.insert(orderContentDTO); 
	} 

	@Override
	public boolean update(OrderContentDTO orderContentDTO) {
		return orderContentDAO.update(orderContentDTO);
	}

	@Override
	public boolean delete(OrderContentDTO orderContentDTO) {
		return orderContentDAO.delete(orderContentDTO);
	}
}
