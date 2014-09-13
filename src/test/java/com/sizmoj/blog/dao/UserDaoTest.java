package com.sizmoj.blog.dao;

import org.junit.Test;

import com.sizmoj.blog.domain.User;
import com.sizmoj.blog.service.UserService;

public class UserDaoTest {

	@Test
	public void testGetUser() {
		UserService userDao = new UserService();
		User u = userDao.getUser("admin", "admin");
		System.out.println(u.getLoginName());
	}

}
