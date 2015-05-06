package com.xizhimojie.blog.service;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xizhimojie.blog.domain.Post;
import com.xizhimojie.blog.domain.Tag;
import com.xizhimojie.blog.domain.User;
import com.xizhimojie.common.persistence.DBUtilsHelper;
import com.xizhimojie.common.persistence.DateHandler;
import com.xizhimojie.common.persistence.JDBCUtils;
import com.xizhimojie.common.web.Page;

public class PostService {
	private static Logger logger = LoggerFactory.getLogger(PostService.class);
	
	@SuppressWarnings("rawtypes")
	private final static ScalarHandler countHandler = new ScalarHandler(){
        @Override
        public Object handle(ResultSet rs) throws SQLException {
            Object obj = super.handle(rs);
            if(obj instanceof BigInteger)
                return ((BigInteger)obj).longValue();
            return obj;
        }       
    };
	
	private static final String GET_POST_SQL = "SELECT DISTINCT p.ID AS id, p.USER_ID AS userId, p.URL AS url, p.LIKING "
			+ " AS liking, "
			+ "p.PUBLIC_DATE AS publicDate,"
			+ "p.CONTENT AS content, p.POST_TITLE AS postTitle, p.STATUS as status,"
			+ "p.POST_MODIFIED AS updateDate, p.MENU_ORDER AS menuOrder,"
			+ "p.COMMENT_COUNT AS commentCount, p.CLICK_COUNT AS clickCount,"
			+ "p.TERM_ID AS termId FROM BLOG_POST p LEFT JOIN BLOG_TAG_POST t ON p.ID = t.POST_ID "
			+ "WHERE p.STATUS ='1'";
	private static final String GET_POST_BY_ID_SQL = "SELECT p.ID AS id, p.USER_ID AS userId, p.URL AS url, p.LIKING"
			+ " AS liking, "
			+ "p.PUBLIC_DATE AS publicDate,"
			+ "p.CONTENT AS content, p.POST_TITLE AS postTitle, p.STATUS as status,"
			+ "p.POST_MODIFIED AS updateDate, p.MENU_ORDER AS menuOrder,"
			+ "p.COMMENT_COUNT AS commentCount, p.CLICK_COUNT AS clickCount,"
			+ "p.TERM_ID AS termId FROM BLOG_POST p LEFT JOIN BLOG_TAG_POST t ON p.ID = t.POST_ID "
			+ "WHERE p.STATUS ='1' AND p.ID = ? GROUP BY p.ID";
	
	private static final String SQL_GET_POST_BY_NEWEST_DATE = "SELECT p.ID AS id, p.USER_ID AS userId, p.URL AS url, p.LIKING"
			+ " AS liking, "
			+ "p.PUBLIC_DATE AS publicDate,"
			+ "p.CONTENT AS content, p.POST_TITLE AS postTitle, p.STATUS as status,"
			+ "p.POST_MODIFIED AS updateDate, p.MENU_ORDER AS menuOrder,"
			+ "p.COMMENT_COUNT AS commentCount, p.CLICK_COUNT AS clickCount,"
			+ "p.TERM_ID AS termId FROM BLOG_POST p LEFT JOIN BLOG_TAG_POST t ON p.ID = t.POST_ID "
			+ "WHERE p.STATUS ='1' AND p.PUBLIC_DATE IS NOT NULL ORDER BY p.PUBLIC_DATE DESC LIMIT 0, 1";
	
	private static final String SQL_GET_POST_BY_URL = "SELECT p.ID AS id, p.USER_ID AS userId, p.URL AS url, p.LIKING"
			+ " AS liking, "
			+ "p.PUBLIC_DATE AS publicDate,"
			+ "p.CONTENT AS content, p.POST_TITLE AS postTitle, p.STATUS as status,"
			+ "p.POST_MODIFIED AS updateDate, p.MENU_ORDER AS menuOrder,"
			+ "p.COMMENT_COUNT AS commentCount, p.CLICK_COUNT AS clickCount,"
			+ "p.TERM_ID AS termId FROM BLOG_POST p LEFT JOIN BLOG_TAG_POST t ON p.ID = t.POST_ID "
			+ "WHERE p.STATUS ='1' AND p.PUBLIC_DATE IS NOT NULL AND p.URL = ?";
	
	private static final String SQL_GET_POST_BY_OLDERDATE = "SELECT p.URL AS url FROM BLOG_POST p "
			+ "WHERE p.STATUS ='1' AND p.PUBLIC_DATE IS NOT NULL AND p.PUBLIC_DATE < ? ORDER BY p.PUBLIC_DATE DESC limit 0, 1";
	
	private static final String SQL_GET_POST_BY_NEWERDATE = "SELECT p.URL AS url FROM BLOG_POST p "
			+ "WHERE p.STATUS ='1' AND p.PUBLIC_DATE IS NOT NULL AND p.PUBLIC_DATE > ? ORDER BY p.PUBLIC_DATE limit 0, 1";
	
	
	private static final String SQL_GET_POST_BY_NEWEST_DATE_FOR_NINE = "SELECT p.ID AS id, p.URL AS url, p.LIKING"
			+ " AS liking, "
			+ "p.PUBLIC_DATE AS publicDate,"
			+ "p.POST_TITLE AS postTitle,"
			+ "p.TERM_ID AS termId FROM BLOG_POST p LEFT JOIN BLOG_TAG_POST t ON p.ID = t.POST_ID "
			+ "WHERE p.STATUS ='1' AND p.PUBLIC_DATE IS NOT NULL GROUP BY p.ID ORDER BY p.PUBLIC_DATE DESC LIMIT 1,10";
	
	private static final String SQL_GET_POST_BY_YEAR = "SELECT p.ID AS id, p.URL AS url, p.LIKING"
			+ " AS liking, "
			+ "p.PUBLIC_DATE AS publicDate,"
			+ "p.POST_TITLE AS postTitle,"
			+ "p.TERM_ID AS termId FROM BLOG_POST p LEFT JOIN BLOG_TAG_POST t ON p.ID = t.POST_ID "
			+ "WHERE p.STATUS ='1' AND p.PUBLIC_DATE IS NOT NULL AND p.PUBLIC_DATE BETWEEN ? AND ? GROUP BY p.ID ORDER BY p.PUBLIC_DATE DESC ";
	
	private static final String GET_POST_COUNT_SQL = "SELECT COUNT(*) FROM BLOG_POST p LEFT JOIN  BLOG_TAG_POST t ON p.ID = t.POST_ID WHERE p.STATUS ='1' ";
	
	private static final String ADD_POST_SQL = "INSERT INTO BLOG_POST(USER_ID, PUBLIC_DATE, CONTENT,"
			+ "POST_TITLE, POST_MODIFIED, TERM_ID, URL, CLICK_COUNT) "
			+ "VALUES(?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE_POST_SQL = "UPDATE BLOG_POST SET CONTENT = ?, URL = ?,"
			+ "POST_TITLE = ?, POST_MODIFIED = ?, TERM_ID = ? "
			+ "WHERE ID = ?";
	
	private static final String DELETE_TAG_POST_SQL = "DELETE FROM BLOG_TAG_POST WHERE POST_ID = ?";
	
	private static final String INSERT_POST_TAG_SQL = "INSERT INTO BLOG_TAG_POST(POST_ID, TAG_ID) VALUES (?,?)";
	
	private static final String DELETE_POST_SQL = "UPDATE BLOG_POST SET STATUS = '0' WHERE ID = ?";
	
	private static final String SQL_UPDATE_POST_CLICK_COUNT = "UPDATE BLOG_POST SET CLICK_COUNT = ? WHERE ID = ?";
	
	private static final String SQL_GET_EARLIST_DATE_POST =  "SELECT P.PUBLIC_DATE FROM BLOG_POST "
			+ "P WHERE STATUS = '1' AND P.PUBLIC_DATE IS NOT NULL ORDER BY P.PUBLIC_DATE LIMIT 0,1";
	
	public Page<Post> getPostList(int pageNumber, int pageSize, String tagId, String termId) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		Page<Post> page;
		ResultSetHandler<List<Post>> resultSetHandler = 
				new BeanListHandler<Post>(Post.class);
		StringBuffer sb = new StringBuffer(GET_POST_SQL);
		if(StringUtils.isNotBlank(tagId))
			sb.append(" AND t.TAG_ID = " + tagId);
		if(StringUtils.isNotBlank(termId))
			sb.append(" AND p.TERM_ID = " + termId);
		sb.append(" GROUP BY p.ID ");
		sb.append(" ORDER BY p.PUBLIC_DATE DESC ");
		sb.append(" LIMIT " + (pageNumber-1) * pageSize);
		sb.append("," + pageSize);
		try {
			List<Post> posts = runner.query(sb.toString(), resultSetHandler);
			int count = getPostCount(tagId, termId);
			page = new Page<Post>(pageNumber, pageSize, count, posts);
			return page;
		} catch (SQLException e) {
			logger.error(e + "");
			e.printStackTrace();
		}
		return null;
	}
	
	public int getPostCount(String tagId, String termId) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		StringBuffer sb = new StringBuffer(GET_POST_COUNT_SQL);
		if(StringUtils.isNotBlank(tagId))
			sb.append(" AND t.TAG_ID = " + tagId);
		if(StringUtils.isNotBlank(termId))
			sb.append(" AND p.TERM_ID = " + termId);
		try {
			@SuppressWarnings("unchecked")
			long count = (Long)runner.query(sb.toString(), countHandler);
			
			return (int)count;
		} catch (SQLException e) {
			logger.error(e + "");
			e.printStackTrace();
		}
		return 0;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean AddPost(Post post, Set<Tag> tags, User user) {
		try {
			JDBCUtils.startTransaction();
			QueryRunner runner = DBUtilsHelper.getRunner();;
			TagService tagDao = new TagService();
			runner.update(ADD_POST_SQL, user.getId(),post.getPublicDate(), post.getContent(), 
					post.getPostTitle(), new Date(), post.getTermId(), post.getUrl(), post.getClickCount());
			long id = ((BigInteger)runner.query("SELECT LAST_INSERT_ID()", new ScalarHandler(1))).longValue();
			Tag temptag = null;
			Long temptagid = null;
			for(Tag tag : tags) {
				temptag = tagDao.getTagByName(tag.getName());
				if(temptag == null) {
					tagDao.addTag(tag);
					temptagid = ((BigInteger)runner.query("SELECT LAST_INSERT_ID()", new ScalarHandler(1))).longValue();
					runner.update(INSERT_POST_TAG_SQL, id, temptagid);
				} else {
					runner.update(INSERT_POST_TAG_SQL, id, temptag.getId());
				}
				
			}
			JDBCUtils.commit();
		} catch (SQLException e) {
			JDBCUtils.rollback();
			logger.error(e + "");
			e.printStackTrace();
		} finally{
			JDBCUtils.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean updatePost(Post post, Set<Tag> tags) {
		try {
			JDBCUtils.startTransaction();
			QueryRunner runner = DBUtilsHelper.getRunner();;
			TagService tagDao = new TagService();
			runner.update(UPDATE_POST_SQL, post.getContent(), post.getUrl(), post.getPostTitle(), post.getUpdateDate(), post.getTermId(), post.getId());
			long id = post.getId();
			Tag temptag = null;
			Long temptagid = null;
			runner.update(DELETE_TAG_POST_SQL, post.getId());
			for(Tag tag : tags) {
				temptag = tagDao.getTagByName(tag.getName());
				if(temptag == null) {
					tagDao.addTag(tag);
					temptagid = ((BigInteger)runner.query("SELECT LAST_INSERT_ID()", new ScalarHandler(1))).longValue();
					runner.update(INSERT_POST_TAG_SQL, id, temptagid);
				} else {
					runner.update(INSERT_POST_TAG_SQL, id, temptag.getId());
				}
				
			}
			JDBCUtils.commit();
		} catch (SQLException e) {
			JDBCUtils.rollback();
			logger.error(e + "");
			e.printStackTrace();
		} finally{
			JDBCUtils.close();
		}
		return true;
	}
	
	public boolean deletePost(Long postId) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(DELETE_POST_SQL, postId);
			runner.update("DELETE FROM BLOG_TAG_POST WHERE POST_ID = ?", postId);
		} catch (Exception e) {
			logger.error(e + "");
		}
		return true;
	}
	
	public Post getPostById(Long postId) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<Post> resultSetHandler = new BeanHandler<Post>(  
				Post.class);
		try {
			return runner.query(GET_POST_BY_ID_SQL, resultSetHandler, postId);
		} catch (Exception e) {
			logger.error(e + "");
		}
		return null;
	}
	
	public Post getNewestPost() {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<Post> resultSetHandler = new BeanHandler<Post>(  
				Post.class);
		try {
			return runner.query(SQL_GET_POST_BY_NEWEST_DATE, resultSetHandler);
		} catch (Exception e) {
			logger.error(e + "");
		}
		return null;
	}
	/**
	 * 获取最新的9篇文章,除第一篇
	 * @return
	 */
	public List<Post> getNewestPostForTen() {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try{
			ResultSetHandler<List<Post>> resultSetHandler = 
					new BeanListHandler<Post>(Post.class);
				List<Post> posts = runner.query(SQL_GET_POST_BY_NEWEST_DATE_FOR_NINE, resultSetHandler);
				return posts;
		} catch (SQLException e) {
			logger.error(e + "");
			e.printStackTrace();
		}
		return null;
	}

	public LinkedHashMap<Integer, List<Post>> getArchivePost() {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		LinkedHashMap<Integer, List<Post>> mapposts = new LinkedHashMap<Integer, List<Post>>();
		try{
			DateHandler dateHandler = new DateHandler();
			Date date = runner.query(SQL_GET_EARLIST_DATE_POST, dateHandler);
			for(int i = 0; i <= countYear(date); i++) {
				int putYear = new DateTime().getYear() - i;
				Date begin = new DateTime(putYear , 1, 1, 0, 0, 0).toDate();
				Date end = new DateTime(putYear, 12, 31, 23, 59, 59).toDate();
				ResultSetHandler<List<Post>> resultSetHandler = 
						new BeanListHandler<Post>(Post.class);
					List<Post> posts = runner.query(SQL_GET_POST_BY_YEAR, resultSetHandler, begin, end);
				if(posts.size() > 0) {
					mapposts.put(putYear, posts);
				}
			}
			return mapposts;
		} catch (SQLException e) {
			logger.error(e + "");
			e.printStackTrace();
		}
		return null;
	}	

	public Post getPost(String url) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<Post> resultSetHandler = new BeanHandler<Post>(  
				Post.class);
		try {
			return runner.query(SQL_GET_POST_BY_URL, resultSetHandler, url);
		} catch (Exception e) {
			logger.error(e + "");
		}
		return null;
	}
	

	public Post getNewerPost(Date publicDate) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<Post> resultSetHandler = new BeanHandler<Post>(  
				Post.class);
		try {
			return runner.query(SQL_GET_POST_BY_NEWERDATE, resultSetHandler, publicDate);
		} catch (Exception e) {
			logger.error(e + "");
		}
		return null;
	}

	public Post getOlderPost(Date publicDate) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		ResultSetHandler<Post> resultSetHandler = new BeanHandler<Post>(  
				Post.class);
		try {
			return runner.query(SQL_GET_POST_BY_OLDERDATE, resultSetHandler, publicDate);
		} catch (Exception e) {
			logger.error(e + "");
		}
		return null;
	}
	
	/**
	 * 计算最早发表时间到现在时间的间隔
	 * @param earlistTime
	 * @return
	 */
	private static int countYear(Date earlistTime) {
		return new DateTime().getYear() - new DateTime(earlistTime).getYear();
	}

	public void updatePost(Post post) {
		QueryRunner runner = DBUtilsHelper.getRunner();;
		try {
			runner.update(SQL_UPDATE_POST_CLICK_COUNT, post.getClickCount(), post.getId());
		} catch (SQLException e) {
			logger.error(e + "");
		}
	}
	
}
