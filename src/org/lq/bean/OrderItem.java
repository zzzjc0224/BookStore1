package org.lq.bean;

import javax.mail.search.IntegerComparisonTerm;

public class OrderItem {
	private Book book;
	private Integer number;
	
	
	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public double getPrice() {
		return (double)Math.round( 100*book.getPrice()*number ) / 100 ;
	}

}
