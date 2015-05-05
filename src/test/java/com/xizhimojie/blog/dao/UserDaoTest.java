package com.xizhimojie.blog.dao;

import org.junit.Test;

import com.xizhimojie.blog.domain.User;
import com.xizhimojie.blog.service.UserService;

public class UserDaoTest {

	@Test
	public void testGetUser() {
		UserService userDao = new UserService();
		User u = userDao.getUser("admin", "admin");
		System.out.println(u.getLoginName());
	}

}
