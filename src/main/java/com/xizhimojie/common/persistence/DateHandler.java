package com.xizhimojie.common.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.ResultSetHandler;

public class DateHandler implements ResultSetHandler<Date>{

	@Override
	public Date handle(ResultSet rs) throws SQLException {
		if(rs.next()) {
			return rs.getDate(1);
		}
		return null;
	}

}
