package com.app.orderbuzz.dto;

public class CartDto {

	private String restid;
	private String restadd;
	private String restname;
	private String prodid;
	private String subprodid;
	private String subprodname;
	private float  price;
	private String prodname;
	private String produrl;
	private String ordersummary;


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

	public String getOrdersummary() {
		return ordersummary;
	}

	public void setOrdersummary(String ordersummary) {
		this.ordersummary = ordersummary;
	}



	public String getRestadd() {
		return restadd;
	}

	public void setRestadd(String restadd) {
		this.restadd = restadd;
	}

	public String getRestname() {
		return restname;
	}

	public void setRestname(String restname) {
		this.restname = restname;
	}

}
