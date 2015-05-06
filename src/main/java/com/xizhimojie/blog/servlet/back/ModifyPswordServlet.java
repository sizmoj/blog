package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.xizhimojie.blog.domain.Term;
import com.xizhimojie.blog.service.TermService;
import com.xizhimojie.blog.service.UserService;
@WebServlet(urlPatterns={"/back/mdpassword"})
public class ModifyPswordServlet extends HttpServlet{
	private static final long serialVersionUID = -3639995055865580141L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		UserService ts = new UserService();
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		if(StringUtils.equals(password, password1) && StringUtils.isNotBlank(password))
		{
			request.setAttribute("message", "密码修改成功！");
			ts.updatePasswd(password);
			request.getRequestDispatcher("/WEB-INF/back/modifyPassword.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "密码修改失败， 请确认密码一致以及不为空！");
			request.getRequestDispatcher("/WEB-INF/back/modifyPassword.jsp").forward(request, response);
		}
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/back/modifyPassword.jsp").forward(request, response);
	}

}
