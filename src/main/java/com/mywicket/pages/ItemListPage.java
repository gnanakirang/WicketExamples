package com.mywicket.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountQueryString;

import com.mywicket.MyAppPage;
import com.mywicket.data.shoppingcart.Item;

@MountPath(path = "itemListPage")
@MountQueryString
public class ItemListPage extends MyAppPage {
	// public Label grandTotallabel; change-1
	@SuppressWarnings("all")
	public ItemListPage() {
		
		add(new Link("addNewItem") {

			@Override
			public void onClick() {
				setItemList(getItemService().getItemListByAdding());
				setResponsePage(ItemListPage.class);
			}

		});
		
		
		final Label grandTotalLabel = new Label("grandTotal", new Model() {
			@Override
			public String getObject() {
				return "Rs." + getTotalSelectedItemsPrice();
			}
		});
		grandTotalLabel.setOutputMarkupId(true);
		
		// Available Items list view
		//
		PageableListView pageListView = getPageListView();
		//add(getListView()); TODO: all the rows will be displayed in single page.
		add(pageListView);
		add(new PagingNavigator("navigator", pageListView));

		// Selected Items View
		//
		add(new ListView<Item>("selectedItems", new PropertyModel(this,
				"shoppingBasket.selectedItems")) {

			@Override
			protected void populateItem(ListItem<Item> selected) {
				final Item addedItem = selected.getModelObject();

				selected.add(new Label("name", addedItem.getName()));
				selected.add(new Label("price", " - Rs." + addedItem.getPrice()));
				final Label quantityTotal = new Label("quantityTotal", " = Rs."	+ addedItem.getTotalPrice());
				quantityTotal.setOutputMarkupId(true);
				
				final TextField<Integer> numberOfQuantity = new TextField<Integer>("numberOfQuantity",
						new PropertyModel<Integer>(addedItem, "numberOfQuantity"));
				
				selected.add(numberOfQuantity);
				
				
				OnChangeAjaxBehavior onChangeAjaxBehavior = new OnChangeAjaxBehavior()
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {     
            	quantityTotal.setDefaultModelObject(" = Rs."	+ addedItem.getTotalPrice());
            	target.addComponent(quantityTotal);
            	target.addComponent(grandTotalLabel);
            }
        };
        
        numberOfQuantity.add(onChangeAjaxBehavior);

				selected.add(quantityTotal);

				selected.add(new Link("remove", selected.getModel()) {

					@Override
					public void onClick() {
						Item removeItem = (Item) getModelObject();
						getShoppingBasket().removeItemFromList(removeItem);
					}

				});

			}

		});

		// Grand Total View
		add(grandTotalLabel);

		// Checkout View

		add(new Link("checkout") {
			@Override
			public void onClick() {
				setResponsePage(new CheckOut());
			}

			@Override
			public boolean isVisible() {
				return !getShoppingBasket().getItemsList().isEmpty();
			}
		});
	}

	/**
	 * This is a generic List View with all the list items.
	 * 
	 * @return
	 */
	@SuppressWarnings("all")
	public ListView<Item> getListView() {
		return new ListView<Item>("items", getItemService().getItemList()) {

			@Override
			protected void populateItem(final ListItem<Item> pageItem) {
				Item item = pageItem.getModelObject();
				pageItem.add(new Label("name", item.getName()));
				pageItem.add(new Label("description", item.getDescription()));
				pageItem.add(new Label("price", "Rs." + item.getPrice()));

				pageItem.add(new Link("addToBasket", pageItem.getModel()) {

					@Override
					public void onClick() {
						Item selected = (Item) getModelObject();
						getShoppingBasket().addSelectedItem(selected);
					}

				});
			}

		};
	}
	
	/**
	 * This method uses PageableListView to implement pagination with specified rows for page
	 * here we are passing 2 in the constructor argument   
	 * 
	 * @return PageableListView
	 */
	@SuppressWarnings("all")
	public PageableListView<Item> getPageListView() {
		setItemList(getItemService().getItemList());
		return new PageableListView<Item>("items", getItemList(), 5) {

			@Override
			protected void populateItem(final ListItem<Item> pageItem) {
				Item item = pageItem.getModelObject();
				pageItem.add(new Label("name", item.getName()));
				pageItem.add(new Label("description", item.getDescription()));
				pageItem.add(new Label("price", "Rs." + item.getPrice()));

				pageItem.add(new Link("addToBasket", pageItem.getModel()) {

					@Override
					public void onClick() {						
						Item selected = (Item) getModelObject();
						getShoppingBasket().addSelectedItem(selected);
					}

				});
			}

		};
	}
}
