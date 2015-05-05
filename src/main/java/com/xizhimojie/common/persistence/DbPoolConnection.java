package com.xizhimojie.common.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.xizhimojie.common.utils.PropertiesLoader;

public class DbPoolConnection {

	private static DbPoolConnection databasePool = null;
	private static DruidDataSource dds = null;
	static {
		Properties properties = PropertiesLoader.loadPropertyFile("db_server.properties");
		try {
			dds = (DruidDataSource) DruidDataSourceFactory
					.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DbPoolConnection() {
	}

	public static synchronized DbPoolConnection getInstance() {
		if (null == databasePool) {
			databasePool = new DbPoolConnection();
		}
		return databasePool;
	}

	public DruidDataSource getDataSource() throws SQLException {
		return dds;
	}

	public DruidPooledConnection getConnection() throws SQLException {
		return dds.getConnection();
	}
	
}