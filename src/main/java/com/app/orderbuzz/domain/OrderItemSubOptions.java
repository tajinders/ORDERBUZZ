package com.app.orderbuzz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ORDER_ITEM_SUB_OPTION")
public class OrderItemSubOptions {

	@Id 
	@Column(name="ORDER_SUB_OPTION_ID_PK")
	@GeneratedValue
	
	private long childProdSubOptionId;


	public long getChildProdSubOptionId() {
		return childProdSubOptionId;
	}
	public void setChildProdSubOptionId(long childProdSubOptionId) {
		this.childProdSubOptionId = childProdSubOptionId;
	}
	@Column(name="ORDER_SUB_OPTION_NAME")
	private String childProdSubOptionName;

	@Column(name="SUB_OPTION_PRICE")
	private float childProdSubOptionPrice;

	private boolean isChecked;
	
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getChildProdSubOptionName() {
		return childProdSubOptionName;
	}
	public void setChildProdSubOptionName(String childProdSubOptionName) {
		this.childProdSubOptionName = childProdSubOptionName;
	}
	public float getChildProdSubOptionPrice() {
		return childProdSubOptionPrice;
	}
	public void setChildProdSubOptionPrice(float childProdSubOptionPrice) {
		this.childProdSubOptionPrice = childProdSubOptionPrice;
	}

}
