package com.xizhimojie.blog.servlet.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.xizhimojie.blog.domain.Post;
import com.xizhimojie.blog.domain.Tag;
import com.xizhimojie.blog.service.PostService;
import com.xizhimojie.blog.service.TagService;
import com.xizhimojie.common.markdowm.MarkdownProcessor;

@WebServlet(urlPatterns = { "/post/*" })
public class FrontPostServlet extends HttpServlet {

	private static final long serialVersionUID = -1331072431317796035L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

		PostService ps = new PostService();
		TagService ts = new TagService();
		String url = get(request.getRequestURL().toString());
		Post post = ps.getPost(url);
		if(post == null) {
			request.getRequestDispatcher("/WEB-INF/front/404.jsp").forward(request, response);
			return;
		}
		post.setClickCount(post.getClickCount() + 1);
		List<Tag> tags = ts.getTagByPostId(post.getId());
		ps.updatePost(post);
		Post newerPost = ps.getNewerPost(post.getPublicDate());
		Post olderPost = ps.getOlderPost(post.getPublicDate());
		MarkdownProcessor markup = new MarkdownProcessor();
    	post.setContent(markup.markdown(post.getContent()));
		request.setAttribute("post", post);
		request.setAttribute("newerPost", newerPost);
		request.setAttribute("olderPost", olderPost);
		request.setAttribute("tags", tags);
		request.getRequestDispatcher("/WEB-INF/front/postDetail.jsp").forward(request, response);
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
