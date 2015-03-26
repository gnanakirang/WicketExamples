package com.mywicket.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mywicket.data.Item;
import com.mywicket.data.ItemType;

@Service
public class ItemServiceImpl implements ItemService {
	
	private List<Item> itemList = Arrays.asList(new Item("Heritage Milk", "Pure Milk", ItemType.Food, 10),
			new Item("Surf", "Best Quality Dedargent", ItemType.HouseItem, 100),
			new Item("iPad 4", "Apple latest version of iPad", ItemType.Electronics, 50000),
			new Item("GoodYear", "Radial Tyers for Hatchback car", ItemType.Automobile, 50000),
			new Item("Outing Tent", "Tent for great outdoor adventures", ItemType.Adventure, 50000)
			);
	
	@Override
	public List<Item> getItemList() {
		return itemList;
	}

	@Override
	public List<Item> getItemListByAdding() {
		itemList = new ArrayList<Item>(itemList);
		itemList.add(new Item("iPhone "+itemList.size(), "Apple latest version of iPad", ItemType.Electronics, 35000+(1000*itemList.size())));
		return itemList;
	}	


}
