<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.recharge.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<div class="main-content">
	<div class="list-nav">
		<ol class="breadcrumb">
			<li><fmt:message key="recharge.recharge.recharge"/></li>
			<li class="active" id="bill_check"><fmt:message key="recharge.recharge.dbrecharge"/></li>
		</ol>
	</div>
	<div class="mainbox">
		<div class="tablebox">
			<div class="promptbox">
				<div class="prompt_tit">
					<i class="fa fa-check"></i>恭喜您信息添加成功
				</div>
				<div class="prompt_body">
					您的用户信息添加成功，请返回用户页面查看详情
				</div>
				<div class="prompt_foot">
					<input type="button" value="立即返回" class="btn btn-primary prompt_btn"> 
				</div>
			</div>
		</div> 
	</div>		
</div>	
</body>
</html>