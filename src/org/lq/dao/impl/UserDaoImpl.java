package org.lq.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.lq.bean.User;
import org.lq.dao.UserDao;
import org.lq.exception.NoSuchGenderException;
import org.lq.util.DBManager;


public class UserDaoImpl implements UserDao {
	QueryRunner runner=new QueryRunner();
	@Override
	public void save(User user) throws SQLException {
		
		String sql = "insert into user(username,password,gender,email,telephone "
				+ ",introduce,activecode, state,role,registtime) values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(DBManager.getConnection(), sql, user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),
				user.getTelephone(),user.getIntroduce(),user.getActiveCode(),user.getState(),user.getRole(),user.getRegistTime());
		
		sql = "select id from user where username=? and password=?";
		
		user.setId( runner.query(DBManager.getConnection(), sql, new ResultSetHandler<Integer>() {

			@Override
			public Integer handle(ResultSet rs) throws SQLException {
				Integer id = null;
				if(rs.next()) {
					id = rs.getInt("id");
				}
				return id;
			}}, user.getUsername(),user.getPassword()) );
		
		

	}
	@Override
	public void updateActiveCodeById(String id) throws SQLException {
		String sql="update user set activeCode=? where id=?";
		runner.update(DBManager.getConnection(), sql, 1,id);
		
	}
	@Override
	public User getByUsernameAndPassword(String username, String password) throws SQLException {
		String sql = "select id, username,gender,email,telephone,introduce,activecode, state,role,registtime from user where username=? and password=?";
		ResultSetHandler<User> rsh = new ResultSetHandler<User>() {

			@Override
			public User handle(ResultSet rs) throws SQLException {
				User user = null;
				if(rs.next()) {
					user = new User();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					try {
						user.setGender(rs.getString("gender"));
					} catch (NoSuchGenderException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.setActiveCode(rs.getString("activeCode"));
					user.setIntroduce(rs.getString("introduce"));
					user.setTelephone(rs.getString("telephone"));
					user.setRole(rs.getString("role"));
					user.setRegistTime(rs.getDate("registtime"));
					user.setState(rs.getInt("state"));
					
				}
				return user;
			}
			
		};
		return runner.query(DBManager.getConnection(), sql, rsh, username,password);
	}
	
	
	
	@Override
	public void update(User user) throws SQLException {
		System.out.println("daoimpl");
		String sql = "update user set password=?, username=?,gender=?  where id=?";
		runner.update(DBManager.getConnection(), sql, user.getPassword(), user.getUsername(),user.getGender(),user.getId());
	
		
	}
	

}
