package com.koreait.kod.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("oderListService")
public class OrderListServiceImpl implements OrderListService{
	@Autowired 
	OrderListDAO orderListDAO;

	@Override
	public List<OrderListDTO> selectAll(OrderListDTO orderListDTO) {
		return orderListDAO.selectAll(orderListDTO);
	}

	@Override
	public OrderListDTO selectOne(OrderListDTO OrderListDTO) {
		return orderListDAO.selectOne(OrderListDTO);
	}

	@Override
	public boolean insert(OrderListDTO orderListDTO) {
		return orderListDAO.insert(orderListDTO); 
	} 

	@Override
	public boolean update(OrderListDTO orderListDTO) {
		return orderListDAO.update(orderListDTO);
	}

	@Override
	public boolean delete(OrderListDTO orderListDTO) {
		return orderListDAO.delete(orderListDTO);
	}
}
