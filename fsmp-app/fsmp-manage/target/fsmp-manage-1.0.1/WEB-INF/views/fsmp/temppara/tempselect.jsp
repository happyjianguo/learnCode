<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>模板选择</title>
</head>
<body>
	<div class="main-content">
		<div class="mainbox">
			<form:form id="mobanform"  modelAttribute="gameInfoModel" action="${ctxPath}/actrelease/init" methodParam="post">
			 <input type="hidden" name="gameid" id="gameid" value="${gameInfoModel.gameid}">
			 <input type="hidden" name="appid" id="appid" value="${gameInfoModel.appid}">
			 <input type="hidden" name="gamename" id="gamename" value="${gameInfoModel.gamename}">
			  <input type="hidden" name="playname" id="playname" value="${gameInfoModel.playname}">
	     	</form:form> 
			<div>
				<c:forEach items="${gameInfoModel.templateinflist}" var="temp" varStatus="s">
					<div name="mobansel">
						<input name="tempid" type="text" value=${temp.tempid} style="display: none" />
						<a class="zhizuo"><img src="${ctxPath}/mcmanage/show?tempid=${temp.tempid}">开始制作</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
<script type="text/javascript" >
	$(document).ready(function() {
		$("a[class='zhizuo']").on("click",function(){
			
			var gamename =encodeURI($("#gamename").val());
			$(this).attr('href','${ctxPath}/mcmanage/tempmake?gameid='+$("#gameid").val()+'&tempid='+$(this).parents("div[name='mobansel']").find("input[name='tempid']").val()+'&appid='+$("#appid").val()+'&gamename='+gamename+'&playname='+$("#playname").val());
			});
		});
</script>
</body>
</html>