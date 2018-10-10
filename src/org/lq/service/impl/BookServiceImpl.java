package org.lq.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.lq.bean.Book;
import org.lq.dao.BookDao;
import org.lq.dao.impl.BookDaoImpl;
import org.lq.exception.ServerException;
import org.lq.service.BookService;

public  class BookServiceImpl implements BookService {
	BookDao dao= new BookDaoImpl();
	
	
	
	
	
	@Override
	public Map<String, Book> page(int pageIndex, int size) throws ServerException {
		Map<String,Book> map = null;
		
		try {
			map=dao.getList(pageIndex,size);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public Integer getTotals() throws SQLException {
		Integer totals=null;
		totals=dao.getTotals();
		return totals;
	}
	@Override
	public Book getBook(String id) throws ServerException {
		Book book = null;
		try {
			book = dao.getBookById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServerException();
		}
		return book;
	
	}
	
	
}
