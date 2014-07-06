package com.app.orderbuzz.dao;

import java.util.List;

import com.app.orderbuzz.domain.Order;
import com.app.orderbuzz.dto.OrderDto;



public interface OrderDAO {
	
	public abstract List<Order>getOrdersForResturant(long restId, String status);
	public abstract Boolean SubmitOrder(Order newOrder , String restId, String Stripetoken ,String totalPrice);
	public abstract void processedOrder(String restId, String orderId); 
}
