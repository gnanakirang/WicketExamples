package com.mywicket.processor.agile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mywicket.data.agile.BurnoutChart;
import com.mywicket.data.agile.BurnoutChartGraphData;
import com.mywicket.data.agile.EffortData;
import com.mywicket.data.agile.SprintData;

public class SprintProcessor {
	
	public static void populateEstimates(SprintData sprintData){
		BurnoutChart burnout = sprintData.getBurnoutChart();
		if (burnout == null){
			burnout = new BurnoutChart();
			sprintData.setBurnoutChart(burnout);
		}
		burnout.getEffortDataList().clear();
		Date date = sprintData.getStartDate();
		while (!date.after(sprintData.getEndDate())){
			date = setEffortAndReturnNextDate(sprintData, burnout, date);
		}		
	}

	private static Date setEffortAndReturnNextDate(SprintData sprintData,
			BurnoutChart burnout, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM (EEE)");	
		
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		double estimateWorkHrs = (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) ? 0 : sprintData.getEstimateWorkingHrsPerDay();
		
		EffortData effort = new EffortData(date, sdf.format(date), estimateWorkHrs, estimateWorkHrs);
		burnout.getEffortDataList().add(effort);
		
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}
	
	public static BurnoutChartGraphData getBurnoutChartGraphData(SprintData sprintData){
		BurnoutChartGraphData result = new BurnoutChartGraphData();
		StringBuilder xvalues = new StringBuilder("[");
		StringBuilder xlabels = new StringBuilder("[");
		StringBuilder estSB = new StringBuilder("[");
		StringBuilder actSB = new StringBuilder("[");
		int counter = 0;
		double estTotal = sprintData.getTotalSpringEffort();
		double actTotal = sprintData.getTotalSpringEffort();
		
		for (EffortData effort : sprintData.getBurnoutChart().getEffortDataList()){
			if (effort.getEstimatedHours() == 0){
				actTotal-=effort.getActualBurnedHours();
				continue;
			}
			
			if (counter > 0){
				xvalues.append(", ");		
				xlabels.append(", ");
				estSB.append(", ");		
				actSB.append(", ");
			}
			
			xvalues.append(counter++);
			xlabels.append("'"+effort.getsDate()+"'");			
			
			estTotal-=effort.getEstimatedHours();
			actTotal-=effort.getActualBurnedHours();
			estSB.append(estTotal);
			actSB.append(actTotal);
		}
		
		result.setxAxisValues( xvalues.append("]").toString());		
		result.setyAxisValues("["+estSB.append("], ").append(actSB.append("]]")).toString());		
		result.setxAxisLables(xlabels.append("]").toString());
		
		return result;
	}

}
