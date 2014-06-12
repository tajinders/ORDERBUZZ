package com.app.orderbuzz.service;

import java.util.List;

import com.app.orderbuzz.domain.Order;



public interface OrderService {
	public List<Order>getOrdersForResturant(long restId, String status);
	public void SubmitOrder(Order newOrder ,String restId);
	public void processedOrder(String restId, String orderSeqNo);
}
