package com.mywicket.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mywicket.MyAppPage;

public class RahaelLineChartPage extends MyAppPage {
	final static Logger logger = LoggerFactory.getLogger(RahaelLineChartPage.class);
	int counter = 0;
	@SuppressWarnings("all")
	public RahaelLineChartPage(final PageParameters parameters) {
		
		AjaxLink<Object> link = new 		AjaxLink<Object>("link"){
			private static final long serialVersionUID = 1L;			
			@Override
			public void onClick(AjaxRequestTarget target) {
				String a = "[18, 10, 20, 30, 25, 15, 28, 24, 33, 29, 14, 10]";
				String b = "[10, 12, 32, 23, 15, 17, 27, 22, 34, 28, 15, 20]";
				if (counter % 2 == 0){
					target.appendJavascript("drawRaphaelLineChart([" + a+" , "+b + "]);");
				}else {
					//drawRaphaelLineChart(y_values, x_values, graphTitle, lineNames, colorsarray, x_labels, axisxstep)
					target.appendJavascript("drawRaphaelLineChart([[234,123,189,203,178,150,210],[0,180,165,201,145,189,160]],"
							+ " [0,1,2,3,4,5,6],"
							+ "'Line graph showing sales graph for last week',"
							+ "['Shirts', 'Trousers'],"
							+ "['#333366', '#006600'],"
							+ "['Mon','Tue','Wed','Thr','Fri','Sat','Sun'], 6);");
				}
				
				logger.info("Counter: {}",counter++);
				
			}			
		};
		link.setOutputMarkupId(true);
		add(link);		
	}

}
