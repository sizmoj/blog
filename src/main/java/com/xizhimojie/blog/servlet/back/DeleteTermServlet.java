package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.Post;
import com.xizhimojie.blog.service.PostService;
import com.xizhimojie.blog.service.TermService;
import com.xizhimojie.common.web.Page;
@WebServlet(urlPatterns={"/back/deleteTerm"})
public class DeleteTermServlet extends HttpServlet{
	private static final long serialVersionUID = -2319651523196993872L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		TermService ts = new TermService();
		PostService ps = new PostService();
		Long id = Long.valueOf(request.getParameter("id"));
		Page<Post> posts = ps.getPostList(1, 1000000, null, id.toString());
		if(posts.getList().size() > 0) {
			response.sendRedirect("termList");
			return;
		}
		ts.deleteTerm(id);
		response.sendRedirect("termList");
		return;
	}
}
