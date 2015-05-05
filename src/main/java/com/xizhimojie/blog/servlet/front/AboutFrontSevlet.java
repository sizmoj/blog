package com.xizhimojie.blog.servlet.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.About;
import com.xizhimojie.blog.service.AboutService;
import com.xizhimojie.common.markdowm.MarkdownProcessor;


@WebServlet(urlPatterns={"/about"})
public class AboutFrontSevlet extends HttpServlet{
	
	private static final long serialVersionUID = 4629581617168678614L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

		AboutService as = new AboutService();
		About about = as.getAbout();
		MarkdownProcessor markup = new MarkdownProcessor();
		about.setContent(markup.markdown(about.getContent()));
		request.setAttribute("about", about);
		request.getRequestDispatcher("/WEB-INF/front/about.jsp").forward(request, response);
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
	}

}
