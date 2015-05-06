package com.xizhimojie.blog.service;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xizhimojie.blog.domain.Project;
import com.xizhimojie.common.persistence.DBUtilsHelper;

public class ProjectService {
	
	private static Logger logger = LoggerFactory.getLogger(ProjectService.class);
	
	private static final String SQL_GET_ABOUT = "SELECT ID, CONTENT FROM BLOG_PROJECTS WHERE ID = 1";
	
	private static final String SQL_UPDATE_ABLUT = "UPDATE BLOG_PROJECTS SET CONTENT = ? WHERE ID = 1";
	
	public Project getProjects() {
		QueryRunner runner = DBUtilsHelper.getRunner();
		ResultSetHandler<Project> resultSetHandler = new BeanHandler<Project>(  
				Project.class);
		try {
			return runner.query(SQL_GET_ABOUT, resultSetHandler);
		} catch (Exception e) {
			logger.error(e + "");
		}
		return null;
	}
	
	public void updateProjects(String content) {
		QueryRunner runner = DBUtilsHelper.getRunner();
		try {
			runner.update(SQL_UPDATE_ABLUT, content);
		} catch (Exception e) {
			logger.error(e + "");
		}
	}
	
	
			
}
