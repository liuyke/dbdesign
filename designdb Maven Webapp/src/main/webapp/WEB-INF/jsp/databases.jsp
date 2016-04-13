<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>选择数据库</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/bootstrap3.3.5/css/bootstrap.min.css">
</head>
<body>
	<div id="container" class="col-sm-offset-3 col-sm-6 ">
		<div class="panel panel-primary">
			<div class="panel-heading"><button type="button" class="btn btn-sm btn-info" onclick="window.location='configConn'">返回</button>&nbsp;&nbsp;选择数据库</div>
			<div class="panel-body bg-warning">
				<c:forEach items="${databases }" var="d">
					<h5>
						<a href="<%=basePath %>db/${d}/showtables">${d }</a>
					</h5>
				</c:forEach>
			</div>
		</div>
	</div>
<script type="text/javascript" src="<%=basePath%>resources/js/jquery.min.js"></script>
 <script type="text/javascript">
 $(function() {
     centerMiddle();
     function centerMiddle() {
         $(".panel").css("marginTop","1rem");
     }
     $(window).on("resize", centerMiddle);
 });
 </script>
</body>
</html>
