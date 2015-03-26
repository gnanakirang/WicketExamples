package com.mywicket.pages;

import com.mywicket.MyAppPage;

public class CheckOut extends MyAppPage {
	public CheckOut() {
/*
		ListView<BLView> tmpViews = new ListView<BLView>(ID.VIEWS, this
				.getPortfolio().getViews()) {

			@Override
			public void populateItem(ListItem<BLView> aViewsListItem) {

				final BLView tmpView = aViewsListItem.getModelObject();

				final ContextLabel<Number> tmpTotalWeightLabel = new ContextLabel<Number>(
						ID.TOTAL_WEIGHTS, new PropertyModel<Number>(tmpView,
								ID.TOTAL_WEIGHTS), StandardType.PERCENT);
				tmpTotalWeightLabel.setOutputMarkupId(true);

				aViewsListItem.add(new TextField<String>(ID.NAME,
						new PropertyModel<String>(tmpView, ID.NAME)));
				aViewsListItem
						.add(new DeleteView(ID.DELETE, new Model<BLView>(tmpView)));
				LoadableDetachableModel<Number> tmpMarketReturnModel = new LoadableDetachableModel<Number>() {

					@Override
					protected BigDecimal load() {
						return myPage.getBlackLittermanModel().calculatePortfolioReturn(
								tmpView.getWeightList());
					}
				};
				aViewsListItem.add(new ContextLabel<Number>(ID.PORTFOLIO_RETURN,
						tmpMarketReturnModel, StandardType.PERCENT));
				aViewsListItem
						.add(new ContextTextField<Number>(ID.RETURN,
								new PropertyModel<Number>(tmpView, ID.RETURN),
								StandardType.PERCENT));

				ListView<ViewWeight> tmpViewWeightTextFields = new ListView<ViewWeight>(
						ID.WEIGHTS, tmpView.getViewWeights()) {

					@Override
					protected void populateItem(ListItem<ViewWeight> aViewWeightsListItem) {

						final ViewWeight tmpViewWeight = aViewWeightsListItem
								.getModelObject();

						ContextTextField<Number> tmpWeightTextField = new ContextTextField<Number>(
								ID.WEIGHT, new PropertyModel<Number>(tmpViewWeight, ID.WEIGHT),
								StandardType.PERCENT);
						aViewWeightsListItem.add(tmpWeightTextField);
						tmpWeightTextField.add(new OnChangeAjaxBehavior() {

							@Override
							protected void onUpdate(
									AjaxRequestTarget aWeightTextFieldChangeTarget) {
								System.out.println("AJAX!");
								System.out.println("THIS ViewWeight: " + tmpViewWeight);
								for (ViewWeight tmpVW : tmpViewWeight.getView()
										.getViewWeights()) {
									System.out.println("LOOP ALL ViewWeights: " + tmpVW);
								}
								System.out.println();

								aWeightTextFieldChangeTarget.addComponent(tmpTotalWeightLabel);
							}
						});
					}
				};

				aViewsListItem.add(tmpViewWeightTextFields);
				aViewsListItem.add(tmpTotalWeightLabel);
			};
		};*/
	}
}
