package com.app.orderbuzz.dao;

import java.util.List;

import com.app.orderbuzz.domain.Order;



public interface OrderDAO {
	
	public abstract List<Order>getOrdersForResturant(long restId, String status);
	public abstract void SubmitOrder(Order newOrder ,String restId);
	public abstract void processedOrder(String restId, String orderId); 

}
