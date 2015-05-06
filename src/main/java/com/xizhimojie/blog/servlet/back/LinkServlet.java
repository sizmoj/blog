package com.xizhimojie.blog.servlet.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.Link;
import com.xizhimojie.blog.service.LinkService;
@WebServlet(urlPatterns={"/back/linkList"})
public class LinkServlet extends HttpServlet{

	private static final long serialVersionUID = 6271592891188750809L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		LinkService ls = new LinkService();
		List<Link> links = ls.getLinks();
		request.setAttribute("links", links);
		request.getRequestDispatcher("/WEB-INF/back/linkList.jsp").forward(request, response);	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
	}
	
	
}
