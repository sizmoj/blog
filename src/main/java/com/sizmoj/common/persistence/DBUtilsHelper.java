package com.sizmoj.common.persistence;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

public class DBUtilsHelper {
	private DataSource ds = null;
	private QueryRunner runner = null;

	public DBUtilsHelper() {
		try {
			this.ds = DbPoolConnection.getInstance().getDataSource();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (this.ds != null) {
			this.runner = new QueryRunner(this.ds);
		}
	}

	public DBUtilsHelper(DataSource ds) {
		this.ds = ds;
		this.runner = new QueryRunner(this.ds);
	}

	public QueryRunner getRunner() {
		return this.runner;
	}
}