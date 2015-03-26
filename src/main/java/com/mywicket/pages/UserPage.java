package com.mywicket.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountQueryString;

import com.mywicket.validators.UsernameValidator;

@MountPath(path = "userPage")
@MountQueryString
public class UserPage extends WebPage {
	@SuppressWarnings("serial")
	public UserPage(final PageParameters parameters) {
		
		add(new FeedbackPanel("feedback"));
 
		final TextField<String> username = new TextField<String>("username",
				Model.of(""));
		username.setRequired(true);
		username.add(new UsernameValidator());
 
		Form<?> form = new Form<Void>("userForm") {
			
			@Override
			protected void onSubmit() {
 
				final String usernameValue = username.getModelObject();
 
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("username", usernameValue);
				setResponsePage(UserSuccessPage.class, pageParameters);
 
			}
 
		};
		//form.setModel(null);
		add(form);
		form.add(username);
 
	}
}
