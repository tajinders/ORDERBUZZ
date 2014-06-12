package com.app.orderbuzz.service;
import java.util.List;

import com.app.orderbuzz.domain.Restaurant;


public interface RestaurantService {
	
	public List<Restaurant> getRestaurantsList( String Lat, String Long, String Radius);

}
