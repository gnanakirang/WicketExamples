package com.mywicket;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.mywicket.data.shoppingcart.Item;
import com.mywicket.data.shoppingcart.ShoppingBasket;
import com.mywicket.services.ItemService;
import com.mywicket.services.UserService;

public abstract class MyAppPage extends WebPage {
	@SpringBean
	private ItemService itemService;
	@SpringBean
	private UserService userService;
	
	private List<Item> itemList;
	
	private List<Item> selectedItems;
	
	public MyAppSession getCurrentSession(){
		return (MyAppSession)getSession();
	}
	
	public ShoppingBasket getShoppingBasket() {
		return getCurrentSession().getShoppingBasket();
	}

	public ItemService getItemService() {
		return itemService;
	}

	public UserService getUserService() {
		return userService;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Item> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<Item> selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	public double getTotalSelectedItemsPrice(){
		double totalPrice = 0;
		selectedItems = getShoppingBasket().getItemsList();
		for (Item item : selectedItems){
			if(item.getNumberOfQuantity()>0){
				totalPrice += (item.getNumberOfQuantity() * item.getPrice());
			}
		}
		
		return totalPrice;
	}
	
}
