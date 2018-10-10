package org.lq.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.lq.bean.Order;
import org.lq.dao.OrderDao;
import org.lq.util.DBManager;

public class OrderDaoImpl implements OrderDao {
	QueryRunner runner = new QueryRunner();
	@Override
	public void save(Order order) throws SQLException {

		try {
			String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
			DBManager.getConnection().setAutoCommit(false);
			runner.update(DBManager.getConnection(), sql, order.getId(), order.getMoney(), order.getReceiverAddress(),
					order.getReceiverName(), order.getReceiverPhone(), order.getPayState(), order.getOrdertime(),
					order.getUser().getId());

			sql = "insert into orderitem values(?,?,?)";

			Object[][] params = new Object[order.getItems().size()][3];
			for (int i = 0; i < params.length; i++) {
				params[i][0] = order.getId();
				params[i][1] = order.getItems().get(i).getBook().getId();
				params[i][2] = order.getItems().get(i).getNumber();

			}
			runner.batch(DBManager.getConnection(), sql, params);
			DBManager.getConnection().commit();
		} catch (SQLException e) {
			DBManager.getConnection().rollback();
			throw e;
		}

	}

}
