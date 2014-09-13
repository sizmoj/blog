<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>细枝末节</title>
<%@ include file="../front/layout/header.jsp"%>
</head>

<body>
	<div id="main" role="main">
		<%@ include file="../front/layout/navigation.jsp"%>
		<div id="content">
			<div>
				<ul class="listing">
					<article class="content">
						<section class="title">
							<h2>
								<a href="${ctx}/post/${newestPost.url}">${newestPost.postTitle }</a>
							</h2>
						</section>
						<section class="meta">
							<span class="time"> <time
									datetime="<fmt:formatDate value="${newestPost.publicDate}" pattern="yyyy-MM-dd"/>">
									<fmt:formatDate value="${newestPost.publicDate}"
										pattern="yyyy-MM-dd" />
								</time>
							</span> 
							<span class="tags"> 
							<c:forEach items="${tags}" var="tag">
								<a href="${ctx}/tag/${tag.name}" title="photography">#${tag.name}</a>
							</c:forEach>
							</span>
						</section>
						<section class="post">
						${newestPost.content }
						<c:choose>
							<c:when test="${lengthFlag eq 1}">
								<a href="${ctx}/post/${newestPost.url}">查看全文</a>
							</c:when>
						</c:choose>	
						</section>
					</article>
				</ul>
				<div class="divider"></div>
				<ul class="listing main-listing">
					<li class="listing-seperator">Happend earlier</i> 
					<c:forEach items="${newestPosts}" var="post">
							<li class="listing-item"><time
									datetime="<fmt:formatDate value="${post.publicDate}" pattern="yyyy-MM-dd"/>">
									<fmt:formatDate value="${post.publicDate}" pattern="yyyy-MM-dd" />
								</time> <a href="${ctx}/post/${post.url}" title="${post.postTitle }">${post.postTitle }</a>
							</li>
						</c:forEach>
					<li class="listing-seperator"><a href="${ctx}/archive">Long
							long ago</a></li>
				</ul>
			</div>
		</div>
		<%@ include file="../front/layout/footer.jsp"%>
	</div>
	<!-- main -->
</body>

</html>
