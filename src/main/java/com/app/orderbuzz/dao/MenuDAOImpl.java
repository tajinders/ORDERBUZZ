package com.app.orderbuzz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.orderbuzz.domain.Product;



@Repository
public class MenuDAOImpl implements MenuDAO{
	
	/*We have defined sessionFactory bean in XML, 
	 * @Autowired annotation is look for bean with type Session Factory in xml 
	 * And initialize sessionFactory with it
	 * */
	@Autowired 
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public  List<Product> getRestaurantProducts(long restId) {
		Session session = getSessionFactory().openSession();
		String hql="select R.restProdList from Restaurant R where R.restId="+restId;
		Query query = session.createQuery(hql);
		List <Product> productList = query.list();
		session.close();
		return productList;
	}

} 
