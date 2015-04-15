package com.mywicket.data.agile;

import java.io.Serializable;
import java.util.Date;

public class SprintData  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date startDate = new Date();
	private Date endDate;
	private int totalWorkingDays;
	private int hoursPerDay;
	private int numberOfResources;
	private BurnoutChart burnoutChart = new BurnoutChart();
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTotalWorkingDays() {
		return totalWorkingDays;
	}
	public void setTotalWorkingDays(int totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}
	public int getHoursPerDay() {
		return hoursPerDay;
	}
	public void setHoursPerDay(int hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}
	public int getNumberOfResources() {
		return numberOfResources;
	}
	public void setNumberOfResources(int numberOfResources) {
		this.numberOfResources = numberOfResources;
	}
	public BurnoutChart getBurnoutChart() {
		return burnoutChart;
	}
	public void setBurnoutChart(BurnoutChart burnoutChart) {
		this.burnoutChart = burnoutChart;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + hoursPerDay;
		result = prime * result + numberOfResources;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + totalWorkingDays;
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
		SprintData other = (SprintData) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		}
		else if (!endDate.equals(other.endDate))
			return false;
		if (hoursPerDay != other.hoursPerDay)
			return false;
		if (numberOfResources != other.numberOfResources)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		}
		else if (!startDate.equals(other.startDate))
			return false;
		if (totalWorkingDays != other.totalWorkingDays)
			return false;
		return true;
	}
	
	public double getTotalSpringEffort(){
		return totalWorkingDays*hoursPerDay*numberOfResources;
	}
	
	public double getEstimateWorkingHrsPerDay(){
		return hoursPerDay*numberOfResources;
	}
	@Override
	public String toString() {
		return "SprintData [startDate=" + startDate + ", endDate=" + endDate
				+ ", totalWorkingDays=" + totalWorkingDays + ", hoursPerDay="
				+ hoursPerDay + ", numberOfResources=" + numberOfResources
				+ ", burnoutChart=" + burnoutChart.toString() + "]";
	}
	
	
	
}
