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
			<article>
				<section class="title">
					<h2>catagory</h2>
				</section>
				<section class="post">
					<div id='tag_cloud'>
						<c:forEach items="${terms}" var="term">
						<a href="${ctx}/term/${term.name}" title="${term.name}" rel="5">${term.name}</a>
						</c:forEach>
					</div>
				</section>
			</article>
			<article>
				<section class="title">
					<h2>Tags</h2>
				</section>
				<section class="post">
					<div id='tag_cloud'>
						<div id='tag_cloud'>
						<c:forEach items="${tags}" var="tag">
						<a href="${ctx}/tag/${tag.name}" title="${tag.name}" rel="5">${tag.name}</a>
						</c:forEach>
					</div>
					</div>
				</section>
			</article>


		</div>
		<%@ include file="../front/layout/footer.jsp"%>
	</div>
	<!-- main -->
</body>

</html>
