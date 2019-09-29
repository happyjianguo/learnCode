<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/views/common/css.jsp"%>
<%@ include file="/WEB-INF/views/common/js.jsp"%>

<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="mainbox">
			<div class="form-group">
				<div class="input-group input-group-lg">
				<span class="">
					哎呀，出现错误了
					${errorCode}:${errorMsg}，还剩
				</span>
				<span class="timeout">
					10
				</span> 
				<span>
					秒跳转到找回密码页面
				</span>
				<span>
				<a href="${ctxPath}/drop/init">点此直接跳转</a>
					找回密码页面
				</span>
				</div>
			</div>
			<form:form action="${ctxPath}/drop/init" method="post"
				id="eform">
			</form:form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
			$(function(){
				var wait=10;  
				timeOut();  
				function timeOut(){  
				    if(wait==0){  
				    	$("#eform").submit();
				    }else{                    
				        setTimeout(function(){  
				            wait--;  
				            $('.timeout').text(wait);  
				            timeOut();  
				        },1000)  
				    }  
				}  
			})
		</script>
</body>
</html>