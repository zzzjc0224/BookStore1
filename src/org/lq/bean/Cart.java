package org.lq.bean;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> itemsMap  = new HashMap<>();
	
	public Map<String, CartItem> getItemsMap() {
		return itemsMap;
	}
	
	public void setItemsMap(Map<String, CartItem> itemsMap) {
		this.itemsMap = itemsMap;
	}
	
	//添加商品
	public void addBook(Book book) {
		addBooks(book,1);
	}
	
	//添加多本
	public void addBooks(Book book, int number) {
		CartItem cartItem=itemsMap.get(book.getId());
		if(cartItem==null) {
			cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setNumbers(number);
			itemsMap.put(book.getId(), cartItem);
		}
		
	}

	public double getPrice() {
		double price = 0;
		for (CartItem item : itemsMap.values()) {
			price += item.getPrice();
		}
		return price;
	}
	public void subABook(Book book) {
		CartItem item = itemsMap.get(book.getId());
		if(item.getNumbers()==1) {
			itemsMap.remove(book.getId());
		}else {
			item.setNumbers(item.getNumbers()-1);
		}
	}
	public void setNumberOfBook(Book book, int number) {
		CartItem item = itemsMap.get(book.getId());
		if (number == 0) {
			itemsMap.remove(book.getId());
		} else {
			item.setNumbers(number);
		}
	}
}
