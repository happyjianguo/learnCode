<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/com/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<title><fmt:message key="wechat.ebank" />
		</title>
		<%@ include file="/WEB-INF/views/com/cssjs.jsp"%>
	</head>
	<body>
		<div class="alert alert-info header" style="border : 0px;" align="center" role="alert">
			<img class="header_title" style="center:center;" src="<%=request.getContextPath()%>/resources/img/header_title_jscard.png">
    	</div>
		<div class="container">
    			<div  class="bindstatus">
    				<img src="<%=request.getContextPath()%>/resources/img/bindsuccess.png">
    				<span class="glyphicon" style="margin-left:-15px;"><fmt:message key="bindOk"></fmt:message></span>
    			</div>
		</div>
	</body>
</html>