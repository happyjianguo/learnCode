<%@ page language="java" pageEncoding="GB2312"%>
<%@ page import="com.pay.util.ParamUtils"%>
<%@page import="java.util.Random"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
	String path = request.getContextPath();
	String usercode = ParamUtils.getStringAttribute(request, "usercode");
	String firstLogin = ParamUtils.getStringAttribute(request,"firstLogin");
	Random random = new Random();
	String src = path+"/captcha.do?ran="+random.nextFloat();
%>
<bean:define id="de_path" value="<%=path%>"></bean:define>
<bean:define id="de_usercode" value="<%=usercode%>"></bean:define>
<bean:define id="de_firstLogin" value="<%=firstLogin%>"></bean:define>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/styles/login.css" />
<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
<title>管理控制台---登录页面</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />



<script type="text/javascript">
	var usercode = "";
	var username = "";
	var teamno = "";
	var teamname = "";
	var currentDate = "";
	var commpath = "";

	function loginSubmit() {
	
		var usercode = document.loginForm.usercode.value;
		var passwd = document.loginForm.passwd.value;
		var captcha = document.loginForm.captcha.value;
		
		if (usercode == "") {
			alert("请输入管理员名！");
			document.loginForm.usercode.focus();
			return false;
		}
		
		if (passwd == "") {
			alert("请输入密码！");
			document.loginForm.passwd.focus();
			return false;
		} else if (passwd.length < 6) {
			alert("密码不足6位,请重新输入！");
			document.loginForm.passwd.focus();
			return false;
		}
		
	    if (captcha == "") {
		   alert("请输入验证码！");
		   document.loginForm.captcha.focus();
		   return false;
	    }
		var password = MD5(passwd);
		document.loginForm.passwd.value = password;
		
        <%//对密码进行MD5加密%>
	    document.getElementById("logindiv").style.display = "none";
		document.getElementById("framediv").style.display = "";
		document.getElementById("loginForm").submit();
	}

	function loginReset() {
		document.loginForm.usercode.value = "";
		document.loginForm.passwd.value = "";
		document.loginForm.usercode.focus();
		return false;
	}

	function onJudge() {
		if (window.event.keyCode == 13) {
			loginSubmit();
		}
	}
    <%//判断管理员是否是第一次登录，如果是第一次登录则，该管理员必须修改密码！%>
	function modifyPassword() {
		var path = '<bean:write name="de_path"/>';
		var usercode = '<bean:write name="de_usercode"/>';
		var firstLogin = '<bean:write name="de_firstLogin"/>';
		if (firstLogin == "yes") {
			document.loginForm.usercode.value = "";
			document.loginForm.passwd.value = "";
			var inputWin;
			if (inputWin == null || inputWin.closed) {
				inputWin = window.open(
						path + '/user.do?method=preModPassword&usercode='
								+ usercode, '管理员修改密码页面',
						'resizeable=no,width=400,height=200,left=300,top=265');
				inputWin.focus();
			} else {
				inputWin.close();
				inputWin = window.open(
						path + '/user.do?method=preModPassword&usercode='
								+ usercode, '管理员修改密码页面',
						'resizeable=no,width=400,height=260,left=300,top=265');
				inputWin.focus();
			}
		}
	}
</script>
</head>


<body onkeypress="onJudge();" scroll="no">
	<html:form styleId="loginForm" action="/checkUser.do" method="post">
		<div style="display: none;">
			<input type="text" value="" name="mac" id="mac"/>
		</div>
		<div id="mainWrapper">
			<div id="logindiv">
				<div id="main">
					<div class="login_windows">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="68" height="35" align="right"><img src="<%=path%>/images/icon_user.gif"  width="11" height="13" align="absmiddle"/>&nbsp;用户
								</td>
								<td width="200" align="left">&nbsp;<span
									id='emp_field_actorno' label='用户' type='Text' class='emp_field'
									cssErrorClass='emp_field_error'
									cssRequiredClass='emp_field_required' colSpan='1'
									readonly='false' onlyControlElement='true' value=''
									rendered='false'> <input id='usercode' name='usercode'
										value='' class='inputselect' tabindex="1" />
								</span>
								</td>
								<td rowspan="2" style="vertical-align:top;padding-top:10px">
									<a href="#" onclick="return loginSubmit();"><img
										src="<%=path%>/images/button_login.gif" width="62" height="54"
										border="0" tabindex="6"/>
								</a></td>
							</tr>
							<tr>
								<td height="35" align="right"><img
									src="<%=path%>/images/icon_password.gif" width="12" height="12"
									align="absmiddle"/>&nbsp;密码
								</td>
								<td align="left">&nbsp; <span id='emp_field_pwd' label='密码'
									type='Text' class='emp_field' cssErrorClass='emp_field_error'
									cssRequiredClass='emp_field_required' colSpan='1'
									readonly='false' onlyControlElement='true' value='123456'
									rendered='false'> <input id='passwd' name='passwd'
										value='' type='password'  class='inputselect'
										tabindex="2" />
								</span>
								</td>
								<td></td>
							</tr>
							<tr>
									<td align="right">验证码</td>
									<td align="left" width="150px" style="display: inline" >&nbsp;
									<span id="emp_field_captcha" label="验证码"
										type="Text" class="emp_field" cssErrorClass="emp_field_error"
										cssRequiredClass="emp_field_required" colSpan="1"
										readonly="false" onlyControlElement="true" value=""
										rendered="false"> 
										<input id="captcha" name="captcha" value="" type="text" class="inputselect2" tabindex="2" />
										<img src="<%=src %>" style="vertical-align: middle" id="captchaImg"/>
									</span>
									</td>
							</tr>
							<tr>
								<td colspan="2" class="errorMessage"></td>
							</tr>
						</table>


					</div>
				</div>
			</div>
		</div>
	</html:form>
	<input type="hidden" name="usercode"
		value='<bean:write name="de_usercode"/>' />
	<div style="display:none" id="framediv"
		style="position: absolute; overflow: no;   border: 0px none #000000; left: 0px; top: 0px;">
		<iframe id="loginframe" src="" width="100%" height="768px"></iframe>
	</div>
<script type="text/javascript">
	modifyPassword();
</script>	
</body>

</html:html>

