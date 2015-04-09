package com.mywicket.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mywicket.MyAppPage;

public class RahaelLineChartPage extends MyAppPage {
	final static Logger logger = LoggerFactory.getLogger(RahaelLineChartPage.class);
	@SuppressWarnings("all")
	public RahaelLineChartPage(final PageParameters parameters) {
		
		AjaxLink<Object> link = new 		AjaxLink<Object>("link"){
			private static final long serialVersionUID = 1L;			
			@Override
			public void onClick(AjaxRequestTarget target) {
				String y_axisArray = "[[18, 10, 20, 30, 25, 15, 28, 24, 33, 29, 14, 10], [10, 12, 32, 23, 15, 17, 27, 22, 34, 28, 15, 10]]";
				target.appendJavascript("drawRaphaelLineChart(" + y_axisArray + ");");
			}			
		};
		link.setOutputMarkupId(true);
		add(link);		
	}

}
