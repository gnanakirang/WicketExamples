package com.mywicket.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Item> selectedItems = new ArrayList<Item>();
	public List<Item> getItemsList() {
		return selectedItems;
	}
	
	public void addSelectedItem(Item selectedItem) {
		synchronized(selectedItems){
			for (Item item : selectedItems){
				if (item.isSameItem(selectedItem)){
					item.setNumberOfQuantity(item.getNumberOfQuantity()+1);
					return;
				}
			}		
			selectedItem.setNumberOfQuantity(1);
			this.selectedItems.add(selectedItem);
		}		
	}
	
	
	public void removeItemFromList(int index){
		this.selectedItems.remove(index);
	}
	
	public void removeItemFromList(Item selected){
		this.selectedItems.remove(selected);
	}
}
