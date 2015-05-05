package com.xizhimojie.blog.servlet.front;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.xizhimojie.blog.domain.Post;
import com.xizhimojie.blog.domain.Term;
import com.xizhimojie.blog.service.PostService;
import com.xizhimojie.blog.service.TermService;
import com.xizhimojie.common.web.Page;

@WebServlet(urlPatterns = { "/term/*" })
public class TermForItemServlet extends HttpServlet {

	private static final long serialVersionUID = -1331072431317796035L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

		TermService ts = new TermService();
		PostService ps = new PostService();
		String termName = URLDecoder.decode(get(request.getRequestURL().toString()), "UTF-8");
		Term term = ts.getTerm(termName);
		if(term == null) {
			request.getRequestDispatcher("/WEB-INF/front/404.jsp").forward(request, response);
			return;
		}
		String termId = term.getId().toString();
		Page<Post> page = ps.getPostList(1, 10000000, null, termId);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/front/postlist.jsp").forward(
				request, response);
		return;
	}

	private static String get(String url) {
		 String[] temps = StringUtils.split(url,"/");
		return temps[temps.length - 1];
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
