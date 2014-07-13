package com.app.orderbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.orderbuzz.dao.OrderDAO;
import com.app.orderbuzz.domain.Order;



@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public List<Order> getOrdersForResturant(long restId, String status) {
		return orderDAO.getOrdersForResturant(restId, status);

	}

	@Override
	public Boolean SubmitOrder(Order newOrder ) {
		return orderDAO.SubmitOrder(newOrder);
		
	}

	@Override
	public void processedOrder(String restId, String orderSeqNo) {
		orderDAO.processedOrder(restId, orderSeqNo);
		
	}
	
	

}
