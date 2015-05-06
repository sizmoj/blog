package com.xizhimojie.blog.servlet.back;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.xizhimojie.blog.domain.Post;
import com.xizhimojie.blog.domain.Tag;
import com.xizhimojie.blog.domain.Term;
import com.xizhimojie.blog.domain.User;
import com.xizhimojie.blog.service.PostService;
import com.xizhimojie.blog.service.TermService;
import com.xizhimojie.blog.service.UserService;

@WebServlet(urlPatterns={"/back/addPost"})
public class AddPostServlet extends HttpServlet{

	private static final long serialVersionUID = 1768157838607049645L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		UserService us = new UserService();
		Post post = new Post();
		PostService ps = new PostService();
		Set<Tag> tags = new HashSet<Tag>();
		String postTitle = request.getParameter("postTitle");
		String content = request.getParameter("content");
		String url = request.getParameter("url");
		/*url = RandomStringUtils.random(6, "1234567890qwertyuiozxcvbnmm") + "-" +url;*/
		String strtags = request.getParameter("tags");
		String termId = request.getParameter("termId");
		String[] tagnames = StringUtils.split(strtags, ";");
		Tag tag = null;
		for(String name: tagnames) {
			tag = new Tag();
			tag.setName(name);
			tags.add(tag);
		}
		post.setPostTitle(postTitle);
		post.setContent(content);
		post.setClickCount(0);
		post.setUrl(url);
		post.setTermId(Integer.valueOf(termId));
		post.setUpdateDate(new Date());
		post.setPublicDate(new Date());
		String loginName = (String)request.getSession().getAttribute("loginName");
		User user = us.getUser(loginName);
		ps.AddPost(post, tags, user);
		response.sendRedirect("postList");
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		TermService ts = new TermService();
		List<Term> terms = ts.getTerms();
		request.setAttribute("terms", terms);
		request.getRequestDispatcher("/WEB-INF/back/addPost.jsp").forward(request, response);
	}
}
