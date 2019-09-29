<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 修改密码</title>
	<meta name="decorator" content="blank"/>
	<style type="text/css">
      html,body,table{background-color:#f5f5f5;width:100%;text-align:center;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
    </style>
	<script type="text/javascript">
	$(document).ready(function() {
		
		//校验密码复杂度
		$('#password').blur(function(){
			var reg = /^(?![a-zA-Z]+$)(?=.*[a-zA-Z]+).{8,50}$/;
			if($(this).val() ==''){
				alert('请输入密码');
				return false;
			}else if(!reg.test($(this).val())){
				alert('密码至少包含数字和字母');
				return false;
			}else{
				return true;
			}
		})				
		$("#confirmNewPassword").blur(function(){		
			if($('#password').val() != $('#confirmNewPassword').val()){
				alert("确认密码请和密码一致");
				return false;
			}
		})
	});

	</script>
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>
	
	<h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
	<h1 class="form-signin-heading">请修改密码</h1>
	<form id="inputForm" class="form-signin" modelAttribute="user" action="${ctx}/sys/user/updatePwd" method="post">
		
		<input type="hidden" id="id" name="id" class="input-block-level required" value="${requestScope.user.id}">
		<input type="hidden" id="loginName" name="loginName" class="input-block-level required" value="${requestScope.user.loginName}">
		
		<label class="input-label" for="newPassword">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required" value="">
		<label class="input-label" for="password">确认密码</label>
		<input type="password" id="confirmNewPassword" name="confirmNewPassword" class="input-block-level required" value="">
		<input class="btn btn-large btn-primary" type="submit" value="保 存"/>&nbsp;&nbsp;	
	</form>
	<div class="footer">
		Copyright &copy; ${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} ${fns:getConfig('version')}
	</div>
	<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
</body>
</html>