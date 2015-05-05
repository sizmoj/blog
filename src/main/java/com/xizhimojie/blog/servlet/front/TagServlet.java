package com.xizhimojie.blog.servlet.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.service.TagService;
import com.xizhimojie.blog.service.TermService;

@WebServlet(urlPatterns = { "/tags" })
public class TagServlet extends HttpServlet {

	private static final long serialVersionUID = -1331072431317796035L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

		TagService ts = new TagService();
		TermService termService = new TermService();
		request.setAttribute("tags", ts.getTags());
		request.setAttribute("terms", termService.getTerms());
		request.getRequestDispatcher("/WEB-INF/front/tags.jsp").forward(
				request, response);
		return;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
