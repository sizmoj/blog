package com.xizhimojie.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/back/loginOut" })
public class LoginOutServlet extends HttpServlet {

	private static final long serialVersionUID = 1768157838607049645L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);// 防止创建Session
		if (session == null) {
			response.sendRedirect("login");
			return;
		}
		session.removeAttribute("loginName");
		response.sendRedirect("login");
	}
}
