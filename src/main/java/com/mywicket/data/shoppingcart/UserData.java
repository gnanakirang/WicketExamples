package com.mywicket.data.shoppingcart;

import java.io.Serializable;
import java.util.Date;

public class UserData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;
	private String userName;
	private String password;
	private Date dob;
	private Customer customer;
	public UserData(long userId, String userName, String password, Date dob,
			Customer customer) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.dob = dob;
		this.customer = customer;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserData other = (UserData) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		}
		else if (!customer.equals(other.customer))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		}
		else if (!dob.equals(other.dob))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		}
		else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
}
