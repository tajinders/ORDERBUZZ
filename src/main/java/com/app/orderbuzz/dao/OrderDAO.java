package com.app.orderbuzz.dao;

import java.util.List;

import com.app.orderbuzz.domain.Order;
import com.app.orderbuzz.domain.User;
import com.app.orderbuzz.dto.OrderDto;



public interface OrderDAO {
	public abstract List<Order>getOrdersForResturant(long restId, String status);
	public abstract Boolean SubmitOrder(Order newOrder );
	public abstract void processedOrder(String restId, String orderId);
	public abstract String VendorAuthentication(User user); 
}
