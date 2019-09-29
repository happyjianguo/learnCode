<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统-营销管理</title>

<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="mainbox">
			<div class="step">
				<span class="step-icon step-on">活动创建</span>
				<span class="step-icon">模板选择</span>
				<span class="step-icon">参数设置</span>
				<span class="step-icon step-icon-last">活动发布</span>
			</div>
			<form:form id="eform" action="${ctxPath}/mcmanage/gameinit" method="post" modelAttribute="showGameModel"
				class="form-horizontal">
				
					<input id="gameid" name="gameid" value="${showGameModel.gameid}" type="hidden">
					<input id="appid" name="appid" value="${showGameModel.wechat}" type="hidden">
					<input id="channel" name="channel" type="hidden">
					
					<div class="choose-on">
						<ul>
							<li>
								<div class="img">
									<img src="${ctxPath}/resources/img/1.png">
								</div>
								<div class="choose-tit">微信渠道</div>
								<button class="choose-btn" id="wechatway" >选择</button>
							</li>
							<li>
								<div class="img">
									<img src="${ctxPath}/resources/img/2.png">
								</div>
								<div class="choose-tit">APP渠道</div>
								<button class="choose-btn" id="appway">选择</button>
							</li>
						</ul>
					</div>
			</form:form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
			$(document).ready(function() {
				
				$("#wechatway").click(function(){
					$("#channel").val("0");
					$("#eform").submit();
				});
				
				$("#appway").click(function(){
					$("#channel").val("1");
					$("#eform").submit();
				});
				
			});
		</script>
</body>
</html>