<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page  import="com.xizhimojie.common.web.Constants" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%-- <header>
	<div id="header">
		<h1>
			<a title="细枝末节" class="" href="${pageContext.request.contextPath}/index">细枝末节</a>
		</h1>
	</div>
	<nav>
		<span><a title="Archive" href="${ctx}/archive"><i class="fa fa-list-ul"></i></a></span>
		<span><a title="Tags" href="${ctx}/tags"><i class="fa fa-tags"></i></a></span> 
		<span><a title="About" href="${ctx}/about"><i class="fa fa-user"></i></a></span>
		<span><a title="Blogroll" href="${ctx}/links"><i class="fa fa-link"></i></a></span> 
		<!-- <span><a title="Subscribe" href=""><i class="fa fa-rss"></i></a></span> -->
		<span><a title="index" href="${pageContext.request.contextPath}/index"><i class="fa fa-film"></i></a></span>
	</nav>
</header> --%>
<header class="site-header">
    <a href="${pageContext.request.contextPath}/index" alt="<%=Constants.sitename %>" class="name"><%=Constants.sitename %></a>
    <nav class="site-nav">
    	<a href="${ctx}/projects" alt="Projects">Projects</a>
		<a href="${ctx}/archive" alt="Archive">Archive</a>
        <%-- <a href="${ctx}/links" alt="Links">Links</a> --%>
        <a href="${ctx}/about" alt="About">About Me</a>

    </nav>
</header>
