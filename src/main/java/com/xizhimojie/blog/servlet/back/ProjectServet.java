package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.service.AboutService;
import com.xizhimojie.blog.service.ProjectService;
@WebServlet(urlPatterns={"/back/updateProjects"})
public class ProjectServet extends HttpServlet{

	private static final long serialVersionUID = 2044049091429148053L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		ProjectService as = new ProjectService();
		String content = request.getParameter("content");
		as.updateProjects(content);
		response.sendRedirect("updateProjects");
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		ProjectService as = new ProjectService();
		request.setAttribute("about", as.getProjects());
		request.getRequestDispatcher("/WEB-INF/back/updateProjects.jsp").forward(request, response);
	}
	
	
}
