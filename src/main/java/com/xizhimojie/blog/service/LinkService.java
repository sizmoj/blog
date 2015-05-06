package com.xizhimojie.blog.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xizhimojie.blog.domain.Link;
import com.xizhimojie.common.persistence.DBUtilsHelper;

public class LinkService {
	
	public static final String GET_ALL_LINK_SQL = 
			"SELECT ID AS id, NAME AS name, URL AS url FROM BLOG_LINK";
	
	
	public static final String ADD_LINK_SQL = 
			"INSERT INTO BLOG_LINK(NAME, URL) VALUES(?,?)";
	
	public static final String DETELE_LINK_SQL = 
			"DELETE FROM BLOG_LINK WHERE ID = ?";
	
	private static Logger logger = LoggerFactory.getLogger(LinkService.class);
	
	public List<Link> getLinks() {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<List<Link>> resultSetHandler = 
				new BeanListHandler<Link>(Link.class);
		try {
			return runner.query(GET_ALL_LINK_SQL, resultSetHandler);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}
	
	public boolean addLink(Link link) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(ADD_LINK_SQL, link.getName(), link.getUrl());
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return true;
	}	
	
	public boolean deleteTerm(Long id) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(DETELE_LINK_SQL, id);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return true;
	}
}
