package com.mywicket.data.shoppingcart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Customer cust;
	private List<Item> itemsList = new ArrayList<Item>();
	private boolean sameAsCustomerAddress;
	private Address shippingAddress;
	private OrderStatus status;
	private double totalAmount;
	public Order() {
		// Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public List<Item> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}
	public boolean isSameAsCustomerAddress() {
		return sameAsCustomerAddress;
	}
	public void setSameAsCustomerAddress(boolean sameAsCustomerAddress) {
		this.sameAsCustomerAddress = sameAsCustomerAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cust == null) ? 0 : cust.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((itemsList == null) ? 0 : itemsList.hashCode());
		result = prime * result + (sameAsCustomerAddress ? 1231 : 1237);
		result = prime * result
				+ ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Order other = (Order) obj;
		if (cust == null) {
			if (other.cust != null)
				return false;
		}
		else if (!cust.equals(other.cust))
			return false;
		if (id != other.id)
			return false;
		if (itemsList == null) {
			if (other.itemsList != null)
				return false;
		}
		else if (!itemsList.equals(other.itemsList))
			return false;
		if (sameAsCustomerAddress != other.sameAsCustomerAddress)
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		}
		else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(totalAmount) != Double
				.doubleToLongBits(other.totalAmount))
			return false;
		return true;
	}
	
	
	
	
}
