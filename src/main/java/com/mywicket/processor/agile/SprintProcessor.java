package com.mywicket.processor.agile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mywicket.data.agile.BurnoutChart;
import com.mywicket.data.agile.BurnoutChartGraphData;
import com.mywicket.data.agile.EffortData;
import com.mywicket.data.agile.SprintData;

public class SprintProcessor {
	final static Logger logger = LoggerFactory.getLogger(SprintProcessor.class);
	public static void populateEstimates(SprintData sprintData){
		BurnoutChart burnout = new BurnoutChart();
		sprintData.setBurnoutChart(burnout);
		burnout.getEffortDataList().clear();
		Date date = sprintData.getStartDate();
		while (!date.after(sprintData.getEndDate())){
			date = setEffortAndReturnNextDate(sprintData, burnout, date);
		}
		
		
		double remainingTotalHrs = sprintData.getTotalSpringEffort();
		for (EffortData effort : sprintData.getBurnoutChart().getEffortDataList()){
			remainingTotalHrs = remainingTotalHrs - effort.getEstimatedHours();
			effort.setRemainingActualHrs(remainingTotalHrs);
			effort.setRemainingEstHrs(remainingTotalHrs);
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
	
	public static BurnoutChartGraphData getBurnoutChartGraphDataByAggregation(SprintData sprintData){
		BurnoutChartGraphData result = new BurnoutChartGraphData();
		StringBuilder xvalues = new StringBuilder("[");
		StringBuilder xlabels = new StringBuilder("[");
		StringBuilder estSB = new StringBuilder("[");
		StringBuilder actSB = new StringBuilder("[");
		int counter = 0;
		double estTotal = sprintData.getTotalSpringEffort();
		double actTotal = sprintData.getTotalSpringEffort();
		
		estSB.append(estTotal);
		actSB.append(actTotal);
		xvalues.append(counter++);
		xlabels.append("'0'");
		
		for (EffortData effort : sprintData.getBurnoutChart().getEffortDataList()){
			if (effort.getEstimatedHours() == 0){
				actTotal-=effort.getActualBurnedHours();
				continue;
			}			

			xvalues.append(", ");		
			xlabels.append(", ");
			estSB.append(", ");		
			actSB.append(", ");
			
			xvalues.append(counter++);
			xlabels.append("'"+effort.getsDate().substring(0, 5)+"'");			
			
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
	
	public static BurnoutChartGraphData getBurnoutChartGraphDataUsingRemainingHrs(SprintData sprintData){
		BurnoutChartGraphData result = new BurnoutChartGraphData();
		StringBuilder xvalues = new StringBuilder("[");
		StringBuilder xlabels = new StringBuilder("[");
		StringBuilder estSB = new StringBuilder("[");
		StringBuilder actSB = new StringBuilder("[");
		int counter = 0;
		
		estSB.append(sprintData.getTotalSpringEffort());
		actSB.append(sprintData.getTotalSpringEffort());
		xvalues.append(counter++);
		xlabels.append("'0'");
		
		for (EffortData effort : sprintData.getBurnoutChart().getEffortDataList()){
			if (effort.getEstimatedHours() == 0){
				continue;
			}			

			xvalues.append(", ");		
			xlabels.append(", ");
			estSB.append(", ");		
			actSB.append(", ");
			
			xvalues.append(counter++);
			xlabels.append("'"+effort.getsDate().substring(0, 5)+"'");			
			
			estSB.append(effort.getRemainingEstHrs());
			actSB.append(effort.getRemainingActualHrs());
		}
		
		result.setxAxisValues( xvalues.append("]").toString());		
		result.setyAxisValues("["+estSB.append("], ").append(actSB.append("]]")).toString());		
		result.setGraphLineLabels("['Estimated Hrs', 'Actual Hrs']");
		result.setxAxisLables(xlabels.append("]").toString());
		result.setAxisxstep(counter-1+"");
		return result;
	}
	
	public static void populateTotalWorkingDays(SprintData sprintData){
		if (sprintData.getStartDate() != null && sprintData.getEndDate() != null){
			long diff = sprintData.getEndDate().getTime() - sprintData.getStartDate().getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			logger.info("Days difference is {}",diffDays);
			int totalWorkingDays = (int)++diffDays;
			if (diffDays == 6){
				totalWorkingDays = 5;				
			}else if (diffDays > 6){
				totalWorkingDays = (int)(diffDays - (Math.floor(diffDays/7)*2));
			}
			sprintData.setTotalWorkingDays(totalWorkingDays);
		}
	}

}
