package com.xizhimojie.blog.service;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xizhimojie.blog.domain.User;
import com.xizhimojie.common.persistence.DBUtilsHelper;

public class UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public static final String GET_USER_SQL = "SELECT ID AS id, LOGIN_NAME AS loginName, PASSWORD AS password, "
			+ "EMAIL AS email, PHONE as phone, LOGIN_IP AS loginIp, LOGIN_DATE AS loginDate FROM SYS_USER WHERE "
			+ "LOGIN_NAME=? AND PASSWORD=?";
	public static final String GET_USER_BY_LOGINNAME_SQL = "SELECT ID AS id, LOGIN_NAME AS loginName, PASSWORD AS password, "
			+ "EMAIL AS email, PHONE as phone, LOGIN_IP AS loginIp, LOGIN_DATE AS loginDate FROM SYS_USER WHERE "
			+ "LOGIN_NAME=?";
	public static final String SQL_UPDATE_PASSWD = " UPDATE SYS_USER SET PASSWORD = ? WHERE LOGIN_NAME ='admin' ";
	
	
	public User getUser(String loginName, String password) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<User> resultSetHandler = new BeanHandler<User>(  
				User.class);
		try {
			User user = runner.query(GET_USER_SQL, resultSetHandler, loginName,password);
			return user;
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}
	
	public User getUser(String loginName) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<User> resultSetHandler = new BeanHandler<User>(  
				User.class);
		try {
			User user = runner.query(GET_USER_BY_LOGINNAME_SQL, resultSetHandler, loginName);
			return user;
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}
	
	public void updatePasswd(String password) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(SQL_UPDATE_PASSWD, password);
		} catch (Exception e) {
			logger.error(e + "");
		}
	}
}
