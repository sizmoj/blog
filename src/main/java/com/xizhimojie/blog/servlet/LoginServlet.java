package com.xizhimojie.blog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xizhimojie.blog.domain.User;
import com.xizhimojie.blog.service.UserService;
@WebServlet(urlPatterns={"/login"})
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 369161888958231165L;
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("loginName");
		if(StringUtils.isNotBlank(username)) {
			response.sendRedirect("back/postList");
			return;
		}
		if(StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
			request.setAttribute("message", "请输入用户名密码！");
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			return;
		} else {
			UserService us = new UserService();
			User user = us.getUser(loginName, password);
			if(user == null ) {
				request.setAttribute("message", "用户名或者密码错误");
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			} else {
				logger.info("LoginDate:" + new Date() + "LoginIp:" + request.getRemoteAddr());
				session.setAttribute("loginName", user.getLoginName());
				response.sendRedirect("back/postList");
				return;
			}
		}        
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}
}	
