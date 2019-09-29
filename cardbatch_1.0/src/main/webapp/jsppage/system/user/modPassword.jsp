<%@ page language="java" pageEncoding="gbk" import="com.pay.util.ParamUtils"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
	String path = request.getContextPath();
	String usercode = ParamUtils.getStringAttribute(request,"usercode");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>管理员修改密码页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
</head>
<script language="javascript">
	var usercode = "<%=(String)session.getAttribute("usercode")%>";
	if (usercode == "null") {
		window.opener.top.document.getElementById("loginframe").src = "";
		window.close();
	}
	function conmmit(){
		document.getElementById("message1").innerHTML = "";
		document.getElementById("message2").innerHTML = "";
		document.getElementById("message3").innerHTML = "";
		document.getElementById("message4").innerHTML = "";
		
		var passwd = document.all("passwd").value;
		var passwdNew = document.all("passwdNew").value;
		var passwdNew2 = document.all("passwdNew2").value;
		
		if(trim(passwd) == ""){
			alert("请输原始密码！");
			document.all("passwd").focus();
			document.all("passwd").select();
			return false;
		}else if(trim(passwd).length < 6){
			alert("原始密码不足6位,请重新输入！");
			document.all("passwd").focus();
			document.all("passwd").select();
			return false;
		}
		
		if(trim(passwdNew) == ""){
			alert("请输入新密码！");
			document.all("passwdNew").focus();
			document.all("passwdNew").select();
			return false;
		}else if(trim(passwdNew).length < 6){
			alert("新密码不足6位,请重新输入！");
			document.all("passwdNew").focus();
			document.all("passwdNew").select();
			return false;
		}
		
		if(trim(passwdNew2) == ""){
			alert("请输入确认密码！");
			document.all("passwdNew2").focus();
			document.all("passwdNew2").select();
			return false;
		}else if(trim(passwdNew2).length < 6){
			alert("确认密码不足6位,请重新输入！");
			document.all("passwdNew2").focus();
			document.all("passwdNew2").select();
			return false;
		}
		
		if(trim(passwdNew) != trim(passwdNew2)){
			alert("两次输入的新密码不一致！请重新输入！");
			document.all("passwdNew").value="";
			document.all("passwdNew2").value="";
			document.all("passwdNew").focus();
			return false;
		}
		if(trim(passwd) == trim(passwdNew)){
			alert("新旧密码不能相同！请重新输入！");
			document.all("passwdNew").value="";
			document.all("passwdNew2").value="";
			document.all("passwdNew").focus();
			return false;
		}
		document.all("passwd").value = MD5(passwd);<%//对管理员旧密码进行MD5加密%>
		checkPasswd();
	}
	
	<%//验证原始密码是否正确%>
	function checkPasswd(){
		var usercode = document.all("usercode").value;
		var passwd = document.all("passwd").value;
		var xmlhttp;
		var message1 = document.getElementById("message1");
		if(window.ActiveXObject){
			xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
		}else if(window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest();
		}
		xmlhttp.open("post","<%=path%>/user.do?method=checkPasswd&usercode=" + usercode + "&passwd=" + passwd,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
					var i = xmlhttp.responseText;
					if(parseInt(trim(i)) == -2){
						message3.innerHTML = "操作超时，请重新登录！";
						return false;
					} else if(parseInt(trim(i)) == -1){
						message1.innerHTML = "原始密码错误！";
						document.all("passwd").value="";
						document.all("passwdNew").value="";
						document.all("passwdNew2").value="";
						document.all("passwd").focus();
						document.all("passwd").select();
						return false;
					} else{
						checkWeakPasswd();
					}
				}
			}
		}
		xmlhttp.setRequestHeader("If-Modified-Since","0");
		xmlhttp.send(null);
	}
	
	<%//验证新密码是否为弱密码%>
	function checkWeakPasswd(){
		var passwdNew = document.all("passwdNew").value;
		var passwdNew2 = document.all("passwdNew2").value;
		var xmlhttp;
		var message2 = document.getElementById("message2");
		if(window.ActiveXObject){
			xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
		}else if(window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest();
		}
		xmlhttp.open("post","<%=path%>/user.do?method=checkWeakPasswd&passwdNew=" + passwdNew,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
					var i = xmlhttp.responseText;
					if(parseInt(trim(i)) == -1){
						message2.innerHTML = "新密码为弱密码！";
						document.all("passwd").value="";
						document.all("passwdNew").value="";
						document.all("passwdNew2").value="";
						document.all("passwd").focus();
						document.all("passwd").select();
						return false;
					}
					else{
						document.all("passwdNew").value = MD5(passwdNew);<%//对管理员新密码进行MD5加密%>
						document.all("passwdNew2").value = MD5(passwdNew2);<%//对管理员的确认密码进行MD5加密%>
						<%//修改管理员密码%>
						modifyPassword();
					}
				}
			}
		}
		xmlhttp.setRequestHeader("If-Modified-Since","0");
		xmlhttp.send(null);
	}
	
	<%//修改管理员密码%>
	function modifyPassword(){
		var usercode = document.all("usercode").value;
		var passwd = document.all("passwd").value;
		var passwdNew = document.all("passwdNew").value;
		var message3 = document.getElementById("message3");
		var message4 = document.getElementById("message4");
		var xmlhttp;
		if(window.ActiveXObject){
			xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
		}else if(window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest();
		}
		
		xmlhttp.open("post","<%=path%>/user.do?method=modPassword&usercode="+usercode+"&passwd="+passwd+"&passwdNew="+passwdNew,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
					var i = xmlhttp.responseText;
					if(i == -1){
						message3.innerHTML = "修改密码失败！请稍候再试！";
						message4.innerHTML = "";
						document.all("passwd").value="";
						document.all("passwdNew").value="";
						document.all("passwdNew2").value="";
						document.all("passwd").focus();
						return false;
					}
					else{
						message3.innerHTML = "";
						message4.innerHTML = "密码修改成功！请关闭当前窗口继续操作！";
						document.getElementById("button1").disabled = "true";
						return true;
					}
				}
			}
		}
		xmlhttp.setRequestHeader("If-Modified-Since","0");
		xmlhttp.send(null);
	}
	
	function onJudge(){
		if(window.event.keyCode==13){
			conmmit();
		}
	}
	
	function close11(){
		window.close();
	}
</script>
<center>
	<body>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<%-- <tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					    <tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
							
							</td>
							<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：系统管理管理 &gt; 管理员管理 &gt; 修改密码 </td>
							<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
						</tr>
						 <tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
			</td>
		</tr> --%>
		<tr>
			<td align="center">
				<table border="0" cellpadding="0" cellspacing="0" width="60%">
					<tr>
						<td height="20" colspan="2">
						</td>
					</tr>
					<tr>
						<td height="30" align="center" colspan="2" class="box1">
							<font color="blue" class="big">修 改 密 码</font>
						</td>
					</tr>
					<tr>
						<td height="30" width="80" align="right" class="box1">
							<font color="#0033FF">原始密码：</font>
						</td>
						<td height="30" align="left" class="box4">
						    <input type="password" name="passwd" maxlength="40" />
						    <font id="message1" color="red">&nbsp;</font>
						</td>
					</tr>
					<tr>
						<td height="30" width="80" align="right" class="box1">
							<font color="#0033FF">新密码：</font>
						</td>
						<td height="30" align="left" class="box2">
							<input type="password" name="passwdNew" maxlength="40" />
							<font id="message2" color="red">&nbsp;</font>
						</td>
					</tr>
					<tr>
						<td height="30" width="80" align="right" class="box1">
							<font color="#0033FF">确认密码：</font>
						</td>
						<td height="30" align="left" class="box2">
							<input type="password" name="passwdNew2" maxlength="40" onkeypress="onJudge();"/>
						</td>
					</tr>
					<tr>
						<td height="30" align="center" class="box1" colspan="2">
							<input id="button1" type="button"  class="button" value="保存" style="cursor: hand" onclick="return conmmit();"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button"  class="button" value="关闭" style="cursor: hand" onclick="close11();"/>
						</td>
					</tr>
					<tr>
						<td height="30" align="center" colspan="2">
							<font id="message3" color="red">&nbsp;</font>
							<font id="message4" color="green">&nbsp;</font>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
		<input type="hidden" name="usercode" value="<%=usercode%>"/>
	</body>
<script>
	document.all("passwd").focus();
</script>
</center>
</html:html>
