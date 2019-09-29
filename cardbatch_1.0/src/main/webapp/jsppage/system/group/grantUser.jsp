<%@ page language="java" pageEncoding="gbk"%>
<%@ page import="com.pay.system.user.bean.UserBean" %>
<%@ page import="com.pay.util.ParamUtils" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>
<%
	String sign = "1";
	String groupUsercodes = ParamUtils.getStringAttribute(request,"groupUsercodes");
	if(groupUsercodes == null || groupUsercodes.intern() == "".intern()){
		sign = "0";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>����Ա����ҳ��</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
</head>
<script language="javascript">
	function initUsercode(){
		var sign = "<%=sign%>";
		var userCode = document.getElementsByName("usercodes");
		if(sign == "1"){
			var userCodeArray = new Array();
			userCodeArray = "<%=groupUsercodes%>".split("|");
			for(i = 0;i < userCodeArray.length; i++){
				for(j = 0; j < userCode.length;j++){
					if(userCodeArray[i] == userCode[j].value){
						userCode[j].checked = true;
					}
				}
			}
		}
	}

	function selectAll(){
		var userCode = document.getElementsByName("usercodes");
		for(i = 0; i < userCode.length;i++){
			userCode[i].checked = true;
		}
	}
	
	function selectOther(){
		var userCode = document.getElementsByName("usercodes");
		for(i = 0; i < userCode.length;i++){
			if(userCode[i].checked == true){
				userCode[i].checked = false;
			}
			else{
				userCode[i].checked = true;
			}
		}
	}
	
	function conmmit(){
		document.groupFrom.submit();
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form styleId="groupFrom" action="/userGroup?method=grantUser" method="post">
	<table border="0" cellpadding="0" cellspacing="0" width="862" height="474" background="<%=path%>/images/background/welcome2.gif">
		<tr>
			<td align="center" valign="top">
				<table border="0" cellpadding="0" cellspacing="0" width="90%">
					<tr>
						<td height="20"></td>
					</tr>
					<tr>
						<td height="23" align="center">
							<font color="blue" class="big">�� �� �� ��</font>
						</td>
					</tr>
					<tr>
						<td height="23" align="center">
							<input style="cursor:hand" class="button" type="button" onclick="selectAll();" value="ȫѡ">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" onclick="selectOther();" value="��ѡ">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" onclick="conmmit();" value="����">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" value="�ر�" onClick="history.go(-1)">
						</td>
					</tr>
				</table>
				<div style="position:absolute; overflow:auto; overflow-x:hidden; width:90%; height:390px; border:1px none #000000; left: 50px;">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr align="center" style="position: relative; top: expression(this.offsetParent.scrollTop);">
							<td height="25" class=box1>
								����Ա���
							</td>
							<td height="25" class=box1>
								����Ա����
							</td>
							<td height="25" class=box1>
								��������Ա��
							</td>
							<td height="25" class=box1>
								�Ƿ����
							</td>
						</tr>
						<logic:present name="allUserList">
							<logic:iterate id="userBean" name="allUserList">
								<tr align="center">
									<td class=box2 height="28">
										<bean:write name="userBean" property="usercode" />
									</td>
									<td class=box2 height="28">
										<bean:write name="userBean" property="username" />
									</td>
									<td class=box2 height="28">
										<bean:write name="userBean" property="teamname" />
									</td>
									<td class=box2 height="28">
										<input type="checkbox" name="usercodes" value="<%=((UserBean)pageContext.getAttribute("userBean")).getUsercode()%>">
									</td>
								</tr>
								<input type="hidden" name="teamno" value="<%=ParamUtils.getStringAttribute(request,"teamno")%>"/>
							</logic:iterate>
						</logic:present>
						<logic:notPresent name="allUserList">
							<tr align="center">
								<td class=box3 colspan="4">
									���޹���Ա��Ϣ
								</td>
							</tr>
						</logic:notPresent>
					</table>
					<script language="javascript">
						initUsercode();<%//��ʼ�����й���Ա��Ϣ%>
					</script>
				</div>
			</td>
		</tr>
	</table>
	</html:form>
</body>
</html:html>
