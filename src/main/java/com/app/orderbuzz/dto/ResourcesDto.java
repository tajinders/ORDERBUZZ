package com.app.orderbuzz.dto;

import java.math.BigInteger;

public class ResourcesDto {
	private BigInteger  rest_id_pk;
	private String rest_photo;
	public BigInteger getRest_id_pk() {
		return rest_id_pk;
	}
	public void setRest_id_pk(BigInteger rest_id_pk) {
		this.rest_id_pk = rest_id_pk;
		
	}
	
	// url
	public String getRest_photo() {
		return rest_photo;
	}
	public void setRest_photo(String rest_photo) {
		this.rest_photo = rest_photo;
	}
	
	
	
}
