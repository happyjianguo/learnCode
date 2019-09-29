<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/com/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Error</title>
		<%@ include file="/WEB-INF/views/com/cssjs.jsp"%>
		<style type="text/css">
		</style>
	</head>
<body>
	<div class="container">
		<h1 class="text-center"></h1>
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			${errorCode}:${errorMsg}
		</div>
	</div>
</body>
</html>