package com.sizmoj.blog.service;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sizmoj.blog.domain.User;
import com.sizmoj.common.persistence.DBUtilsHelper;

public class UserService {
	public static final String GET_USER_SQL = "SELECT ID AS id, LOGIN_NAME AS loginName, PASSWORD AS password, "
			+ "EMAIL AS email, PHONE as phone, LOGIN_IP AS loginIp, LOGIN_DATE AS loginDate FROM SYS_USER WHERE "
			+ "LOGIN_NAME=? AND PASSWORD=?";
	public static final String GET_USER_BY_LOGINNAME_SQL = "SELECT ID AS id, LOGIN_NAME AS loginName, PASSWORD AS password, "
			+ "EMAIL AS email, PHONE as phone, LOGIN_IP AS loginIp, LOGIN_DATE AS loginDate FROM SYS_USER WHERE "
			+ "LOGIN_NAME=?";
	public User getUser(String loginName, String password) {
		QueryRunner runner = (new DBUtilsHelper()).getRunner();
		ResultSetHandler<User> resultSetHandler = new BeanHandler<User>(  
				User.class);
		try {
			User user = runner.query(GET_USER_SQL, resultSetHandler, loginName,password);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUser(String loginName) {
		QueryRunner runner = (new DBUtilsHelper()).getRunner();
		ResultSetHandler<User> resultSetHandler = new BeanHandler<User>(  
				User.class);
		try {
			User user = runner.query(GET_USER_BY_LOGINNAME_SQL, resultSetHandler, loginName);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
