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
				<span class="step-icon">活动创建</span>
				<span class="step-icon step-on">模板选择</span>
				<span class="step-icon">参数设置</span>
				<span class="step-icon step-icon-last">活动发布</span>
			</div>
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
			<div class="templetBox">
				<ul>
					<li>
						<img src="resources/img/images/1.png">
						<div class="qr-code" style="display: none;">
							<div class="qr-code-img"><img src="${ctxPath}/resources/img/qr-code.png"></div>
							<a class="choose-btn" href="">选择</a>
						</div>
					</li>
					<li>
						<img src="resources/img/images/2.png">
						<div class="qr-code" style="display: none;">
							<div class="qr-code-img"><img src="${ctxPath}/resources/img/qr-code.png"></div>
							<a class="choose-btn" href="">选择</a>
						</div>
					</li>
					<li>
						<img src="resources/img/images/1.png">
						<div class="qr-code" style="display: none;">
							<div class="qr-code-img"><img src="${ctxPath}/resources/img/qr-code.png"></div>
							<a class="choose-btn" href="">选择</a>
						</div>
					</li>
					<li>
						<img src="resources/img/images/2.png">
						<div class="qr-code" style="display: none;">
							<div class="qr-code-img"><img src="${ctxPath}/resources/img/qr-code.png"></div>
							<a class="choose-btn" href="">选择</a>
						</div>
					</li>
				</ul>
			</div>			
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
			$(document).ready(function() {
				check();
				$('#eform').validate({
					//debug: true,
					rules : {
						gamename : {
							required : true,
							isRightfulString:true,
							maxlength : 120
						},
						appid : {
							required : true
						},
						tempid : {
							required : true
						}
					}
				});
				$("input[name='channel']").change(function() {
					var channel = $("input[name='channel']:checked").val();
					if ("1" == channel) {
						$("#appid").prop("disabled",true);
						$("#wechatid").hide();
					} else {
						$("#appid").prop("disabled",false); 	
						$("#wechatid").show();
					}
				});
				
				//判断活动渠道是否是应用
				function check(){
					if("${showGameModel.channel}"==1){
						$("#wechatid").hide();
						$("#appid").prop("disabled",true);
					}
				}
				//显示游戏二维码
				$(".templetBox li").mouseover(function(){
					$(this).find(".qr-code").show();
				});
				$(".templetBox li").mouseout(function(){
					$(this).find(".qr-code").hide();
				});
			});
		</script>
</body>
</html>