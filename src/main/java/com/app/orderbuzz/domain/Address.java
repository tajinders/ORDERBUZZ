package com.app.orderbuzz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author tajindsi
 * This class will store Resturant/Food Outlet exact Location
 * We are storing Latitude , Longitude , and additional Information.
 * 
 * @Entity- This annotation will create a table for this class
 * @Table - This annotation will change default table name 
 * @Id - This annotations is used to make Primary Key
 * @column - This annotation is used to change default column name 
 * @Generated Value - This annotation is used to make field Auto Increment 
 */
@Entity
@Table(name="ADDRESS")
public class Address {

	@Id 
	@Column(name="GEOFENCE_ID_PK")
	@GeneratedValue
	private long geofenceId;

	@Column(name="LATITUDE")
	private double latitude;

	@Column(name="LONGITUDE")
	double longitude;
	
	@Column(name="ADD_DESC")
	private String adddesc;

	//Start of Member Functions 
	public String getAdddesc() {
		return adddesc;
	}
	public void setAdddesc(String adddesc) {
		this.adddesc = adddesc;
	}

	public long getGeofenceId() {
		return geofenceId;
	}
	public void setGeofenceId(long geofenceId) {
		this.geofenceId = geofenceId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
