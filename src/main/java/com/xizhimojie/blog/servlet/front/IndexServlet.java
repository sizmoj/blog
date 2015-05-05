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
@WebServlet(urlPatterns={"/index"})
public class IndexServlet extends HttpServlet{
	private static final long serialVersionUID = 304702340660001607L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

		
		PostService ps = new PostService();
		TagService ts = new TagService();
		Post newestPost = ps.getNewestPost();
		List<Post> newestPosts = ps.getNewestPostForTen();
		List<Tag> tags = ts.getTagByPostId(newestPost.getId());
		MarkdownProcessor markup = new MarkdownProcessor();
		newestPost.setContent(markup.markdown(newestPost.getContent()));
		String lengthFlag = "0";
		if(newestPost.getContent().length() > 500) {
			lengthFlag = "1";
			newestPost.setContent(
					StringUtils.abbreviate(newestPost.getContent(), 500));
		}
		request.setAttribute("newestPost", newestPost);
		request.setAttribute("newestPosts", newestPosts);
		request.setAttribute("lengthFlag", lengthFlag);
		request.setAttribute("tags", tags);
		request.getRequestDispatcher("/WEB-INF/front/index.jsp").forward(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
	}
	
}
