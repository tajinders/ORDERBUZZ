package com.app.orderbuzz.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity 
@Table(name="ORDER_DETAILS")
public class Order {

	@Id 
	@Column(name="ORDER_ID_PK")
	@GeneratedValue
	private long orderId;
	
	@Transient
	private String stripetokenno ;

	
//	//@Lob
//	@Column(name="ORDER_SUMMARY")
//	private String orderSummary;

	@JoinTable(
			name = "ORDERDETAILS_ORDERITEM", 
			joinColumns = @JoinColumn(name = "ORDER_ID_FK"), 
			inverseJoinColumns = @JoinColumn(name = "ORDER_ITEM_ID_FK")
			)
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<OrderItem> orderItem ;

	
	@Column(name="ORDER_KEY")
	private String orderKey;
	
	@Column(name="ORDER_GCMKEY")
	private String orderGcmKey;
	
	@Column(name="ORDER_STATUS")
	private String orderStatus;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Transient
	String restId ;

	@Transient
	String totalPrice;

	public long getOrderSequenceNo() {
		return orderSequenceNo;
	}

	public void setOrderSequenceNo(long orderSequenceNo) {
		this.orderSequenceNo = orderSequenceNo;
	}

	@Column(name="ORDER_SEQ_NO")
	private long orderSequenceNo;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


	public String getOrderKey() {
		return orderKey;
	}

	public String getStripetokenno() {
		return stripetokenno;
	}

	public void setStripetokenno(String stripetokenno) {
		this.stripetokenno = stripetokenno;
	}

	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	public String getOrderGcmKey() {
		return orderGcmKey;
	}

	public void setOrderGcmKey(String orderGcmKey) {
		this.orderGcmKey = orderGcmKey;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
			
}
