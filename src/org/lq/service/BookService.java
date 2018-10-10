package org.lq.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.lq.bean.Book;
import org.lq.exception.ServerException;

public interface BookService {

	Map<String,Book> page(int pageIndex, int size) throws ServerException;

	Integer getTotals() throws SQLException;
	
	Book getBook(String id) throws ServerException;

}
