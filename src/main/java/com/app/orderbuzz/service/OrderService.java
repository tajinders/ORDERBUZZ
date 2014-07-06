package com.app.orderbuzz.service;

import java.util.List;

import com.app.orderbuzz.domain.Order;




public interface OrderService {
	public List<Order>getOrdersForResturant(long restId, String status);
	public Boolean SubmitOrder(Order newOrder , String restId , String Stripetoken , String totalPrice);
	public void processedOrder(String restId, String orderSeqNo);
}
