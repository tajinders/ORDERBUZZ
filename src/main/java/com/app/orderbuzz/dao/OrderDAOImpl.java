package com.app.orderbuzz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.app.orderbuzz.domain.Order;
import com.app.orderbuzz.domain.Restaurant;



@Repository
public class OrderDAOImpl implements OrderDAO {


	/*We have defined sessionFactory bean in XML, 
	 * @Autowired annotation is look for bean with type Session Factory in xml 
	 * And initialize sessionFactory with it
	 * */
	@Autowired 
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Order> getOrdersForResturant(long restId, String status) {

		Session session = getSessionFactory().openSession();

		// Selecting all orders 
		String hql="from Order where order_status='"+status+"'";
		Query query = session.createQuery(hql);
		List <Order> orderList = query.list();
		session.close();
		return orderList;

	}

	@Transactional
	@Override	
	public void SubmitOrder(Order newOrder ,String restId)
	{
		Session session = getSessionFactory().openSession();

		// Inserting newOrder in to Order Table

		String hql = "SELECT COALESCE(MAX(OD.order_seq_no,0) FROM restaurant_order AS RO, order_details AS OD WHERE RO.order_id_fk = OD.order_id_pk AND RO.rest_id_fk="+restId;
		long orderSequenceNo = 0;
		Query query = session.createSQLQuery(hql);

		try{
			orderSequenceNo = Long.valueOf(query.uniqueResult().toString());
		}
		catch (Exception ex){
			System.out.println("Null Value Exception - No Record Found - No Orders Avaliable for this resturant");
		}

		newOrder.setOrderSequenceNo(orderSequenceNo+1);
		long OrderId = (Long) session.save(newOrder);

		//Inserting record in to Restaurant_Order Table
		hql="insert into restaurant_order(rest_id_fk,order_id_fk) values('"+restId+"','"+OrderId+"')";
		query = session.createSQLQuery(hql);
		query.executeUpdate();

		//updating restaurant queue no in DB
		hql="update restaurant set rest_queueno=rest_queueno+1 WHERE rest_id_pk ='"+restId+"'";
		query = session.createSQLQuery(hql);
		query.executeUpdate();

		session.close();

	}

	public void processedOrder(String restId, String orderSeqNo){

		Session session = getSessionFactory().openSession();
		String mobilezRegkey;
		String restName;
	
		//Update Status 
		String sql =  "UPDATE order_details od inner join restaurant_order ro ON od.ORDER_ID_PK = ro.ORDER_ID_FK  set od.order_status='Done' Where od.order_status='pending' And od.order_seq_no=" + orderSeqNo;
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();

		// Get Mobile_KEY
		sql = "Select ORDER_GCMKEY from order_details od inner join restaurant_order ro ON od.ORDER_ID_PK = ro.ORDER_ID_FK Where od.order_status='Done' And od.order_seq_no=" + orderSeqNo;
		query = session.createSQLQuery(sql);
		mobilezRegkey = (String) query.list().get(0);
		System.out.println(mobilezRegkey);

		
		// Get restaurant Name 
		sql = "Select R.rest_name  from restaurant R inner join address A ON R.geofence_id_fk = A.geofence_id_pk where R.rest_id_pk="+restId;
		query = session.createSQLQuery(sql);
		restName = (String) query.list().get(0);
		System.out.println(restName);
		
		
		// GCM push
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://android.googleapis.com/gcm/send?=&=";
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "key=AIzaSyDTOBzSdYnJWpY6-ceWPOb91L6ho2LkDpw");
		requestHeaders.set("Content-type", "application/x-www-form-urlencoded");
		

		MultiValueMap<String, String> postParams = new LinkedMultiValueMap<String, String>();
		postParams.add("registration_id",mobilezRegkey);
		postParams.add("data", restName);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParams, requestHeaders);

		restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		
		
		
		
		
		//GCM PUSH
		
		/*
		sendPost("https://android.googleapis.com/gcm/send?=&=",mobileregkey, data);
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Authorization", "key=AIzaSyDTOBzSdYnJWpY6-ceWPOb91L6ho2LkDpw");
		method.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		NameValuePair[] body = { 
				new NameValuePair("registration_id", mobileregkey),
				new NameValuePair("data", data),

		};
		*/
		
		
		// Update restaurant waiting queue no
		sql = "UPDATE restaurant SET rest_queueno=rest_queueno-1 WHERE restid="+restId;
		query = session.createSQLQuery(sql);
		query.executeUpdate();
		
		// close session
		session.close();
		
	}
	

}
