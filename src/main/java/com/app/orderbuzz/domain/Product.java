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
 * Product Class will store Details of Product (example of products are DONUT, JUICE, DONUT etc)
 * Each product has a List of Child Products 
 *                         For Example BURGER ->[ CHICEKEN BURGER, or VEG BURGER etc ]
 * 
 * 
 * Hibernate will make table with name PRODUCT for this class with following properties
 * @Entity- This annotation will create a table for this class
 * @Table - This annotation will change default table name 
 * @Id - This annotations is used to make Primary Key
 * @column - This annotation is used to change default column name 
 * @Generated Value - This annotation is used to make field Auto Increment 
 * @OneToOne is used to perform 1-1 mapping 
 * @OneToMany mapping will generate a New Mapping table
 * Cascade keyword  is used  to perform cascade operations
 */

@Entity 
@Table(name="PRODUCT")
public class Product {

	@Id
	@Column(name="PROD_ID_PK")
	@GeneratedValue
	private long prodId;

	@Column(name="PROD_NAME")
	private String prodname;


	@Column(name="PROD_PHOTO")
	private String prodPhoto;


	@JoinTable(
			name = "PRODUCT_CHILDPRODUCT", 
			joinColumns = @JoinColumn(name = "PROD_ID_FK"), 
			inverseJoinColumns = @JoinColumn(name = "CHILDPROD_ID_FK")
			)
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ChildProduct> childProdList;

	//Start of Member Functions 

	public Set<ChildProduct> getChildProdList() {
		return childProdList;
	}
	public void setChildProdList(Set<ChildProduct> childProdList) {
		this.childProdList = childProdList;
	}
	public String getProdname() {
		return prodname;
	}
	public long getProdId() {
		return prodId;
	}
	public void setProdId(long prodId) {
		this.prodId = prodId;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getProdPhoto() {
		return prodPhoto;
	}
	public void setProdPhoto(String prodPhoto) {
		this.prodPhoto = prodPhoto;
	}


}
