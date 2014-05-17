package com.app.orderbuzz.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 *  @author Tajinder Singh
 *  This class will store details of child/sub products. Our main product are BURGER, DONUT , JUICES etc
 *  Our child products will be BURGER -> CHICEKEN BURGER, or VEG BURGER etc 
 *                             DONUT -> MINT DONUT or CHOC DONUT etc
 *                             JUICES -> ORANGE or APPPLE or GRAPES etc. 
 *                             
 *  Each child product will store List of MAIN OPTIONS as well. 
 *                            BURGER-> CHICKEN BURGER -> [SIZE or TOPINGS or TOASTED  etc]
 *                            
 *  Have used Hibernate Annotations to create Table in Database
 *
 */

@Entity
@Table(name="CHILDPRODUCT")
public class ChildProduct {

	@Id 
	@Column(name="CHILDPROD_ID_PK")
	@GeneratedValue
	private long childProdId;

	@Column(name="CHILDPROD_NAME")
	private String childProdName;

	@Column(name="CHILDPROD_BASEPRICE")
	private float childProdBasePrice;


	@JoinTable(
			name = "CHILDPRODUCT_MAINOPTIONS", 
			joinColumns = @JoinColumn(name = "CHILDPROD_ID_FK"), 
			inverseJoinColumns = @JoinColumn(name = "MAIN_OPTION_ID_FK")
			)
	@OneToMany(cascade=javax.persistence.CascadeType.ALL)
	private List<ChildProductMainOptions> childProductMainOptionsList;


	public long getChildProdId() {
		return childProdId;
	}
	public void setChildProdId(long childProdId) {
		this.childProdId = childProdId;
	}
	public String getChildProdName() {
		return childProdName;
	}
	public void setChildProdName(String childProdName) {
		this.childProdName = childProdName;
	}

	public float getChildProdBasePrice() {
		return childProdBasePrice;
	}
	public void setChildProdBasePrice(float childProdBasePrice) {
		this.childProdBasePrice = childProdBasePrice;
	}
	public List<ChildProductMainOptions> getChildProductMainOptionsList() {
		return childProductMainOptionsList;
	}
	public void setChildProductMainOptionsList(
			List<ChildProductMainOptions> childProductMainOptionsList) {
		this.childProductMainOptionsList = childProductMainOptionsList;
	}

}
