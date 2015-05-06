package com.xizhimojie.blog.servlet.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.Project;
import com.xizhimojie.blog.service.ProjectService;
import com.xizhimojie.common.markdowm.MarkdownProcessor;

@WebServlet(urlPatterns={"/projects"})
public class FrontProjectServlet extends HttpServlet{
	
	private static final long serialVersionUID = 4629581617168678614L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ProjectService ps = new ProjectService();
        Project project = ps.getProjects();
		MarkdownProcessor markup = new MarkdownProcessor();
		project.setContent(markup.markdown(project.getContent()));
		request.setAttribute("project", project);
		request.getRequestDispatcher("/WEB-INF/front/projects.jsp").forward(request, response);
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
	}

}
