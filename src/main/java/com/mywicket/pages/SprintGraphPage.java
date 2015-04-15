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
import com.mywicket.data.agile.BurnoutChartGraphData;
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
		 
		final WebMarkupContainer sprintInputGroup = new WebMarkupContainer("sprintInputGroup");
		sprintInputGroup.setOutputMarkupPlaceholderTag(true);
		add(sprintInputGroup);

		final WebMarkupContainer hoursInputgroup = new WebMarkupContainer(
				"hoursInputGroup");
		hoursInputgroup.setVisible(false);
		hoursInputgroup.setOutputMarkupId(true);
		hoursInputgroup.setOutputMarkupPlaceholderTag(true);
		add(hoursInputgroup);
		
		/*final WebMarkupContainer raphaelBurnoutChart = new WebMarkupContainer(
				"burnoutChart");
		raphaelBurnoutChart.setVisible(true);
		raphaelBurnoutChart.setOutputMarkupPlaceholderTag(true);
		raphaelBurnoutChart.setOutputMarkupId(true).setMarkupId("holder");		
		add(raphaelBurnoutChart);*/

		prepareSprintBasicDialoguePanel(sprintInputGroup, hoursInputgroup);		
		prepareHoursInputPanel(hoursInputgroup, null);
		addHoursLink(sprintInputGroup, hoursInputgroup);
		
	}

	@SuppressWarnings("all")
	private void prepareSprintBasicDialoguePanel(
			final WebMarkupContainer sprintInputGroup,
			final WebMarkupContainer hoursInputgroup) {
		final DateTextField dateTextField = new DateTextField("sprintStartDate",
				new PropertyModel<Date>(getSprintData(), "startDate"), 
				new PatternDateConverter("MM/dd/yyyy", false));
		DatePicker datePicker = new DatePicker();
		datePicker.setShowOnFieldClick(true);
		dateTextField.add(datePicker);
		sprintInputGroup.add(dateTextField);
		dateTextField.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {}});

		final TextField<Integer> workingDays = new TextField<Integer>(
				"totalWorkingDays", new PropertyModel<Integer>(getSprintData(),
						"totalWorkingDays"));
		workingDays.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {}});
		sprintInputGroup.add(workingDays);

		final DateTextField sprintEndDate = new DateTextField("sprintEndDate",
				new PropertyModel<Date>(getSprintData(), "endDate"),
				new PatternDateConverter("MM/dd/yyyy", false));
		
		sprintEndDate.add(new DatePicker());
		sprintEndDate.setOutputMarkupId(true);
		sprintEndDate.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				SprintProcessor.populateTotalWorkingDays(getSprintData());
				target.addComponent(workingDays);
				if (hoursInputgroup.isVisible()) {
					hoursInputgroup.setVisible(false);
				}
				target.addComponent(hoursInputgroup);
			}});
		sprintInputGroup.add(sprintEndDate);

		final DropDownChoice<Integer> hoursPerDay = new DropDownChoice<Integer>(
				"hoursPerDay", new PropertyModel<Integer>(this,
						"sprintData.hoursPerDay"), HRS_PER_DAY);
		hoursPerDay.setOutputMarkupId(true);
		hoursPerDay.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {}	});

		sprintInputGroup.add(hoursPerDay);

		final TextField<Integer> totalResources = new TextField<Integer>(
				"numberOfResources", new PropertyModel<Integer>(getSprintData(),
						"numberOfResources"));
		totalResources.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget arg0) {}});
		sprintInputGroup.add(totalResources);
		
	}

	private void addHoursLink(final WebMarkupContainer sprintInputGroup,
			final WebMarkupContainer hoursInputgroup) {
		sprintInputGroup.add(new AjaxFallbackLink<Object>("enterActualslink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				SprintProcessor.populateEstimates(getSprintData());
				if (!hoursInputgroup.isVisible()) {
					hoursInputgroup.setVisible(true);
				}
				if (target != null) {
					target.addComponent(hoursInputgroup);
				}
				
			}
		});
	}

	@SuppressWarnings("all")
	private void prepareHoursInputPanel(final WebMarkupContainer hoursInputgroup,
			final WebMarkupContainer raphaelBurnoutChart) {
		this.listModel  = loadEffortData();

		PageableListView pageListView = getPageListView();
		pageListView.setReuseItems(false);//If this property is set to 'true' then the pageListView items will not be refreshed
		pageListView.setOutputMarkupId(true);
		hoursInputgroup.add(pageListView);
		hoursInputgroup.add(new PagingNavigator("navigator", pageListView));
		Form formAction = new Form<Void>("actionButtons");
		formAction.setOutputMarkupId(true);
		
		AjaxButton saveButton = new AjaxButton("saveButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				/*if (raphaelBurnoutChart.isVisible()){
					raphaelBurnoutChart.add(new SimpleAttributeModifier("class", "raphaelChartDiv"));
				}
				target.addComponent(raphaelBurnoutChart);*/
				logger.info("Sprint Data: {}",getSprintData());
				BurnoutChartGraphData graphData = SprintProcessor.getBurnoutChartGraphDataUsingRemainingHrs(getSprintData());
				logger.info("Graph Data: {}", graphData);
				logger.info("Javascript call drawRaphaelLineChart({});", graphData.getParametersStringForDrawingRahaelLineChart());
				target.appendJavascript("drawRaphaelLineChart("+graphData.getParametersStringForDrawingRahaelLineChart()+");");				
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
						new PropertyModel<Double>(effort, "remainingEstHrs"));
				estimatedHours.setOutputMarkupId(true);
				estimatedHours.setEnabled(false);//Estimated hours input box is disabled
				/*estimatedHours.add(new OnChangeAjaxBehavior() {
					@Override
					protected void onUpdate(AjaxRequestTarget arg0) {
					}
				});*/
				pageItem.add(estimatedHours);
				
				TextField<Double> actualBurnedHours = new TextField<Double>("actualBurnedHours",
						new PropertyModel<Double>(effort, "remainingActualHrs"));
				actualBurnedHours.setOutputMarkupId(true);
				actualBurnedHours.add(new OnChangeAjaxBehavior() {
					@Override
					protected void onUpdate(AjaxRequestTarget arg0) {
					}
				});
				pageItem.add(actualBurnedHours);
			}

		};
	}

}
