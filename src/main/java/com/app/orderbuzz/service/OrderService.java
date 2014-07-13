package com.app.orderbuzz.service;

import java.util.List;

import com.app.orderbuzz.domain.Order;




public interface OrderService {
	public List<Order>getOrdersForResturant(long restId, String status);
	public Boolean SubmitOrder(Order newOrder );
	public void processedOrder(String restId, String orderSeqNo);
}
