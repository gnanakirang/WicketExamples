package com.mywicket.processor.agile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mywicket.data.agile.BurnoutChart;
import com.mywicket.data.agile.EffortCoordinate;
import com.mywicket.data.agile.Sprint;

public class SprintProcessor {
	
	public static void populateEstimates(Sprint sprint){
		BurnoutChart burnout = sprint.getBurnoutChart();
		if (burnout == null){
			burnout = new BurnoutChart();
			sprint.setBurnoutChart(burnout);
		}
		burnout.getEffortSet().clear();
		Date date = sprint.getStartDate();
		while (!date.after(sprint.getEndDate())){
			date = setEffortAndReturnNextDate(sprint, burnout, date);
		}
		
		
	}

	private static Date setEffortAndReturnNextDate(Sprint sprint,
			BurnoutChart burnout, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");	
		
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		double estimateWorkHrs = (dayOfWeek == 0 || dayOfWeek == 6) ? 0 : sprint.getEstimateWorkingHrsPerDay();
		
		EffortCoordinate effort = new EffortCoordinate(date, sdf.format(date), estimateWorkHrs, 0);
		burnout.getEffortSet().add(effort);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}
	
	public String[] getBurnoutChartXYvalues(Sprint sprint){
		String[] result = new String[3];
		StringBuilder xvalues = new StringBuilder("[");
		StringBuilder xlabels = new StringBuilder("[");
		StringBuilder estSB = new StringBuilder("[");
		StringBuilder actSB = new StringBuilder("[");
		int counter = 0;
		double estTotal = sprint.getTotalSpringEffort();
		double actTotal = sprint.getTotalSpringEffort();
		
		for (EffortCoordinate effort : sprint.getBurnoutChart().getEffortSet()){
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
		
		result[0] = xvalues.append("]").toString();
		
		result[1] = "["+estSB.append("], ").append(actSB.append("]]")).toString();
		
		result[2] = xlabels.append("]").toString();
		
		return result;
	}

}
