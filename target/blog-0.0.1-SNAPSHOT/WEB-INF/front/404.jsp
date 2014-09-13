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
<title>about</title>
<%@ include file="../front/layout/header.jsp"%>
</head>

<body>
	<div id="main" role="main">
		<%@ include file="../front/layout/navigation.jsp"%>
		<div id="content">
			<article>
				<section class="title">
					<h2>404</h2>
				</section>

				<section class="post">
					<p>Nothing found :(</p>

					<p>
						But you still can have a <a href="${pageContext.request.contextPath}/index">look around</a>
					</p>
				</section>
			</article>
		</div>
		<%@ include file="../front/layout/footer.jsp"%>
	</div>
	<!-- main -->
</body>

</html>
