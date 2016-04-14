<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>详细表设计</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/bootstrap3.3.5/css/bootstrap.min.css">
</head>
<body>
<div id="container">
    <div class="row">
	  <div class="col-xs-12">&nbsp;</div>
	</div>
     <div class="col-md-3">
		<div class="list-group" id="menus">
		  <a href="#" class="list-group-item disabled">
		      <b><input id="back" type="button" value="返回" class="btn btn-sm btn-info"/>&nbsp;表名称</b>
		  </a>
		   <c:forEach items="${tableInfos }" var="t" varStatus="s">
               <a href="javascript:void(0);" class="list-group-item menuitem" data-linkname="${t.name }">${s.index+1 }.&nbsp;${t.name }&nbsp;${t.comment }</a>
           </c:forEach>
		</div>
     </div>
     <div class="col-md-9" >
        <c:forEach items="${tableInfos }" var="t" varStatus="s">
			<div class="panel panel-default" id="${t.name }">
				<div class="panel-heading"><b>${s.index+1 }.&nbsp;${t.name }&nbsp;${t.comment }</b></div>
				<div class="panel-body">
					<table class="table table-bordered">
						<thead  class="text-center">
							<tr class="success">
								<td><b>字段</b></td>
	                            <td><b>类型</b></td>
	                            <td><b>可为空</b></td>
	                            <td><b>主键</b></td>
	                            <td><b>自增</b></td>
	                            <td><b>描述</b></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${t.columns }" var="c">
                        <tr <c:if test="${c.primary}">class="warning"</c:if>>
                            <td style="width: 15%;text-align: center;">${c.name }</td>
                            <td style="width: 10%;text-align: center;">${c.columnType} </td>
                            <td style="width: 15%;text-align: center;"><c:if test="${c.nullable }">是</c:if><c:if test="${not c.nullable}">否</c:if> </td>
                            <td style="width: 10%;text-align: center;"><c:if test="${c.primary}">是</c:if></td>
                            <td style="width: 20%;text-align: center;">${c.extra} </td>
                            <td>${c.comment }</td>
                        </tr>
                        </c:forEach>
						</tbody>
					</table>
					<c:if test="${not empty t.indexColumns }">
						<table class="table table-bordered text-center bg-info">
						   <tr>
						   <td style="width: 15%;"><b>索引字段</b></td>
						   <td class="text-left">
						      <c:forEach items="${t.indexColumns }" var="c" varStatus="s">
						          <b>
						          ${c }
						          <c:if test="${s.index+1 < t.indexColumns.size()}">,&nbsp;</c:if>
						          </b>
						      </c:forEach>
						   </td>
						   </tr>
						</table>
					</c:if>
				</div>
			</div>
		</c:forEach>
		</div>
     <div class="row">
      <div class="col-xs-12">&nbsp;</div>
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/smartfloat.jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/postform.jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/inviewport.jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$(".list-group-item").on("click", function() {
			var id = $(this).attr("data-linkname");
			if(id) {
			    document.getElementById(id).scrollIntoView();
			}
		});
		$("#back").on("click", function() {
			$.postForm("<%=basePath %>db/configConn",{host:'${dBConnInfo.host}',port:'${dBConnInfo.port}',username:'${dBConnInfo.username}', password:'${dBConnInfo.password}'});
		});
		var s = $("#menus").offset().top + $("#menus").height() - $(window).height();
		var top = $("#menus").offset().top;
		$(window).scroll(function() {
			var scollTop = $(this).scrollTop();
			console.info("scollTop:" + scollTop + ",s:" + s);
			if(scollTop > s) {
				$("#menus").css({position:"fixed",top: -($("#menus").height() - $(window).height()) - top});
			} else {
				$("#menus").css({position:"fixed",top: -(scollTop - top)});
			}
			
			var aId = $('.panel:in-viewport').attr("id");
			if(aId) {
				$("#menus [data-linkname]").removeClass("active");
				$("#menus [data-linkname='"+aId+"']").addClass("active");
			}
		});
	});
</script>
</body>
</html>
