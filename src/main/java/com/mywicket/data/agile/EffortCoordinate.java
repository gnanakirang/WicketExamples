package com.mywicket.data.agile;

import java.io.Serializable;
import java.util.Date;

public class EffortCoordinate implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String sDate;
	private double estimatedHours;
	private double actualBurnedHours;	
	
	public EffortCoordinate(){}
	
	public EffortCoordinate(Date date, String sDate, double estimatedHours,
			double actualBurnedHours) {
		super();
		this.date = date;
		this.sDate = sDate;
		this.estimatedHours = estimatedHours;
		this.actualBurnedHours = actualBurnedHours;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public double getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	public double getActualBurnedHours() {
		return actualBurnedHours;
	}
	public void setActualBurnedHours(double actualBurnedHours) {
		this.actualBurnedHours = actualBurnedHours;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		EffortCoordinate other = (EffortCoordinate) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	
}
