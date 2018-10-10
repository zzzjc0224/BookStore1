package org.lq.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBManager {
	private static ThreadLocal<Connection> map=new ThreadLocal<>();
	private static DataSource ds;
	static {
		ComboPooledDataSource dataSource =new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bookstore");
			dataSource.setUser("root");
			dataSource.setPassword("root");
			ds=dataSource;
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static Connection getConnection() throws SQLException {
		Connection conn = map.get();
		if(conn==null) {
			conn = ds.getConnection();
			map.set(conn);
		}
		return conn;
	}
}
