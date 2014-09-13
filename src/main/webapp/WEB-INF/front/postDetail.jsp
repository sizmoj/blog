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
<title>${post.postTitle}</title>
<%@ include file="../front/layout/header.jsp"%>
</head>

<body>
	<div id="main" role="main">
		<%@ include file="../front/layout/navigation.jsp"%>
		<div id="content">
			<article>
				<section class="title">
					<h2>
						<a href="${ctx}/post/${post.url}" title="photography">${post.postTitle}</a>
					</h2>
					${post.postTitle}
				</section>
				<section class="meta">
					<span class="time"> <time
							datetime="<fmt:formatDate value="${post.publicDate}" pattern="yyyy-MM-dd"/>">
							<fmt:formatDate value="${post.publicDate}" pattern="yyyy-MM-dd" />
						</time>
					</span> <span class="tags"> <c:forEach items="${tags}" var="tag">
							<a href="${ctx}/tag/${tag.name}" title="photography">#${tag.name}</a>
						</c:forEach>
					</span>
				</section>

				<section class="post">${post.content }</section>
				<div class="divider">
					<c:choose>
						<c:when test="${!empty newerPost.url}">
							<span> <a href="${ctx}/post/${newerPost.url}"> <i
									class="fa fa-chevron-left"></i>
							</a>
							</span>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${!empty olderPost.url}">
							<span> <a href="${ctx}/post/${olderPost.url}"> <i
									class="fa fa-chevron-right"></i>
							</a>
							</span>
						</c:when>
					</c:choose>

				</div>
				<!-- 多说评论框 start -->
				<div class="ds-thread" data-thread-key="${post.url}"
					data-title="${post.postTitle}" data-url="${ctx}/post/${post.url}"></div>
				<!-- 多说评论框 end -->
				<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
				<script type="text/javascript">
					var duoshuoQuery = {
						short_name : "xizhimojie"
					};
					(function() {
						var ds = document.createElement('script');
						ds.type = 'text/javascript';
						ds.async = true;
						ds.src = (document.location.protocol == 'https:' ? 'https:'
								: 'http:')
								+ '//static.duoshuo.com/embed.js';
						ds.charset = 'UTF-8';
						(document.getElementsByTagName('head')[0] || document
								.getElementsByTagName('body')[0])
								.appendChild(ds);
					})();
				</script>
			</article>
		</div>
		<!-- 多说公共JS代码 end -->
		<%@ include file="../front/layout/footer.jsp"%>
	</div>
	<!-- main -->
</body>

</html>
