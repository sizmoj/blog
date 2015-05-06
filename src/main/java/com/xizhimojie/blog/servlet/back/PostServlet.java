package com.xizhimojie.blog.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.Post;
import com.xizhimojie.blog.service.PostService;
import com.xizhimojie.common.web.Page;
@WebServlet(urlPatterns={"/back/postList"})
public class PostServlet extends HttpServlet{

	private static final long serialVersionUID = -2382749909982883326L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		int pageNum;
		pageNum = request.getParameter("page") == null ?  
				1 :  Integer.parseInt(request.getParameter("page"));
		PostService ps = new PostService();
		Page<Post> page = ps.getPostList(pageNum, 10, null, null);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/back/postList.jsp").forward(request, response);
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
	}
}
