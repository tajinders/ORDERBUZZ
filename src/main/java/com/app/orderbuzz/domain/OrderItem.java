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

@Entity 
@Table(name="ORDER_ITEM")
public class OrderItem {
	@Id 
	@Column(name="ORDER_ITEM_PK" ,updatable = false, insertable = false)
	@GeneratedValue
	private long orderItem;

	@Column(name="REST_ID")
	private String restid;
	
	@Column(name="PROD_ID")
	private String prodid;
	
	@Column(name="PROD_NAME")
	private String prodname;
	
	@Column(name="PROD_URL")
	private String produrl;
	
	@Column(name="SUB_PRODID")
	private String subprodid;
	
	@Column(name="SUB_PROD_NAME")
	private String subprodname;
	
	@Column(name="PRICE")
	private float  price;
	
	@JoinTable(
			name = "ORDERITEM_MAINOPTION", 
			joinColumns = @JoinColumn(name = "ORDER_ITEM_ID_FK"), 
			inverseJoinColumns = @JoinColumn(name = "ORDER_ITEM_MAINOPTION_ID_FK")
			)
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=FetchType.EAGER)
	Set<OrderItemMainOptions> cprodMainList; 

	public Set<OrderItemMainOptions> getCprodMainList() {
		return cprodMainList;
	}

	public void setCprodMainList(Set<OrderItemMainOptions> cprodMainList) {
		this.cprodMainList = cprodMainList;
	}

	public String getRestid() {
		return restid;
	}

	public void setRestid(String restid) {
		this.restid = restid;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public String getSubprodid() {
		return subprodid;
	}

	public void setSubprodid(String subprodid) {
		this.subprodid = subprodid;
	}

	public String getSubprodname() {
		return subprodname;
	}

	public void setSubprodname(String subprodname) {
		this.subprodname = subprodname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getProdurl() {
		return produrl;
	}

	public void setProdurl(String produrl) {
		this.produrl = produrl;
	}


}
