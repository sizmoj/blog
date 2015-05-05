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

import org.apache.commons.lang3.StringUtils;

import com.xizhimojie.blog.domain.Post;
import com.xizhimojie.blog.domain.Tag;
import com.xizhimojie.blog.domain.Term;
import com.xizhimojie.blog.domain.User;
import com.xizhimojie.blog.service.PostService;
import com.xizhimojie.blog.service.TagService;
import com.xizhimojie.blog.service.TermService;
import com.xizhimojie.blog.service.UserService;
@WebServlet(urlPatterns={"/back/updatePost"})
public class UpdatePostServlet extends HttpServlet{
	private static final long serialVersionUID = 1768157838607049645L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		UserService us = new UserService();
		PostService ps = new PostService();
		Set<Tag> tags = new HashSet<Tag>();
		String postTitle = request.getParameter("postTitle");
		String content = request.getParameter("content");
		String url = request.getParameter("url");
		String strtags = request.getParameter("tags");
		String termId = request.getParameter("termId");
		long id = Long.valueOf((String)request.getParameter("id"));
		String[] tagnames = StringUtils.split(strtags, ";");
		Post post = ps.getPostById(id);
		Tag tag = null;
		for(String name: tagnames) {
			tag = new Tag();
			tag.setName(name);
			tags.add(tag);
		}
		post.setPostTitle(postTitle);
		post.setContent(content);
		post.setUrl(url);
		post.setTermId(Integer.valueOf(termId));
		post.setUpdateDate(new Date());
		String loginName = (String)request.getSession().getAttribute("loginName");
		User user = us.getUser(loginName);
		post.setUserId(user.getId());
		ps.updatePost(post, tags);
		response.sendRedirect("postList");
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		TagService tagService = new TagService();
		PostService ps = new PostService();
		TermService ts = new TermService();
		List<Term> terms = ts.getTerms();
		Long id = Long.valueOf(request.getParameter("id"));
		Post post = ps.getPostById(id);
		List<Tag> tags = tagService.getTagByPostId(post.getId());
		StringBuilder sb = new StringBuilder();
		
		for(Tag tag: tags) {
			sb.append(tag.getName()).append(";");
		}
		if(sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		
		request.setAttribute("tagnames", sb.toString());
		request.setAttribute("terms", terms);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/back/updatePost.jsp").forward(request, response);
	}
}
