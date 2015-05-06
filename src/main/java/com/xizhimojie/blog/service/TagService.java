package com.xizhimojie.blog.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xizhimojie.blog.domain.Tag;
import com.xizhimojie.common.persistence.DBUtilsHelper;

public class TagService {
	public static final String GET_ALL_TAG_SQL = 
			"SELECT ID AS id, NAME AS name FROM BLOG_TAG T WHERE T.ID IN (SELECT L.TAG_ID FROM BLOG_TAG_POST L)";
	public static final String GET_TAG_BY_POST_ID_SQL = 
			"SELECT T.ID AS id, T.NAME AS name FROM BLOG_TAG T LEFT JOIN BLOG_TAG_POST P ON "
			+ "T.ID = P.TAG_ID WHERE P.POST_ID = ?";
	
	public static final String ADD_TAG_SQL = 
			"INSERT INTO BLOG_TAG(NAME) VALUES(?)";
	
	public static final String UPDATE_TAG_SQL = 
			"UPDATE BLOG_TAG SET NAME = ?";
	
	public static final String DETELE_TAG_SQL = 
			"DELETE FROM BLOG_TAG WHERE ID = ?";
	
	public static final String GET_TAG_BY_NAME_SQL = 
			"SELECT ID AS id, NAME AS name FROM BLOG_TAG WHERE NAME = ?";
	
	private static Logger logger = LoggerFactory.getLogger(TagService.class);
	
	public List<Tag> getTags() {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<List<Tag>> resultSetHandler = 
				new BeanListHandler<Tag>(Tag.class);
		try {
			return runner.query(GET_ALL_TAG_SQL, resultSetHandler);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}
	
	public boolean addTag(Tag tag) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(ADD_TAG_SQL, tag.getName());
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return true;
	}
	
	public boolean updateTag(Tag tag) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(UPDATE_TAG_SQL, tag.getName());
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return true;
	}
	
	public boolean deleteTag(Long id) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(DETELE_TAG_SQL, id);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return true;
	}
	
	public Tag getTagByName(String name) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<Tag> resultSetHandler = new BeanHandler<Tag>(  
				Tag.class);
		try {
			return runner.query(GET_TAG_BY_NAME_SQL, resultSetHandler, name);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}

	public List<Tag> getTagByPostId(long postId) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<List<Tag>> resultSetHandler = 
				new BeanListHandler<Tag>(Tag.class);
		try {
			return runner.query(GET_TAG_BY_POST_ID_SQL, resultSetHandler, postId);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}
	
}
