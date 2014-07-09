package com.app.orderbuzz;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.orderbuzz.domain.Product;
import com.app.orderbuzz.domain.Restaurant;
import com.app.orderbuzz.dto.ResourcesDto;
import com.app.orderbuzz.dto.RestaurantDto;
import com.app.orderbuzz.service.MenuService;
import com.app.orderbuzz.service.RestaurantService;


/**
 * 
 * @author tajindsi
 * 
 * This controller will serve request which have resturant in URL 
 * to check resturant in URL we have used @Request mapping
 * @Controller makes this class a Singleton bean
 */
@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	/**
	 * Look for a bean with type ResturantServiceImpl 
	 * and initialize resturantService object
	 */
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private MenuService menuService;
	
	/*
	 * This method will provide list of all restaurants near to user current location.
	 * Input - users Latitude , user Longitude and radius(kilometers) 
	 * @Return - List of restaurants
	 * Service - //http://localhost:8081/orderking/restaurant/getnearbyrestinfo?Radius=54&Lat=45.81&Long=-75.81
	 * 
	 */
	
	@RequestMapping(value = "/getnearbyrestinfo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<RestaurantDto> getUserList (
			@RequestParam("Radius") String Radius,
			@RequestParam("Lat") String Lat,
			@RequestParam("Long") String Long){
		return restaurantService.getRestaurantsList( Lat,  Long ,  Radius);
	}
	
		
	/**
	 * This method will provide Restaurant menu based on Restaurant ID 
	 * @param restId
	 * http://orderbuzz-orderbuzz.rhcloud.com/orderbuzz/restaurant/getrestmenu/1
	 * @return
	*/
	
	@RequestMapping(value = "/getrestmenu/{restId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public  List<Product> getResturantProducts(@PathVariable long restId) {
		return menuService.getRestaurantProducts(restId);
	}
	
	@RequestMapping(value = "/getappresources", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public  List<ResourcesDto> getAppResources() {
		return restaurantService.getAppResources();
	}
	
	@RequestMapping(value = "/gettoprestinfo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<RestaurantDto> getTopRestaurantsList(){
		return restaurantService.getTopRestaurantsList();
	}
	
	
		
}
