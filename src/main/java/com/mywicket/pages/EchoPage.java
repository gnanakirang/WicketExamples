package com.mywicket.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountQueryString;

@MountPath(path = "echoPage")
@MountQueryString
public class EchoPage extends WebPage {

	public Label label;
	public TextField field;
	
	public EchoPage() {
		Form form = new Form("echoForm");
		field = new TextField("field", new Model(""));
		form.add(field);
		form.add(new Button("button"){
			@Override
			public void onSubmit(){
				String value = (String)field.getModelObject();
				label.setDefaultModelObject(value);
				field.setModelObject("");
			}
		});
		add(form);
		add(label = new Label("message", new Model("")));
	}
}
