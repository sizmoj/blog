package com.xizhimojie.blog.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xizhimojie.blog.domain.Term;
import com.xizhimojie.blog.domain.User;
import com.xizhimojie.common.persistence.DBUtilsHelper;

public class TermService {
	public static final String GET_ALL_TERM_SQL = 
			"SELECT ID AS id, NAME AS name FROM BLOG_TERM WHERE STATUS = '1'";
	public static final String GET_TERM_SQL = 
			"SELECT ID AS id, NAME AS name FROM BLOG_TERM WHERE STATUS = '1' AND ID = ?";
	
	public static final String SQL_GET_TERM_BY_NAME = 
			"SELECT ID AS id, NAME AS name FROM BLOG_TERM WHERE STATUS = '1' AND NAME= ?";
	
	public static final String ADD_TERM_SQL = 
			"INSERT INTO BLOG_TERM(NAME) VALUES(?)";
	
	public static final String UPDATE_TERM_SQL = 
			"UPDATE BLOG_TERM SET NAME = ? WHERE ID = ?";
	
	public static final String DETELE_TERM_SQL = 
			"DELETE FROM BLOG_TERM WHERE ID = ?";
	
	public static final String GET__TERM_BY_ID_SQL = 
			"SELECT ID AS id, NAME AS name FROM BLOG_TERM WHERE STATUS = '1' AND ID=?";
	
	private static Logger logger = LoggerFactory.getLogger(TermService.class);
	
	public List<Term> getTerms() {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<List<Term>> resultSetHandler = 
				new BeanListHandler<Term>(Term.class);
		try {
			return runner.query(GET_ALL_TERM_SQL, resultSetHandler);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}
	
	public Term getTerm(long id) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<Term> resultSetHandler = new BeanHandler<Term>(  
				Term.class);
		try {
			return runner.query(GET_TERM_SQL, resultSetHandler, id);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}

	public Term getTerm(String name) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<Term> resultSetHandler = new BeanHandler<Term>(  
				Term.class);
		try {
			return runner.query(SQL_GET_TERM_BY_NAME, resultSetHandler, name);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return null;
	}
	
	
	public boolean addTerm(Term term) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(ADD_TERM_SQL, term.getName());
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return true;
	}
	
	public boolean updateTerm(Term term) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(UPDATE_TERM_SQL, term.getName(), term.getId());
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return true;
	}
	
	public boolean deleteTerm(Long id) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(DETELE_TERM_SQL, id);
		} catch (SQLException e) {
			logger.error(e + "");
		}
		return true;
	}
}
