package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.service.LinkService;

@WebServlet(urlPatterns={"/back/deleteLink"})
public class DeleteLinkServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1472313646630499694L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		LinkService ls = new LinkService();
		Long id = Long.valueOf(request.getParameter("id"));
		ls.deleteTerm(id);
		response.sendRedirect("linkList");
		return;
	}
}
