package com.mywicket.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountQueryString;

@MountPath(path = "userSuccessPage")
@MountQueryString
public class UserSuccessPage extends WebPage {
	public UserSuccessPage(final PageParameters parameters) {
		 
		String username = "";
 
		if(parameters.containsKey("username")){
			username = parameters.getString("username");
		}
 
		final Label result = new Label("result", "Username : " + username);
		add(result);
 
	}
}
