package org.lq.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.lq.bean.Book;

public interface BookDao {

	Map<String,Book> getList(int pageIndex, int size) throws SQLException;

	Integer getTotals() throws SQLException;
		
	Book getBookById(String id) throws SQLException;

}
