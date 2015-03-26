package com.mywicket;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.mywicket.pages.SimplePage;

public class WicketApplication extends WebApplication {	

	@Override
	public Class<SimplePage> getHomePage() {

		return SimplePage.class; // return default page
	}
	
	@Override
	protected void init() {
		
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this));
		new AnnotatedMountScanner().scanPackage("com.mywicket.pages").mount(this);
		 
	}
	
	/**
	 * This method encrypts the user parameters in URL
	 * 
	 * @see org.apache.wicket.protocol.http.WebApplication#newRequestCycleProcessor()
	 */
	@Override	
	protected IRequestCycleProcessor newRequestCycleProcessor() {
 
		return new WebRequestCycleProcessor() {
			protected IRequestCodingStrategy newRequestCodingStrategy() {
				return new CryptedUrlWebRequestCodingStrategy(
					new WebRequestCodingStrategy());
			}
		};
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new MyAppSession(request);
	}

}
