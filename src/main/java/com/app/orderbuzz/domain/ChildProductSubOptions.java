package com.app.orderbuzz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * @author Tajinder Singh 
 * This class will store details of All the SUB OPTIONS , a product can have, or 
 * more precisely a MAIN OPTION can have.
 * 
 *  For Example: 
 *             SIZE -> [SMALL, MEDIUM, LARGE]
 *             TOPINGS -> [ CAPCICUM, ONIONS, TOMATO, MASHROOMS] 
 *             BAKED-> [YES or NO] 
 * Have used Hibernate Annotations to create Table in Database
 */

@Entity
@Table(name="CHILDPROD_SUB_OPTION")
public class ChildProductSubOptions {

	@Id 
	@Column(name="SUB_OPTION_ID_PK")
	@GeneratedValue
	private long childProdSubOptionId;

	@Column(name="SUB_OPTION_NAME")
	private String childProdSubOptionName;

	@Column(name="SUB_OPTION_PRICE")
	private float childProdSubOptionPrice;

	private boolean isChecked;
	
	public long getChildProdSubOptionId() {
		return childProdSubOptionId;
	}
	public void setChildProdSubOptionId(long childProdSubOptionId) {
		this.childProdSubOptionId = childProdSubOptionId;
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
	
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}


}
