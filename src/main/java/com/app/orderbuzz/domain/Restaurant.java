package com.app.orderbuzz.domain;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Tajinder singh
 * 
 * Resturant class will store details for a each Resturant/FoodOutlet/CofeeCafee
 * Each Resturant Will have List of Products
 *                 Example - TimHORTONS -> [BURGER, DONUT, COFEE etc ]
 * 
 * Hibernate will make table with name RESTURANT for this class with following properties
 * @Entity- This annotation will create a table for this class
 * @Table - This annotation will change default table name 
 * @Id - This annotations is used to make Primary Key
 * @column - This annotation is used to change default column name 
 * @Generated Value - This annotation is used to make field Auto Increment 
 * @OneToOne is used to perform 1-1 mapping between ADDRESS TABLE and RESTURANT TABLE
 * @JoinColumn - This annotation will change the default name of join column of(RESTURANT & ADDRESS table) to REST_ADD
 * @OneToMany mapping will generate a New Mapping table
 * Cascade keyword  is to perform cascade operations
 * Note: Column name ending with PK refers to Primary Key and Column name ending with FK is Forigen Key
 */

@Entity 
@Table(name="RESTAURANT")
public class Restaurant {
	@Id 
	@Column(name="REST_ID_PK")
	@GeneratedValue
	private long restId;

	@Column(name="REST_NAME")
	private String restName;

	/* restQueueNo will show the current waiting 
	List at any given point of time */
	@Column(name="REST_QUEUENO")
	private String restQueueNo;  

	@Column(name="REST_PHOTO")
	private String restPhoto;

	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="GEOFENCE_ID_FK")
	private Address restAdd;

	@JoinTable(
			name = "RESTAURANT_PRODUCT", 
			joinColumns = @JoinColumn(name = "REST_ID_FK"), 
			inverseJoinColumns = @JoinColumn(name = "PROD_ID_FK")
			)
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Product> restProdList;

	

	@JoinTable(
			name = "RESTAURANT_ORDER", 
			joinColumns = @JoinColumn(name = "REST_ID_FK"), 
			inverseJoinColumns = @JoinColumn(name = "ORDER_ID_FK")
			)
	@OneToMany(cascade=javax.persistence.CascadeType.ALL)
	private List<Order> orderList;	
	

	//Start of Member Functions 
	public String getRestName() {
		return restName;
	}
	public long getRestId() {
		return restId;
	}
	public void setRestId(long restId) {
		this.restId = restId;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRestQueueNo() {
		return restQueueNo;
	}
	public void setRestQueueNo(String restQueueNo) {
		this.restQueueNo = restQueueNo;
	}
	public Address getRestAdd() {
		return restAdd;
	}
	public void setRestAdd(Address restAdd) {
		this.restAdd = restAdd;
	}
	public String getRestPhoto() {
		return restPhoto;
	}
	public void setRestPhoto(String restPhoto) {
		this.restPhoto = restPhoto;
	}
	public Set<Product> getRestProdList() {
		return restProdList;
	}
	public void setRestProdList(Set<Product> restProdList) {
		this.restProdList = restProdList;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}


}
