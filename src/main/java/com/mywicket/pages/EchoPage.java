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
	public TextField<String> field;
	
	public EchoPage() {
		Form<Object> form = new Form<Object>("echoForm");
		field = new TextField<String>("field", new Model<String>(""));
		form.add(field);
		form.add(new Button("button"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(){
				String value = (String)field.getModelObject();
				label.setDefaultModelObject(value);
				field.setModelObject("");
			}
		});
		add(form);
		add(label = new Label("message", new Model<String>("")));
	}
}
