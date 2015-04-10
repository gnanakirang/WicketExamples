package com.mywicket.services;

import java.util.List;

import com.mywicket.data.shoppingcart.Item;

public interface ItemService {
	List<Item> getItemList();
	List<Item> getItemListByAdding();
}
