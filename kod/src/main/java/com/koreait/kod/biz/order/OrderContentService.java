package com.koreait.kod.biz.order;

import java.util.List;

public interface OrderContentService {
	List<OrderContentDTO> selectAll(OrderContentDTO orderContentDTO);
	OrderContentDTO selectOne(OrderContentDTO orderContentDTO);
	boolean insert(OrderContentDTO orderContentDTO);
	boolean update(OrderContentDTO orderContentDTO);
	boolean delete(OrderContentDTO orderContentDTO);
}
