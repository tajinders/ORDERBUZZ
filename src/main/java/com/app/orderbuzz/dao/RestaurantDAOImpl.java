package com.app.orderbuzz.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderbuzz.domain.Restaurant;
import com.app.orderbuzz.dto.RestaurantDto;




/**
 * @Repositiory makes ResturantDAOImpl a Singleton bean
 */

@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

	/*We have defined sessionFactory bean in XML, 
	 * @Autowired annotation is look for bean with type Session Factory in xml 
	 * And initialize sessionFactory with it
	 * */
	@Autowired 
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/**
	 * This method Will Return Basic Information about Resturant
	 * For example, ID,NAME,ADDRESS,PHOTOURL,QUEUENO
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<RestaurantDto> getRestaurantsList(String Lat, String Long, String Radius) {
		Session session = getSessionFactory().openSession();
		//String hql="select R.restName, R.restId, R.restPhoto, R.restQueueNo, R.restAdd from Restaurant R";
		String sql= "select  R.rest_id_pk , R.rest_Name, R.rest_Photo, R.rest_QueueNo, add_desc, distance_in_km from RESTAURANT As R Inner Join ( SELECT z.GEOFENCE_ID_PK, z.ADD_DESC, "
				+ "p.distance_unit * DEGREES(ACOS(COS(RADIANS(p.latpoint)) * COS(RADIANS(z.latitude)) * "
				+ "COS(RADIANS(p.longpoint) - RADIANS(z.longitude)) + SIN(RADIANS(p.latpoint)) * "
				+ "SIN(RADIANS(z.latitude)))) AS distance_in_km FROM  ADDRESS AS z JOIN ( "
				+ "SELECT "+ Lat + " AS latpoint, "+ Long+ " AS longpoint,"+Radius+" AS radius, "
				+ "111.045 AS distance_unit ) AS p WHERE z.latitude BETWEEN p.latpoint - "
				+ "(p.radius / p.distance_unit) AND p.latpoint  + (p.radius / p.distance_unit) AND "
				+ "z.longitude BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS (p.latpoint)))) "
				+ "AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) "
				+ "ORDER BY distance_in_km ) temp ON R.GEOFENCE_ID_FK = temp.GEOFENCE_ID_PK" ;
	
		Query query = session.createSQLQuery(sql);
		List <RestaurantDto> restaurantList = query.setResultTransformer(Transformers.aliasToBean(RestaurantDto.class)).list();
		session.close();
		return restaurantList;
	}

}
