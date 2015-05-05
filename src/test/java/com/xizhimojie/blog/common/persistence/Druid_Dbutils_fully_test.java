package com.xizhimojie.blog.common.persistence;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xizhimojie.common.persistence.DBUtilsHelper;
import com.xizhimojie.common.persistence.JDBCUtils;

public class Druid_Dbutils_fully_test {

	public static void main(String[] args) throws SQLException {
		//测试事物回滚情况, 都莫有插入
		try {		
		JDBCUtils.startTransaction();
		//DBUtilsHelper dbh = new DBUtilsHelper();
		QueryRunner runner = DBUtilsHelper.getRunner();
		runner.update("INSERT INTO SYS_USER(LOGIN_NAME,PASSWORD,NAME) VALUES('回滚admin','admin','admin')");
		//执行不能成功的SQL
		System.out.println("11");
		runner.update("INSERT INTO SYS_USER(LOGIN_NAME,PASSWORD,NAME) VALUES('admin','admin','admin')");
		JDBCUtils.commit();
		} catch (Exception e) {
			System.out.println("回滚 = =~");
			e.printStackTrace();
			JDBCUtils.rollback();
		}finally{
			System.out.println("222");
			JDBCUtils.close();
		}
		//事物不回滚,1条成功,一条失败
		/*DBUtilsHelper dbh = new DBUtilsHelper();
		QueryRunner runner = dbh.getRunner();
		runner.update("INSERT INTO SYS_USER(LOGIN_NAME,PASSWORD,NAME) VALUES('admin','admin','admin')");
		//bad sql 用户表不存在
		runner.update("INSERT INTO SYS_USE(LOGIN_NAME,PASSWORD,NAME) VALUES('admin','admin','admin')");*/

	}	
}