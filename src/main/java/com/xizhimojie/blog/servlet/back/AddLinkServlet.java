package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.Link;
import com.xizhimojie.blog.service.LinkService;
@WebServlet(urlPatterns={"/back/addLink"})
public class AddLinkServlet extends HttpServlet{
	private static final long serialVersionUID = -3639995055865580141L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		LinkService ls = new LinkService();	
		Link link = new Link();
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		link.setName(name);
		link.setUrl(url);
		ls.addLink(link);
		response.sendRedirect("linkList");
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/back/addLink.jsp").forward(request, response);
	}

}
