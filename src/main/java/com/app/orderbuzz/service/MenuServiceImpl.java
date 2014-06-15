package com.app.orderbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.orderbuzz.dao.MenuDAO;
import com.app.orderbuzz.domain.Product;



@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menuDAO;

	@Override
	public List<Product> getRestaurantProducts(long restId) {
		return menuDAO.getRestaurantProducts(restId);
	}
}
