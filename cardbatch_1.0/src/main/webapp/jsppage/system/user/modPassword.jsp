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

	<title>����Ա�޸�����ҳ��</title>

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
			alert("����ԭʼ���룡");
			document.all("passwd").focus();
			document.all("passwd").select();
			return false;
		}else if(trim(passwd).length < 6){
			alert("ԭʼ���벻��6λ,���������룡");
			document.all("passwd").focus();
			document.all("passwd").select();
			return false;
		}
		
		if(trim(passwdNew) == ""){
			alert("�����������룡");
			document.all("passwdNew").focus();
			document.all("passwdNew").select();
			return false;
		}else if(trim(passwdNew).length < 6){
			alert("�����벻��6λ,���������룡");
			document.all("passwdNew").focus();
			document.all("passwdNew").select();
			return false;
		}
		
		if(trim(passwdNew2) == ""){
			alert("������ȷ�����룡");
			document.all("passwdNew2").focus();
			document.all("passwdNew2").select();
			return false;
		}else if(trim(passwdNew2).length < 6){
			alert("ȷ�����벻��6λ,���������룡");
			document.all("passwdNew2").focus();
			document.all("passwdNew2").select();
			return false;
		}
		
		if(trim(passwdNew) != trim(passwdNew2)){
			alert("��������������벻һ�£����������룡");
			document.all("passwdNew").value="";
			document.all("passwdNew2").value="";
			document.all("passwdNew").focus();
			return false;
		}
		if(trim(passwd) == trim(passwdNew)){
			alert("�¾����벻����ͬ�����������룡");
			document.all("passwdNew").value="";
			document.all("passwdNew2").value="";
			document.all("passwdNew").focus();
			return false;
		}
		document.all("passwd").value = MD5(passwd);<%//�Թ���Ա���������MD5����%>
		checkPasswd();
	}
	
	<%//��֤ԭʼ�����Ƿ���ȷ%>
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
						message3.innerHTML = "������ʱ�������µ�¼��";
						return false;
					} else if(parseInt(trim(i)) == -1){
						message1.innerHTML = "ԭʼ�������";
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
	
	<%//��֤�������Ƿ�Ϊ������%>
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
						message2.innerHTML = "������Ϊ�����룡";
						document.all("passwd").value="";
						document.all("passwdNew").value="";
						document.all("passwdNew2").value="";
						document.all("passwd").focus();
						document.all("passwd").select();
						return false;
					}
					else{
						document.all("passwdNew").value = MD5(passwdNew);<%//�Թ���Ա���������MD5����%>
						document.all("passwdNew2").value = MD5(passwdNew2);<%//�Թ���Ա��ȷ���������MD5����%>
						<%//�޸Ĺ���Ա����%>
						modifyPassword();
					}
				}
			}
		}
		xmlhttp.setRequestHeader("If-Modified-Since","0");
		xmlhttp.send(null);
	}
	
	<%//�޸Ĺ���Ա����%>
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
						message3.innerHTML = "�޸�����ʧ�ܣ����Ժ����ԣ�";
						message4.innerHTML = "";
						document.all("passwd").value="";
						document.all("passwdNew").value="";
						document.all("passwdNew2").value="";
						document.all("passwd").focus();
						return false;
					}
					else{
						message3.innerHTML = "";
						message4.innerHTML = "�����޸ĳɹ�����رյ�ǰ���ڼ���������";
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
							<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;��ǰλ�ã�ϵͳ������� &gt; ����Ա���� &gt; �޸����� </td>
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
							<font color="blue" class="big">�� �� �� ��</font>
						</td>
					</tr>
					<tr>
						<td height="30" width="80" align="right" class="box1">
							<font color="#0033FF">ԭʼ���룺</font>
						</td>
						<td height="30" align="left" class="box4">
						    <input type="password" name="passwd" maxlength="40" />
						    <font id="message1" color="red">&nbsp;</font>
						</td>
					</tr>
					<tr>
						<td height="30" width="80" align="right" class="box1">
							<font color="#0033FF">�����룺</font>
						</td>
						<td height="30" align="left" class="box2">
							<input type="password" name="passwdNew" maxlength="40" />
							<font id="message2" color="red">&nbsp;</font>
						</td>
					</tr>
					<tr>
						<td height="30" width="80" align="right" class="box1">
							<font color="#0033FF">ȷ�����룺</font>
						</td>
						<td height="30" align="left" class="box2">
							<input type="password" name="passwdNew2" maxlength="40" onkeypress="onJudge();"/>
						</td>
					</tr>
					<tr>
						<td height="30" align="center" class="box1" colspan="2">
							<input id="button1" type="button"  class="button" value="����" style="cursor: hand" onclick="return conmmit();"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button"  class="button" value="�ر�" style="cursor: hand" onclick="close11();"/>
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
