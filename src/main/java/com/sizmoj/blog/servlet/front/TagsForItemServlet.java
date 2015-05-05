package com.sizmoj.blog.servlet.front;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.sizmoj.blog.domain.Post;
import com.sizmoj.blog.domain.Tag;
import com.sizmoj.blog.service.PostService;
import com.sizmoj.blog.service.TagService;
import com.sizmoj.common.web.Page;

@WebServlet(urlPatterns = { "/tag/*" })
public class TagsForItemServlet extends HttpServlet {

	private static final long serialVersionUID = -1331072431317796035L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

		
		TagService ts = new TagService();
		PostService ps = new PostService();
		String tagName = URLDecoder.decode(get(request.getRequestURL().toString()), "UTF-8");
		Tag tag = ts.getTagByName(tagName);
		if(tag == null) {
			request.getRequestDispatcher("/WEB-INF/front/404.jsp").forward(request, response);
			return;
		}
		String tagId = tag.getId().toString();
		Page<Post> page = ps.getPostList(1, 10000000, tagId, null);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/front/postlist.jsp").forward(
				request, response);
		return;
	}


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	private static String get(String url) {
		String[] temps = StringUtils.split(url,"/");
		return temps[temps.length - 1];
	}
}
