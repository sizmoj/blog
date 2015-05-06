package com.xizhimojie.blog.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter  implements Filter{
	
	 private FilterConfig config;
	 private String encoding;

	    public void destroy() {
	         
	    }
	    //主要做过滤工作的方法
	    //filterchain用不高于调用过滤器链中的下一个过滤器
	    public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {
	         //获取Filter的初始化参数的值
	        //String encoding = config.getInitParameter("encoding");
	        //System.err.println(encoding);
	        if (encoding != null && !"".equals(encoding)) {
	            //设置情节数据的编码方式
	            request.setCharacterEncoding(encoding);
	        }
	        //把请求和响应对象传给过滤链中的下一个要调用的过滤器或Servlet
	        chain.doFilter(request, response);

	    }

	    //Filter初始化是的回调方法
	    public void init(FilterConfig filterConfig) throws ServletException {
	         this.config = filterConfig;
	         this.encoding = filterConfig.getInitParameter("encoding");  

	    }
}
