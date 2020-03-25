package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="addressSeqGen")
	@SequenceGenerator(name="addressSeqGen", sequenceName = "addressSequence", initialValue = 1, allocationSize = 1)
	protected Long addressID;
	protected StringBuffer state;
	protected StringBuffer city;
	protected StringBuffer Street;
	protected int pincode;
	protected StringBuffer houseNum;
	public Long getAddressID() {
		return addressID;
	}
	public void setAddressID(Long addressID) {
		this.addressID = addressID;
	}
	public StringBuffer getState() {
		return state;
	}
	public void setState(StringBuffer state) {
		this.state = state;
	}
	public StringBuffer getCity() {
		return city;
	}
	public void setCity(StringBuffer city) {
		this.city = city;
	}
	public StringBuffer getStreet() {
		return Street;
	}
	public void setStreet(StringBuffer street) {
		Street = street;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public StringBuffer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(StringBuffer houseNum) {
		this.houseNum = houseNum;
	}
	
}
