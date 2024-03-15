package com.koreait.kod.biz.order;

import java.util.List;

public interface OrderListService {
	List<OrderListDTO> selectAll(OrderListDTO orderListDTO);
	OrderListDTO selectOne(OrderListDTO orderListDTO);
	
	boolean insert(OrderListDTO orderListDTO);
	boolean update(OrderListDTO orderListDTO);
	boolean delete(OrderListDTO orderListDTO);
}
