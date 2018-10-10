package org.lq.dao;

import java.sql.SQLException;

import org.lq.bean.User;

public interface UserDao {
	public void save(User user)throws SQLException;

	public void updateActiveCodeById(String id) throws SQLException;

	public void update(User user)throws SQLException;

	User getByUsernameAndPassword(String username, String password) throws SQLException;

	

	
	
	
}
