<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<div class="main-content">
			<div class="mainbox">
				<div class="step">
					<span class="step-icon">活动创建</span>
					<span class="step-icon step-on">模板选择</span>
					<span class="step-icon">参数设置</span>
					<span class="step-icon step-icon-last">活动发布</span>
				</div>
				<!--  模板分类-->
				<div class="templet-menu">
					<ul>
						<li>
							<em>分类模板：</em>
							<a href="" class="menu-on">全部</a>
							<a href="">大转盘</a>
							<a href="">刮刮卡</a>
							<a href="">猜金币</a>
							<a href="">滑雪大冒险</a>
						</li>
						<li>
							<em>节日模板：</em>
							<a href="" class="menu-on">全部</a>
							<a href="">情人节</a>
							<a href="">端午节</a>
							<a href="">中秋节</span>
							<a href="">春节</a>
						</li>
					</ul>
				</div>
				<!--  模板分类  end-->
			<div class="templetBox">
				<ul>
					<c:forEach items="${gameInfoModel.gameTemps}" var="temp">
						<li>
							<input value="${temp.templatename}">
							<%-- <img src="${temp.pictrueurl}"> --%>
							<img src="${ctxPath}/resources/img/1.png">
							<input type="hidden" name="vardeploy" value="${gameInfoModel.gameURI}${temp.deploypath}">
							<input type="hidden" name= "varid" value="${temp.tempid}">
							<input type="hidden" name= "vartype" value="${temp.gameid}">
							<input type="hidden" name= "varname" value="${temp.templatename}">
							<div class="qr-code" style="display: none;">
								<div class="qr-code-img"><img src="${ctxPath}/resources/img/qr-code.png"></div>
								<button class="choose-btn" name="varselect" >选择</button>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		<form:form id="eform" method="post" class="form-horizontal">
			<input type="hidden"  id="gameid" name= "gameid" value="${gameInfoModel.gameid}">
			<input type="hidden" id="tempid" name= "tempid" >
			<input type="hidden" id="wxappid" name= "wxappid" value="${gameInfoModel.appid}">
			<input type="hidden" id="userid" name= "userid" value="${gameInfoModel.userid}">
			<input type="hidden" id="gametity" name= "gametity">
			<input type="hidden" id="gametype" name= "gametype">
			<input type="hidden" id="channel" name= "channel" value="${gameInfoModel.channel}">
		</form:form>
	</div>

	</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
				$(function(){
					$("button[name='varselect']").click(function(){
						var parent = $(this).parents("li");
						var vardeploy = $(parent).find("input[name='vardeploy']").val();
						var varid = $(parent).find("input[name='varid']").val();
						var varname =$(parent).find("input[name='varname']").val();
						var gametype = $(parent).find("input[name='vartype']").val();
						$("#tempid").val(varid);
						$("#gametity").val(varname);
						$("#gametype").val(gametype);
						$("#eform").attr("action",vardeploy);
						$("#eform").submit();
					});
					//显示游戏二维码
					$(".templetBox li").mouseover(function(){
						$(this).find(".qr-code").show();
					});
					$(".templetBox li").mouseout(function(){
						$(this).find(".qr-code").hide();
					});
				})
		</script>
</body>
</html>