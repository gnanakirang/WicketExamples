package com.mywicket.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mywicket.services.HelloService;

public class SimplePage extends WebPage {

	@SpringBean
	private HelloService helloService;
	final static Logger logger = LoggerFactory.getLogger(SimplePage.class);
	int counter = 0;
	public SimplePage(final PageParameters parameters) {
		
		logger.info("In SimplePage");
		add(new Label("msg", helloService.getHelloWorldMsg()));
		add(new Label("counter",new PropertyModel<String>(this, "counter")));
		
		add(new Link<Object>("link"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				++counter;				
			}
			
		});
		
		add(new Link<Object>("linkUserPage"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(UserPage.class);				
			}
			
		});
		
		add(new Link<Object>("linkEchoPage"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(EchoPage.class);				
			}
			
		});
		
		add(new Link<Object>("linkItemListPage"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(ItemListPage.class);				
			}
			
		});
		
		add(new Link<Object>("gRahaelLineChartPage"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(RahaelLineChartPage.class);				
			}
			
		});

	}

}
