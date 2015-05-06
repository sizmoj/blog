package com.xizhimojie.blog.servlet.front;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhimojie.blog.domain.Post;
import com.xizhimojie.blog.service.PostService;

@WebServlet(urlPatterns={"/archive"})
public class ArchiveServlet extends HttpServlet {

	private static final long serialVersionUID = 1937837763232577175L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

		PostService ps = new PostService();
		LinkedHashMap<Integer, List<Post>> posts = ps.getArchivePost();
		request.setAttribute("posts", posts);
		request.getRequestDispatcher("/WEB-INF/front/archive.jsp").forward(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
	}
}
