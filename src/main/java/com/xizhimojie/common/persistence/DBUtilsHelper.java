package com.xizhimojie.common.persistence;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xizhimojie.blog.service.TermService;

public class DBUtilsHelper {
	private static DataSource ds = null;
	private static QueryRunner runner = null;
	
	private static Logger logger = LoggerFactory.getLogger(DBUtilsHelper.class);
	
	private DBUtilsHelper() {
		
	}
	public static QueryRunner getRunner() {
		try {
			ds = DbPoolConnection.getInstance().getDataSource();
		} catch (SQLException e) {
			logger.error(e + "");
		}
		if (ds != null) {
			return new QueryRunner(ds);
		} else {
			throw new RuntimeException("get datasource can't be null"); 
		}
	}
}