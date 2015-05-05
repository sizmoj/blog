package com.xizhimojie.blog.servlet.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.service.LinkService;

@WebServlet(urlPatterns = { "/links" })
public class FrontLinkServlet extends HttpServlet {

	private static final long serialVersionUID = -1331072431317796035L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

		LinkService ls = new LinkService();
		request.setAttribute("links", ls.getLinks());
		request.getRequestDispatcher("/WEB-INF/front/links.jsp").forward(
				request, response);
		return;
	}


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
