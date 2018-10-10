package org.lq.bean;

public class CartItem {
	Book book;
	Integer numbers;
	
	public double getPrice() {
		return (double)Math.round( 100*numbers*book.getPrice() )/100;		
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
}
