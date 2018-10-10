package org.lq.service.impl;

import java.sql.SQLException;

import org.lq.bean.User;
import org.lq.dao.UserDao;
import org.lq.dao.impl.UserDaoImpl;
import org.lq.exception.UserActiveFailedException;
import org.lq.exception.UserRegistFailedException;
import org.lq.exception.UserRegisterFailedException;
import org.lq.service.UserService;
import org.lq.dao.impl.*;
public class UserServiceImpl implements UserService {
	UserDao dao =new UserDaoImpl();
	
	public UserDao getDao() {
		return dao;
	}
	public void setDao(UserDao dao) {
		this.dao=dao;
	}
	@Override
	public void register(User user) throws UserRegisterFailedException {
		try {
			dao.save(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		throw new UserRegisterFailedException("用户注册失败");
		}
		
	}
	@Override
	public void active1(String id) throws UserActiveFailedException {
	
		try {
			dao.updateActiveCodeById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserActiveFailedException("the invalid id:"+id);
		}	
		
	}
	@Override
	public User login(String username, String password) throws SQLException {
		
		 return dao.getByUsernameAndPassword(username, password);
	}
	@Override
	public void update(User user) throws SQLException {
		System.out.println("service impl");
		dao.update(user);
		
	}
	
}
