package com.app.orderbuzz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity 
@Table(name="ORDER_DETAILS")
public class Order {

	@Id 
	@Column(name="ORDER_ID_PK")
	@GeneratedValue
	private long orderId;
	
	//@Lob
	@Column(name="ORDER_SUMMARY")
	private String orderSummary;
		
	@Column(name="ORDER_KEY")
	private String orderKey;
	
	@Column(name="ORDER_GCMKEY")
	private String orderGcmKey;
	
	@Column(name="ORDER_STATUS")
	private String orderStatus;
	
	@Column(name="USERNAME")
	private String userName;
	

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

	public String getOrderSummary() {
		return orderSummary;
	}

	public void setOrderSummary(String orderSummary) {
		this.orderSummary = orderSummary;
	}

	public String getOrderKey() {
		return orderKey;
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
