package com.app.orderbuzz.dao;
import java.util.List;

import com.app.orderbuzz.domain.Restaurant;



public interface RestaurantDAO {

	public List<Restaurant> getRestaurantsList(String Lat, String Long, String Radius );
	
	
}
