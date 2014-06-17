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
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Tajinder Singh
 * This class will store details of MAIN options which a Sub Product can have
 * Example  CHICKENBURGER -> [SIZE , TOPINGS, BAKED]
 *          VEGBURGER -> [SIZE , TOPINGS, BAKED]
 *			FRENCH VANILA -> [SIZE , CREAM, etc ]
 *
 * Each Main options will store a List of SubOptions
 *  For Example We can have Multi Selection   SIZE -> [SMALL, MEDIUM, LARGE]
 *                        TOPINGS -> [ CAPCICUM, ONIONS, TOMATO, MASHROOMS] 
 *  We can have single selection Suboptins as well  BAKED-> [YES or NO] 
 *                   
 *  Have used Hibernate Annotations to create Table in Database
 */

@Entity
@Table(name="CHILDPROD_MAIN_OPTION")
public class ChildProductMainOptions {

	@Id 
	@Column(name="MAIN_OPTION_ID_PK")
	@GeneratedValue
	private long chileProdMainOptionId;

	@Column(name="MAIN_OPTION_NAME")
	private String childProdMainOptionName;

	private boolean singleSelection;

	@JoinTable(
			name = "CHILDPROD_MAINOPTION_SUBOPTION", 
			joinColumns = @JoinColumn(name = "MAIN_OPTION_ID_FK"), 
			inverseJoinColumns = @JoinColumn(name = "SUB_OPTION_ID_FK")
			)
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ChildProductSubOptions> childProductSubOptionsList ;



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

	public Set<ChildProductSubOptions> getChildProductSubOptionsList() {
		return childProductSubOptionsList;
	}
	public void setChildProductSubOptionsList(
			Set<ChildProductSubOptions> childProductSubOptionsList) {
		this.childProductSubOptionsList = childProductSubOptionsList;
	}
	public boolean isSingleSelection() {
		return singleSelection;
	}
	public void setSingleSelection(boolean singleSelection) {
		this.singleSelection = singleSelection;
	}


}
