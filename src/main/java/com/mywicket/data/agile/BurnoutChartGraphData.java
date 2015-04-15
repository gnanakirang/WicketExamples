package com.mywicket.data.agile;

import java.io.Serializable;

public class BurnoutChartGraphData implements Serializable {
	/**
	 * 
	 */
	public static final String DEFAULT_LINE_GRAPH_COLOURS_ARRAY = "['#CC0000', '#660033']";
	private static final long serialVersionUID = 1L;
	private String xAxisLables = "";
	private String xAxisValues = "";
	private String yAxisValues = "";
	private String axisxstep = "";
	private String colors = DEFAULT_LINE_GRAPH_COLOURS_ARRAY;	
	private String graphLineLabels = "";
	
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
	
	public String getAxisxstep() {
		return axisxstep;
	}
	public void setAxisxstep(String axisxstep) {
		this.axisxstep = axisxstep;
	}
	
	public String getColors() {
		return colors;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	
	public String getGraphLineLabels() {
		return graphLineLabels;
	}
	public void setGraphLineLabels(String graphLineLabels) {
		this.graphLineLabels = graphLineLabels;
	}
	
	/*
	"drawRaphaelLineChart([[234,123,189,203,178,150,210],[0,180,165,201,145,189,160]],"
	+ " [0,1,2,3,4,5,6],"
	+ "'Line graph showing sales graph for last week',"
	+ "['Shirts', 'Trousers'],"
	+ "['#333366', '#006600'],"
	+ "['Mon','Tue','Wed','Thr','Fri','Sat','Sun'], 6);"*/
	
	public String getParametersStringForDrawingRahaelLineChart(){
		StringBuilder params = new StringBuilder();
		params.append(yAxisValues).append(",")
				.append(xAxisValues).append(",")
				.append("' Sprint Burn-out Line Chart '").append(",")
				.append(graphLineLabels).append(",")
				.append(colors).append(",")
				.append(xAxisLables).append(",")
				.append(axisxstep);
		return params.toString();
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
	
	@Override
	public String toString() {
		return "BurnoutChartGraphData [xAxisLables=" + xAxisLables
				+ ", xAxisValues=" + xAxisValues + ", yAxisValues=" + yAxisValues
				+ ", axisxstep=" + axisxstep + ", colors=" + colors
				+ ", graphLineLabels=" + graphLineLabels + "]";
	}	
	
}
