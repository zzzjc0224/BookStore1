package org.lq.service;

import java.sql.SQLException;

import org.lq.bean.User;
import org.lq.exception.UserActiveFailedException;
import org.lq.exception.UserRegisterFailedException;

public interface UserService {
	public void register(User user)throws UserRegisterFailedException;
	public void active1(String id) throws UserActiveFailedException;
	public User login(String username,String password) throws SQLException;
	public void update(User user) throws SQLException;
}
