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
					target.appendJavascript("drawRaphaelLineChart([" + b+" , "+a + "]);");
				}
				
				logger.info("Counter: {}",counter++);
				
			}			
		};
		link.setOutputMarkupId(true);
		add(link);		
	}

}
