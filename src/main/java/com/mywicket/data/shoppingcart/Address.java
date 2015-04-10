package com.mywicket.data.shoppingcart;

import java.io.Serializable;

public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String line1;
	private String line2;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	public Address(String line1, String line2, String street, String city,
			String state, String zipCode) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((line1 == null) ? 0 : line1.hashCode());
		result = prime * result + ((line2 == null) ? 0 : line2.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		}
		else if (!city.equals(other.city))
			return false;
		if (line1 == null) {
			if (other.line1 != null)
				return false;
		}
		else if (!line1.equals(other.line1))
			return false;
		if (line2 == null) {
			if (other.line2 != null)
				return false;
		}
		else if (!line2.equals(other.line2))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		}
		else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		}
		else if (!street.equals(other.street))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		}
		else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	
}
