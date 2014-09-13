package com.sizmoj.blog.servlet.back;

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

import com.sizmoj.blog.domain.Post;
import com.sizmoj.blog.domain.Tag;
import com.sizmoj.blog.domain.Term;
import com.sizmoj.blog.domain.User;
import com.sizmoj.blog.service.PostService;
import com.sizmoj.blog.service.TagService;
import com.sizmoj.blog.service.TermService;
import com.sizmoj.blog.service.UserService;

@WebServlet(urlPatterns={"/back/updateTerm"})
public class UpdateTermServlet extends HttpServlet{
	private static final long serialVersionUID = -4413201945607711537L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		TermService us = new TermService();
		long id = Long.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		Term term = us.getTerm(id);
		term.setName(name);
		us.updateTerm(term);
		response.sendRedirect("termList");
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		TermService ts = new TermService();
		long id = Long.valueOf(request.getParameter("id"));
		Term term = ts.getTerm(id);
		request.setAttribute("term", term);
		request.getRequestDispatcher("/WEB-INF/back/updateTerm.jsp").forward(request, response);
	}
}
