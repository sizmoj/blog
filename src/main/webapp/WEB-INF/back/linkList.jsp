<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>友情连接管理</title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">
<!-- Timeline CSS -->
<link href="${pageContext.request.contextPath}/css/plugins/timeline.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css"
	rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="${pageContext.request.contextPath}/css/plugins/morris.css"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		$("#add").click(function(){
			window.location = "${ctx}/back/addLink";
		});
	});
</script>
</head>

<body>
	<div id="wrapper">
		<%@ include file="../back/layout/head.jsp"%>
		<div id="page-wrapper">	
		
			<div class="panel-heading">
				<button id="add" type="button" class="btn btn-default">添加友情连接</button>
			</div>
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th>ID</th>
								<th>name</th>
								<th>url</th>
								<th>Operatoin</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${links}" var="link">
							<tr class="odd gradeX">
								<td>${link.id}</td>
								<td>${link.name}</td>
								<td>${link.url}</td>
								<th>
									<a href="${ctx}/back/deleteLink?id=${link.id}">删除</a>
								</th>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- /.panel-body -->
	</div>
	<!-- /.panel -->



	<!-- /#wrapper -->

	<!-- jQuery Version 1.11.0 -->


	<!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>

	<!-- DataTables JavaScript -->
	<script
		src="${pageContext.request.contextPath}/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath}js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="${pageContext.request.contextPath}/js/sb-admin-2.js"></script>

</body>

</html>
