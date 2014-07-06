package com.app.orderbuzz.dto;

import java.util.ArrayList;

public class OrderDto {
	String stripetokenno ;
	String restId ;
	String secretKey ; 
	String gcmKey ;
	ArrayList<CartDto> mycart;
	String totalPrice;
	
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTokenno() {
		return stripetokenno;
	}
	public void setTokenno(String tokenno) {
		this.stripetokenno = tokenno;
	}
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getGcmKey() {
		return gcmKey;
	}
	public void setGcmKey(String gcmKey) {
		this.gcmKey = gcmKey;
	}
//	public String getOrdersummary() {
//		return orderSummary;
//	}
//	public void setOrdersummary(String ordersummary) {
//		this.orderSummary = ordersummary;
//	}
	public ArrayList<CartDto> getMycart() {
		return mycart;
	}
	public void setMycart(ArrayList<CartDto> mycart) {
		this.mycart = mycart;
	}
}
