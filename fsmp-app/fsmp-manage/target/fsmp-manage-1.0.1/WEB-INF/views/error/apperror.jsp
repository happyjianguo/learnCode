<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误</title>
</head>
<body>
	<div class="main-content">
		<div class="error-sub">
			<div class="sorry">
				<img src="${ctxPath}/resources/img/sorry.png">
			</div>
			<div class="oops">哎呀，出现错误了！</div>
			<div id="errorcm" class="calm">${errorCode}:${errorMsg}</div>
			<!-- 			<div class="back-option"> ${errorCode}:${errorMsg}-->
			<!-- 				<span>您可以<a href="#">返回上一页</a>,或<a href="#">返回首页</a>搜索更多精彩内容！ -->
			<!-- 				</span> -->
			<!-- 			</div> -->
		</div>
	</div>
</body>
</html>