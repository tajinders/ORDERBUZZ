package com.app.orderbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.orderbuzz.dao.RestaurantDAO;
import com.app.orderbuzz.domain.Restaurant;
import com.app.orderbuzz.dto.RestaurantDto;





/**
 * @Service makes ResturantServiceImpl a Singleton bean
 */

@Service
public class RestaurantServiceImpl implements RestaurantService {

	/* look for a bean with type ResturantDAOImpl 
	 * and initialize resturantDao with it
	 */
	@Autowired
	private RestaurantDAO resturantDao;

	@Override
	public List<RestaurantDto> getRestaurantsList(String Lat, String Long, String Radius) {
		return resturantDao.getRestaurantsList(Lat,Long,Radius);
	}

}
