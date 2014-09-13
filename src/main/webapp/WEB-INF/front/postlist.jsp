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
<title>archive</title>
<%@ include file="../front/layout/header.jsp"%>
</head>

<body>
	<div id="main" role="main">
		<%@ include file="../front/layout/navigation.jsp"%>
		<div id="content">
			<article>
				<section class="title">
					<h2>archive</h2>
				</section>
				<section class="post">
					<ul class="listing">
						<c:forEach items="${page.list}" var="postDetail" >
						<li class="listing-item">
							<time datetime="<fmt:formatDate value="${postDetail.publicDate}" pattern="yyyy-MM-dd"/>">
								<fmt:formatDate value="${postDetail.publicDate}" pattern="yyyy-MM-dd"/>
							</time>
							<a href="${ctx}/post/${postDetail.url}"
							title="${postDetail.postTitle }">${postDetail.postTitle }</a></li>
						</c:forEach>
					</ul>
				</section>
			</article>
		</div>
		<%@ include file="../front/layout/footer.jsp"%>
	</div>
	<!-- main -->
</body>

</html>
