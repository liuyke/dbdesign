<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>配置MYSQL数据库连接信息</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/bootstrap3.3.5/css/bootstrap.min.css">
</head>
<body>
	<div id="container" class="col-sm-offset-3 col-sm-6 ">
		<div class="panel panel-primary">
		  <div class="panel-heading">数据库连接配置</div>
		  <div class="panel-body bg-warning">
			<form class="form-horizontal" method="post">
				<div class="form-group">
					<label class="col-sm-3 control-label" for="host">主机/IP:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="${dBConnInfo.host }" id="host" name="host" placeholder="请输入主机/IP">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="port">端口:</label>
					<div class="col-sm-6">
						<input type="number" maxlength="5" value="${dBConnInfo.port }" class="form-control" id="port" name="port" placeholder="请输入端口">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="username">用户名:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="${dBConnInfo.username }" id="username" name="username" placeholder="请输入用户名">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="password">密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" value="${dBConnInfo.password }" id="password" name="password" placeholder="请输入密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-3">
						<button type="submit" class="btn btn-primary">下一步</button>
					</div>
					<div class="col-sm-3 text-right">
						<button type="reset" class="btn btn-default">重置</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>resources/js/jquery.min.js"></script>
    <script type="text/javascript">
    $(function() {
    	centerMiddle();
    	function centerMiddle() {
    		var pHeight = $(".panel").height();
            var wHeight = $(window).height();
            $(".panel").css("marginTop",(wHeight - pHeight)/3 + "px");
    	}
    	$(window).on("resize", centerMiddle);
    });
    </script>
</body>
</html>