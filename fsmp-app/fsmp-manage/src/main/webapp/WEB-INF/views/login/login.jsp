<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_CN" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_CN" class="ie9 no-js"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="zh_CN">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>场景化金融营销平台-后台管理</title>
<link rel="shortcut icon" type="image/x-icon" href="${ctxPath}/resources/img/favicon.ico"/>
<link rel="stylesheet" href="${ctxPath}/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctxPath}/resources/css/simple-line-icons.css" />
<link rel="stylesheet" href="${ctxPath}/resources/css/base.css">
<link rel="stylesheet" href="${ctxPath}/resources/css/login.css" />
<!--[if lt IE 9]>
		<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<style type="text/css">
	
</style>
</head>
<body>
	<div class="loginBox">
		<!-- <div class="contact">
			<span class="Phone"><i class="icon-call-end"></i>13693618929</span>
			<span class="qq"><i class="icon-social-twitter"></i>583382715</span>
			<span class="mailbox"><i class="icon-envelope-open"></i>jansh@jansh.com.cn</span>
			<span class="address"><i class="icon-pointer"></i>北京市海淀区学清路9号汇智大厦B座17层  </span>
		</div> -->
		<div class="nav">
			<div class="logo">
				<img src="${ctxPath}/resources/img/login_logo.png" class="max-w">
			</div>
			<div class="menu f-r">
				<ul>
					<li class="active"><a href="#">首页</a></li>
					<li><a href="#">产品服务</a></li>
					<li><a href="#">案例营销</a></li>
					<li><a href="#">帮助中心</a></li>
					<li><a href="#">关于我们</a></li>
				</ul>
			</div>
		</div>
		<div class="login-t">
			<img src="${ctxPath}/resources/img/login_t.jpg" class="max-w">
		</div>
		<form:form class="form-horizontal " method="post" action="${ctxPath}/logincheck" id="form" >
		<div class="login">
			<input type="hidden" id="challenge" name="challenge">
			<input type="hidden" id="validate" name="validate">
			<input type="hidden" id="seccode" name="seccode">
			<div class="title"><img src="${ctxPath}/resources/img/login_logo.png"></div>
			<div class="p-r">
				<i class="i-icon icon-user"></i>
				<input type="text" class="input user" name="username" autofocus="autofocus" placeholder="请输入您的账号"/>		
			</div>
			<div class="p-r l-password">
				<i class="i-icon icon-lock-open"></i>
				<input type="password" class="input password" name="password" placeholder="请输入您的密码"/>
				<div class="l-prompt" style="display: ${param.error == true ? '' : 'none'};padding-top:25px">${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</div>
			</div>
			<div id="popup-captcha"></div>			
			<!-- <div class="c-gray">
				<input type="checkbox" class="f-l"/>
				<span class="pl">记住账号</span>
			</div> -->
			<button type="button" class="confirm mt5" name="login" id="login">登录</button>		
			<div class="l-b">
				<span class="f-l"><a href="${ctxPath}/drop/init" class="c-gray">忘记密码？</a></span>
				<span class="f-r"><a href="${ctxPath}/register/init" class="c-gray">立即注册</a></span>
			</div>			
		</div>
		</form:form>
	</div>
	<%@ include file="/WEB-INF/views/common/js.jsp"%>
	
	<!-- 引入封装了failback的接口--initGeetest -->
	<script src="http://static.geetest.com/static/tools/gt.js"></script>
	<script type="text/javascript">
	//修改表单验证，提交
		$(document).ready(function() {
			$('#form').validate({
				onsubmit: false,
				//debug: true,
				rules : {
					username : {
						notnull:true,
						required : true
					},
					password : {
						notnull:true,
						required : true
					},
					otppwd : {
						notnull:true,
						required : true
					},
				}
			}); 
			
			var handlerPopup = function (captchaObj) {
		        $("#login").click(function () {
		        	
		        	var name = $("input[name='username']").val(); 
		        	var pwd = $("input[name='password']").val();
		        	if( name == null || pwd == null || name == '' || pwd == ''){
		        		return ;
		        	}
		        	
		            var validate = captchaObj.getValidate();
		            if (!validate) {
		                alert('请先完成验证！');
		                return;
		            }
		            $("#challenge").val(validate.geetest_challenge);
		            $("#validate").val(validate.geetest_validate);
		            $("#seccode").val(validate.geetest_seccode);
		            $("#form").submit();
		        });
				
        		 // 弹出式需要绑定触发验证码弹出按钮
		        captchaObj.bindOn("#login");

		        // 将验证码加到id为captcha的元素里
		        captchaObj.appendTo("#popup-captcha");

		    };
		    
			var data =eval("(${resStr})");
			 // 使用initGeetest接口
	        // 参数1：配置参数
	        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
	        initGeetest({
	            gt: data.gt,
	            challenge: data.challenge,
	            product: "popup", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
	            offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
	        }, handlerPopup);
		});
		$("#form input").keypress(function(e) {
			var thisinput = this;
	        if (e.which == 13) {
	        	var inputs = $("#form input").not(":hidden");
	        	var nextflag = false;
	        	inputs.each(function(i, n){
	        		//光标切换至下一个input
	        		if(nextflag){
	        			n.focus();
	        			return false;
	        		}
	        		//查找当前元素
	        		if($(this).is(thisinput)){
	        			//提交form
		        		if(i===inputs.length-1){
		        			$("#login").click();
		        			return false;
		        		}
	        			nextflag = true;
	        		}
	        	});
	            return false;
	        }
	    });
		//设置表单验证默认值
		$.validator.setDefaults({
			errorElement: 'span', 
	        errorClass: 'l-prompt',      
		});
	</script>
</body>
</html>
