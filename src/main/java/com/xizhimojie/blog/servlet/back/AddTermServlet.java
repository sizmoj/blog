package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.Term;
import com.xizhimojie.blog.service.TermService;
@WebServlet(urlPatterns={"/back/addTerm"})
public class AddTermServlet extends HttpServlet{
	private static final long serialVersionUID = -3639995055865580141L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		TermService ts = new TermService();
		Term term = new Term();
		String name = request.getParameter("name");
		Term endTerm =  ts.getTerm(name);
		if(endTerm != null) {
			response.sendRedirect("termList");
			return;
		}
		term.setName(name);
		ts.addTerm(term);
		response.sendRedirect("termList");
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/back/addTerm.jsp").forward(request, response);
	}

}
