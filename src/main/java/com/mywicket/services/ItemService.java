package com.mywicket.services;

import java.util.List;

import com.mywicket.data.Item;

public interface ItemService {
	List<Item> getItemList();
	List<Item> getItemListByAdding();
}
