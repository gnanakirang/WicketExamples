package com.mywicket.data.agile;

import java.io.Serializable;

public class BurnoutChartGraphData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String xAxisLables = "";
	private String xAxisValues = "";
	private String yAxisValues = "";
	public String getxAxisLables() {
		return xAxisLables;
	}
	public void setxAxisLables(String xAxisLables) {
		this.xAxisLables = xAxisLables;
	}
	public String getxAxisValues() {
		return xAxisValues;
	}
	public void setxAxisValues(String xAxisValues) {
		this.xAxisValues = xAxisValues;
	}
	public String getyAxisValues() {
		return yAxisValues;
	}
	public void setyAxisValues(String yAxisValues) {
		this.yAxisValues = yAxisValues;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((xAxisLables == null) ? 0 : xAxisLables.hashCode());
		result = prime * result
				+ ((xAxisValues == null) ? 0 : xAxisValues.hashCode());
		result = prime * result
				+ ((yAxisValues == null) ? 0 : yAxisValues.hashCode());
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
		BurnoutChartGraphData other = (BurnoutChartGraphData) obj;
		if (xAxisLables == null) {
			if (other.xAxisLables != null)
				return false;
		}
		else if (!xAxisLables.equals(other.xAxisLables))
			return false;
		if (xAxisValues == null) {
			if (other.xAxisValues != null)
				return false;
		}
		else if (!xAxisValues.equals(other.xAxisValues))
			return false;
		if (yAxisValues == null) {
			if (other.yAxisValues != null)
				return false;
		}
		else if (!yAxisValues.equals(other.yAxisValues))
			return false;
		return true;
	}
	
}
