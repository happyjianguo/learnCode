<%@ page contentType="text/html; charset=GBK" import="java.util.Date,java.text.SimpleDateFormat,
java.util.List
"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>收单系统管理平台</title>
<style type="text/css">
body {
	MARGIN: 0px;
	background-image: url();
}
.Copyright {
	font-size: 12px;
	color: #F65432;
	padding-top: 50px;
}
.login_text {font-size: 15px}
</style>
<style type="text/css">
.username{
	padding-left:30px;
	background:url(images/login_1.png) 4px  no-repeat;
	background-color:#FFFFFF;
	width:155px;
	height:25px;
	font-weight:bold; 
	line-height:25px;
	border:1px solid #ccc;
	}
.password{
	padding-left:30px;
	background:url(images/login_2.png) 4px  no-repeat;
	background-color:#FFFFFF;
	width:155px;
	height:25px;
	line-height:25px;
	border:1px solid #ccc;
	}
.login_title{
	font-size:22px; 
	font-weight:bold; 
	color:#F65432;
	text-align: left;
	font-family: 微软雅黑;
}
.login_title1{
	font-size:18px; 
	font-weight:bold; 
	color:#F65432;
	text-align: center;
	font-family: 微软雅黑;
}
.shuaxin a{
	font-family: 微软雅黑;
	font-size: 5px;
	color:#cccccc;
	text-decoration: none;
}
input{
	border:0;
	font-size:18px;	
	}

</style>
<SCRIPT language=JavaScript type="" src="images/check.js"></SCRIPT>
<SCRIPT language=JavaScript src="images/data.js"></SCRIPT>
<SCRIPT language=JavaScript>
<% if (session.getAttribute("error")!=null){%>
	alert('<%=session.getAttribute("error") %>');
<% }%>
function checkdata(thisForm) {
if ("" == jtrim(form1.userId.value))
    {
		alert("用户名称不能为空，请重新输入！");
		form1.userId.focus();
		return false;
    }
	if ("" == jtrim(form1.password.value ))
	{
		alert("用户密码不能为空，请重新输入！");
		form1.password.focus();
		return false;
	}
	if ("" == jtrim(form1.token.value ))
	{
		alert("请输入口令！");
		form1.token.focus();
		return false;
	}
}
</SCRIPT>
<META content="text/html; charset=GBK" http-equiv=Content-Type>
<BODY onload=form1.userId.focus()>
<img src="images/login_bg.jpg" width="100%" height="100%" style="z-index:-100;position:absolute;left:0;top:0">
<FORM method=post name=form1 action="UserLogin.do?method=login">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="200">&nbsp;</td>
  </tr>
</table>
<table width="543" height="255" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td background="images/login_kuang.png">
    <table width="320" border="0" align="center" cellpadding="0" cellspacing="15">
    <tr>
        <td width="100%" colspan="2">
<%--        	<div class="login_title">中石化裕福支付</div>--%>
        	<div class="login_title1">收单系统管理平台</div>
        </td>
      </tr>
      <tr>
        <td width="25%"><span class="login_text">用户名：</span></td>
        <td width="75%"><div class="username">
          <input name="userId" type="text" size="14"  style="height:22px;width:153px;outline:none; vertical-align:middle;" />
        </div></td>
      </tr>
      <tr>
        <td width="25%"><span class="login_text">密　码：</span></td>
        <td width="75%"><div class="password">
          <input name="password" type="password" size="14" style="height:22px;width:153px;outline:none;vertical-align:middle;"/>
        </div></td>
      </tr>
<!--       <tr> -->
<!--         <td><span class="STYLE2">口　令：</span></td> -->
<!--         <td><input name="token" type="text" id="token" size="10" style="border:1px solid #ccc;"/></td> -->
<!--       </tr> -->
      <tr>
        <td colspan="2">
	        <div align="center" class="shuaxin">
	        	<input type="hidden" name=clientId value="HX"/>
	        	<input src="images/login.jpg" width="81" height="26" type=image onclick="return checkdata(this);" />
	        	<!-- 
	        	<img src="images/b_1.gif" width="81" height="26" />
	        	<INPUT src="images/b_1.gif" type=image name=Submit/>
	        	&nbsp;&nbsp;&nbsp;&nbsp;
	        	<a href="javascript:document.forms[0].reset();"><img src="images/b_2.gif" width="65" height="20"  border="0"/></a>
	        	 -->
	        </div>
        </td>
       </tr>
    </table></td>
  </tr>
</table>
<div align="center" class="Copyright">Copyright&copy;2014 裕福支付 版权所有</div>
</FORM>
</body>
</html>
