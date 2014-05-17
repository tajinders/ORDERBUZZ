package com.app.orderbuzz.dao;

import java.util.List;

import com.app.orderbuzz.domain.Product;



public interface MenuDAO {
	public List<Product> getRestaurantProducts(long restId);
}
