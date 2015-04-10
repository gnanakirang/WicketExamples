package com.mywicket;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.mywicket.data.shoppingcart.ShoppingBasket;

public class MyAppSession extends WebSession {

	private static final long serialVersionUID = 1L;
	
	private ShoppingBasket shoppingBasket = null;
	
	public MyAppSession(Request request) {
		super(request);		
		setShoppingBasket(new ShoppingBasket());
	}

	public ShoppingBasket getShoppingBasket() {
		return shoppingBasket;
	}

	public void setShoppingBasket(ShoppingBasket shoppingBasket) {
		this.shoppingBasket = shoppingBasket;
	}

}
