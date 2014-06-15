package com.app.orderbuzz.service;

import java.util.List;

import com.app.orderbuzz.domain.Product;



public interface MenuService {

		public List<Product> getRestaurantProducts(long restId);
	

}
