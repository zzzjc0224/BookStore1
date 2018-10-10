package org.lq.dao;

import java.sql.SQLException;

import org.lq.bean.Order;

public interface OrderDao {
	void save(Order order) throws SQLException;
}
