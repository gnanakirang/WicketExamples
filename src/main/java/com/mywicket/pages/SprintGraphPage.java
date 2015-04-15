package com.mywicket.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mywicket.MyAppPage;
import com.mywicket.data.agile.EffortData;
import com.mywicket.processor.agile.SprintProcessor;

public class SprintGraphPage extends MyAppPage {
	
	final static Logger logger = LoggerFactory.getLogger(SprintGraphPage.class);

	private static final List<Integer> HRS_PER_DAY = Arrays.asList(new Integer[] {
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
	
	private IModel<?> listModel = null;

	@SuppressWarnings("all")
	public SprintGraphPage() {
		super();
		 
		final WebMarkupContainer group = new WebMarkupContainer("sprintInputGroup");
		group.setOutputMarkupPlaceholderTag(true);
		add(group);

		Form<?> form = new Form<Void>("sprintForm") {
			public final void onSubmit() {

			}
		};
		group.add(form);

		final DateTextField dateTextField = new DateTextField("sprintStartDate",
				new PropertyModel<Date>(getSprintData(), "startDate"), // new
																																// StyleDateConverter("S-",
																																// true));
				new PatternDateConverter("MM/dd/yyyy", false));
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		dateTextField.add(datePicker);
		form.add(dateTextField);
		dateTextField.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {
			}
		});

		final DateTextField sprintEndDate = new DateTextField("sprintEndDate",
				new PropertyModel<Date>(getSprintData(), "endDate"),// new
																														// StyleDateConverter("S-",
																														// true));
				new PatternDateConverter("MM/dd/yyyy", false));
		sprintEndDate.add(new DatePicker());
		sprintEndDate.setOutputMarkupId(true);
		sprintEndDate.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {
			}
		});
		form.add(sprintEndDate);

		final TextField<Integer> workingDays = new TextField<Integer>(
				"totalWorkingDays", new PropertyModel<Integer>(getSprintData(),
						"totalWorkingDays"));
		workingDays.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {
				// TODO Auto-generated method stub

			}
		});
		form.add(workingDays);

		final DropDownChoice<Integer> hoursPerDay = new DropDownChoice<Integer>(
				"hoursPerDay", new PropertyModel<Integer>(this,
						"sprintData.hoursPerDay"), HRS_PER_DAY);
		hoursPerDay.setOutputMarkupId(true);
		hoursPerDay.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {
				// TODO Auto-generated method stub

			}
		});

		form.add(hoursPerDay);

		final TextField<Integer> totalResources = new TextField<Integer>(
				"numberOfResources", new PropertyModel<Integer>(getSprintData(),
						"numberOfResources"));
		totalResources.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {
				// TODO Auto-generated method stub

			}
		});
		form.add(totalResources);

		final WebMarkupContainer hoursInputgroup = new WebMarkupContainer(
				"hoursInputGroup");
		hoursInputgroup.setVisible(false);
		hoursInputgroup.setOutputMarkupPlaceholderTag(true);
		add(hoursInputgroup);
		/*
		 * final Label label = new Label("enteredStartDate", new Model() {
		 * 
		 * @Override public String getObject() { return
		 * getSprintData().getStartDate().toString(); } });
		 * 
		 * label.setOutputMarkupId(true); hoursInputgroup.add(label);
		 */

		form.add(new AjaxFallbackLink<Object>("enterActualslink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if (!hoursInputgroup.isVisible()) {
					hoursInputgroup.setVisible(true);
				}
				if (target != null) {
					target.addComponent(hoursInputgroup);
				}
				SprintProcessor.populateEstimates(getSprintData());
			}
		});
		
		this.listModel  = loadEffortData();

		PageableListView pageListView = getPageListView();
		pageListView.setReuseItems(true);
		pageListView.setOutputMarkupId(true);
		hoursInputgroup.add(pageListView);
		hoursInputgroup.add(new PagingNavigator("navigator", pageListView));
		Form formAction = new Form<Void>("actionButtons");
		formAction.setOutputMarkupId(true);
		
		AjaxButton saveButton = new AjaxButton("saveButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				target.addComponent(hoursInputgroup);
				logger.info("Sprint Data: {}",getSprintData());
			}
		};
		saveButton.setOutputMarkupId(true);
		formAction.add(saveButton);
		hoursInputgroup.add(formAction);
		
	}
	
	@SuppressWarnings("all")
	private LoadableDetachableModel<Object> loadEffortData() {
		return new LoadableDetachableModel<Object>() {
      protected Object load() {
      	if ( getSprintData() != null 
      			&&  getSprintData().getBurnoutChart() != null 
      			&& getSprintData().getBurnoutChart().getEffortDataList() != null){
    			return getSprintData().getBurnoutChart().getEffortDataList();
    		}else {
    			return new ArrayList<EffortData>();
    		}
      }
		};
	}

	/**
	 * This method uses PageableListView to implement pagination with specified
	 * rows for page here we are passing 2 in the constructor argument
	 * 
	 * @return PageableListView
	 */
	@SuppressWarnings("all")
	public PageableListView<EffortData> getPageListView() {
		return new PageableListView<EffortData>("effortData", (IModel<? extends List<? extends EffortData>>) this.listModel, 7) { //getEffortDataList()

			@Override
			protected void populateItem(final ListItem pageItem) {
				EffortData effort = (EffortData)pageItem.getModelObject();
				pageItem.add(new Label("sDate", effort.getsDate()));
				TextField<Double> estimatedHours = new TextField<Double>("estimatedHours",
						new PropertyModel<Double>(effort, "estimatedHours"));
				estimatedHours.setOutputMarkupId(true);
				estimatedHours.add(new OnChangeAjaxBehavior() {
					@Override
					protected void onUpdate(AjaxRequestTarget arg0) {
					}
				});
				pageItem.add(estimatedHours);
				
				TextField<Double> actualBurnedHours = new TextField<Double>("actualBurnedHours",
						new PropertyModel<Double>(effort, "actualBurnedHours"));
				actualBurnedHours.setOutputMarkupId(true);
				actualBurnedHours.add(new OnChangeAjaxBehavior() {
					@Override
					protected void onUpdate(AjaxRequestTarget arg0) {
					}
				});
				pageItem.add(actualBurnedHours);

				/*
				 * Item item = pageItem.getModelObject(); //pageItem.add(new
				 * Label("name", item.getName())); //pageItem.add(new
				 * Label("description", item.getDescription())); //pageItem.add(new
				 * Label("price", "Rs." + item.getPrice()));
				 * 
				 * pageItem.add(new Link("addToBasket", pageItem.getModel()) {
				 * 
				 * @Override public void onClick() { Item selected = (Item)
				 * getModelObject(); getShoppingBasket().addSelectedItem(selected); }
				 * 
				 * });
				 */
			}

		};
	}

}
