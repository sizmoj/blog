package com.xizhimojie.blog.servlet.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.Term;
import com.xizhimojie.blog.service.TermService;

@WebServlet(urlPatterns = { "/back/termList" })
public class TermServlet extends HttpServlet {

	private static final long serialVersionUID = 1768157838607049645L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		TermService ts = new TermService();
		List<Term> terms = ts.getTerms();
		request.setAttribute("terms", terms);
		request.getRequestDispatcher("/WEB-INF/back/termList.jsp").forward(
				request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
