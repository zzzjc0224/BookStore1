package org.lq.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.lq.bean.Book;
import org.lq.dao.BookDao;
import org.lq.util.DBManager;

public class BookDaoImpl implements BookDao {
	QueryRunner runner =new QueryRunner();
	@Override
	public Map<String,Book> getList(int pageIndex, int size) throws SQLException {
		String sql = "select id,name,price,category,pnum,imgurl,description from products limit ?,?";
		
		ResultSetHandler<Map<String,Book>> rsh = new ResultSetHandler<Map<String,Book>>() {

			@Override
			public Map<String,Book> handle(ResultSet rs) throws SQLException {
				Map<String,Book> map = null;
				while(rs.next()) {
					if(map==null) {
						map = new HashMap<>();
					}
					Book book = new Book();
					
					book.setId(rs.getString("id"));
					book.setName(rs.getString("name"));
					book.setPrice(rs.getDouble("price"));
					book.setImgurl(rs.getString("imgurl"));
					book.setDescription(rs.getString("description"));
					book.setCategory(rs.getString("category"));
					book.setPnum(rs.getInt("pnum"));
					map.put(book.getId(), book);
				}
				return map;
			}
			
		};
		
		return runner.query(DBManager.getConnection(), sql, rsh, (pageIndex-1)*size, size); 
	}
	@Override
	public Integer getTotals() throws SQLException {
		String sql = "select count(*) cot from products";
		ResultSetHandler<Integer> rsh = new ResultSetHandler<Integer>() {

			@Override
			public Integer handle(ResultSet rs) throws SQLException {
				Integer totals = null;
				if(rs.next()) {
					totals = rs.getInt("cot"); 
				}
				return totals;
			}
			
		};
		return runner.query(DBManager.getConnection(), sql, rsh);
	}
	@Override
	public Book getBookById(String id) throws SQLException {
String sql = "select id,name,price,category,pnum,imgurl,description from products where id=?";
		
		ResultSetHandler<Book> rsh = new ResultSetHandler<Book>() {

			@Override
			public Book handle(ResultSet rs) throws SQLException {
				Book book = null;
				if(rs.next()) {
					book = new Book();
					book.setId(rs.getString("id"));
					book.setName(rs.getString("name"));
					book.setPrice(rs.getDouble("price"));
					book.setImgurl(rs.getString("imgurl"));
					book.setDescription(rs.getString("description"));
					book.setCategory(rs.getString("category"));
					book.setPnum(rs.getInt("pnum"));
				}
				return book;
			}
			
		};
		
		return runner.query(DBManager.getConnection(), sql, rsh, id);
	}

}
