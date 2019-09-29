<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统-微信公众号管理</title>

<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="error-sub">
			<div class="sorry">
				<img src="${ctxPath}/resources/img/sorry.png">
			</div>
			<div class="oops">对不起，您未选择默认公众号，请选择！</div>
			<div id="errorcm" class="calm"><h4><span id="jumpTo">5</span>秒后自动跳转到<a class="insertwxcitem" href="${ctxPath}/wxmanage/init">公众号管理</a>页面</h4><br/></div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
		$(function() {
			//alert("ss");
			countDown(5,'${ctxPath}/wxmanage/init');
		});
		function countDown(secs,surl){     
			 //alert(surl);     
			 var jumpTo = document.getElementById('jumpTo');
			 jumpTo.innerHTML=secs;  
			 if(--secs>0){     
			     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
			     }     
			 else{       
			     location.href=surl;     
			     }     
			 } 
		</script>
</body>
</html>