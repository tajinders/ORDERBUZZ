package com.app.orderbuzz.domain;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_ITEM_MAIN_OPTION")
public class OrderItemMainOptions {

	@Id 
	@Column(name="ORDER_MAIN_OPTION_ID_PK")
	@GeneratedValue
	private long chileProdMainOptionId;


	@Column(name="MAIN_OPTION_NAME")
	private String childProdMainOptionName;

	private boolean singleSelection;

	@JoinTable(
			name = "ORDER_ITEM_MAINOPTION_SUBOPTION", 
			joinColumns = @JoinColumn(name = "ORDER_MAIN_OPTION_ID_FK"), 
			inverseJoinColumns = @JoinColumn(name = "ORDER_SUB_OPTION_ID_FK")
			)
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<OrderItemSubOptions> childProductSubOptionsList ;

	public boolean isSingleSelection() {
		return singleSelection;
	}
	public void setSingleSelection(boolean singleSelection) {
		this.singleSelection = singleSelection;
	}

	public long getChileProdMainOptionId() {
		return chileProdMainOptionId;
	}
	public void setChileProdMainOptionId(long chileProdMainOptionId) {
		this.chileProdMainOptionId = chileProdMainOptionId;
	}
	public String getChildProdMainOptionName() {
		return childProdMainOptionName;
	}
	public void setChildProdMainOptionName(String childProdMainOptionName) {
		this.childProdMainOptionName = childProdMainOptionName;
	}
	public Set<OrderItemSubOptions> getChildProductSubOptionsList() {
		return childProductSubOptionsList;
	}
	public void setChildProductSubOptionsList(
			Set<OrderItemSubOptions> childProductSubOptionsList) {
		this.childProductSubOptionsList = childProductSubOptionsList;
	}

}
