package com.app.orderbuzz.dao;
import java.util.List;

import com.app.orderbuzz.domain.Restaurant;
import com.app.orderbuzz.dto.ResourcesDto;
import com.app.orderbuzz.dto.RestaurantDto;



public interface RestaurantDAO {

	public List<RestaurantDto> getRestaurantsList(String Lat, String Long, String Radius );
	public List<ResourcesDto> getAppResources ();
	
}
