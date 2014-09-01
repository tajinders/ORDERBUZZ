package com.app.orderbuzz.dao;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.app.orderbuzz.domain.Order;
import com.app.orderbuzz.domain.Product;
import com.app.orderbuzz.domain.User;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;



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
		String hql = "Select R.orderList from Restaurant R where R.restId="+restId;
		Query query = session.createQuery(hql);
		List <Order> orderList = query.list();
		Iterator<Order> i = orderList.iterator();

		String Filter = null;
		if (status.equalsIgnoreCase("done")){
			Filter = "pending";	
		}
		else if (status.equalsIgnoreCase("pending")){
			Filter ="done";
		}

		while (i.hasNext()) {
			Order order = i.next();
			if (order.getOrderStatus().equalsIgnoreCase(Filter)){
				i.remove(); 
			}  
		}

		session.close();
		return orderList;

	}

	private Boolean StripePayment(String stripeToken , String totalPrice )
	{
		Stripe.apiKey = "sk_test_4KzvykNVDMae6nVPgvgt4lLb";
		Map<String, Object> chargeParams = new HashMap<String, Object>();

		chargeParams.put("amount", (int)Float.parseFloat(totalPrice)*100); 
		chargeParams.put("currency", "usd");
		chargeParams.put("card", stripeToken);
		chargeParams.put("description", "Charge for Orderbuzz online order");

		try {
			Boolean payment = Charge.create(chargeParams).getPaid();
			if (!payment)
				return false; 

		} catch (AuthenticationException e) {
			e.printStackTrace();
			return false; 
		} catch (InvalidRequestException e) {
			e.printStackTrace();
			return false; 
		} catch (APIConnectionException e) {
			e.printStackTrace();
			return false; 
		} catch (CardException e) {
			e.printStackTrace();
			return false; 
		} catch (APIException e) {
			e.printStackTrace();
			return false; 
		}
		return true;
	}

	@Transactional
	@Override	
	public Boolean SubmitOrder(Order newOrder)
	{ 
		String restId = newOrder.getRestId();
		String Stripetoken = newOrder.getStripetokenno();
		String totalPrice = newOrder.getTotalPrice();
		newOrder.setOrderStatus("pending");

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Boolean Status = StripePayment(Stripetoken , totalPrice);
		if (!Status)
			return false ; 

		// Inserting newOrder in the  Order table

		String hql = "SELECT COALESCE(MAX(OD.ORDER_SEQ_NO),0) FROM RESTAURANT_ORDER AS RO, ORDER_DETAILS AS OD WHERE RO.ORDER_ID_FK = OD.ORDER_ID_PK AND RO.REST_ID_FK="+restId;
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
		hql="insert into RESTAURANT_ORDER(REST_ID_FK,ORDER_ID_FK) values('"+restId+"','"+OrderId+"')";
		query = session.createSQLQuery(hql);
		query.executeUpdate();

		//updating restaurant queue no in DB
		hql="update RESTAURANT set REST_QUEUENO=REST_QUEUENO+1 WHERE REST_ID_PK ='"+restId+"'";
		query = session.createSQLQuery(hql);
		query.executeUpdate();

		session.getTransaction().commit();
		session.close();
		return true;
	}

	//http://localhost:8080/orderking/order/processedorder?restid=1&orderseqno=1
	@Transactional
	public void processedOrder(String restId, String orderSeqNo){

		Session session = getSessionFactory().openSession();
		String mobilezRegkey;
		String restName;

		//Update Status 
		String sql =  "UPDATE ORDER_DETAILS od inner join RESTAURANT_ORDER ro ON od.ORDER_ID_PK = ro.ORDER_ID_FK  set od.ORDER_STATUS='Done' Where od.ORDER_STATUS='pending' And od.ORDER_SEQ_NO=" + orderSeqNo;
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();


		// Get Mobile_KEY
		sql = "Select ORDER_GCMKEY from ORDER_DETAILS od inner join RESTAURANT_ORDER ro ON od.ORDER_ID_PK = ro.ORDER_ID_FK Where od.ORDER_STATUS='Done' And od.ORDER_SEQ_NO=" + orderSeqNo;
		query = session.createSQLQuery(sql);
		mobilezRegkey = (String) query.list().get(0);
		System.out.println(mobilezRegkey);


		// Get restaurant Name 
		sql = "Select R.REST_NAME  from RESTAURANT R inner join ADDRESS A ON R.GEOFENCE_ID_FK = A.GEOFENCE_ID_PK where R.REST_ID_PK="+restId;
		query = session.createSQLQuery(sql);
		restName = (String) query.list().get(0);
		System.out.println(restName);
		mobilezRegkey = "APA91bFKplHJrRLIuuRBh2xgHMtu7toVdqyc5lEvET41twi4HRQe0XZQ8_9__noZxCgQEHDB9kXhTyG5imv7C0G1DYfZrXZ6cYuObjbo5s91EzkYUpD8w1oO1E1cDflEGxeldQfilhM3ll5WCiMx-xBAv6z4DqTHWw";
		// GCM push
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://android.googleapis.com/gcm/send?=&=";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "key=AIzaSyDM5TT-MtJeiOD3j5wax5nXxldJUKrK0vE");
		requestHeaders.set("Content-type", "application/x-www-form-urlencoded");
		MultiValueMap<String, String> postParams = new LinkedMultiValueMap<String, String>();
		postParams.add("registration_id",mobilezRegkey);
		
		String data = "Your Order at "+restName.toUpperCase()+" is ready. Kindly Visit the Store";
		postParams.add("data", data);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParams, requestHeaders);
		restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		// Update restaurant waiting queue no
		sql = "UPDATE RESTAURANT SET REST_QUEUENO=REST_QUEUENO-1 WHERE REST_ID_PK="+restId;
		query = session.createSQLQuery(sql);
		query.executeUpdate();

		// close session
		session.close();


	}

	@Override
	public String VendorAuthentication(User user) {
		// TODO Auto-generated method stub

		Session session = getSessionFactory().openSession();
		String sql = "Select U.REST_ID  from USER U where U.USER_ID='"+user.getUserid()+"' and U.PASSWORD='"+user.getPassword()+"'";
		Query query = session.createSQLQuery(sql);
		String restId;
		if(query.list().isEmpty()) {
			restId = null;
		}else {
			restId = (String) query.list().get(0);
		}
		return restId;
	}


}
