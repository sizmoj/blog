package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.service.AboutService;
@WebServlet(urlPatterns={"/back/updateAbout"})
public class AboutServlet extends HttpServlet{

	private static final long serialVersionUID = 2044049091429148053L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		AboutService as = new AboutService();
		String content = request.getParameter("content");
		as.updateAbout(content);
		response.sendRedirect("updateAbout");
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		AboutService as = new AboutService();
		request.setAttribute("about", as.getAbout());
		request.getRequestDispatcher("/WEB-INF/back/updateAbout.jsp").forward(request, response);
	}
	
	
}
