package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.service.PostService;

@WebServlet(urlPatterns={"/back/deletePost"})
public class DeletePostServlet extends HttpServlet{
	private static final long serialVersionUID = -2319651523196993872L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		PostService ps = new PostService();
		Long id = Long.valueOf(request.getParameter("id"));
		ps.deletePost(id);
		response.sendRedirect("postList");
		return;
	}

}
