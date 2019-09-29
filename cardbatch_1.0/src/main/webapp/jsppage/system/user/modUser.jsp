<%@ page language="java" pageEncoding="gbk" import="com.pay.util.ParamUtils"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
    String path = request.getContextPath();
    String comparePass = ParamUtils.getStringAttribute(request,"comparePass");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>����Ա�޸�ҳ��</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<script language="javascript">
	window.history.forward(1);
	function commit(){
		var username = document.userForm.username.value;
		var validdays = document.userForm.validdays.value;
		var roleno = document.userForm.roleno.value;
		var deptno = document.userForm.deptno.value;
		var fax = document.userForm.fax.value;
		var phone = document.userForm.phone.value;
		if(trim(username) == ""){
			alert("���������Ա���ƣ�");
			document.userForm.username.focus();
			return false;
		}
		if(trim(deptno) == ""){
			alert("��ѡ�����Ա�����û��飡");
			document.userForm.deptno.focus();
			return false;
		}
		if(trim(roleno) == ""){
			alert("��ѡ�����Ա������ɫ��");
			document.userForm.roleno.focus();
			return false;
		}
		if(fax != '' && !isnumberonly("userForm","fax","�����ʽ")){
			return false;
		}
		if(phone != '' && !isnumberonly("userForm","phone","�绰��ʽ")){
			return false;
		}
		<%//�ж�validdays����Ϊ����%>
		if(trim(validdays) == ""){
			alert("������Ч�ڲ���Ϊ�գ����������룡");
			document.userForm.validdays.focus();
			return false;
		}
		if(validdays == "��������"){
			document.userForm.validdays.value = "-1";
		}
		else if(!isnumberonly("userForm","validdays","������Ч��")){
			return false;
		}
		var isMac = $("#isMac").val();
		if(isMac=="0"){
			var macInput = $("#macInput input").val();
			if(null==macInput || macInput==""){
				alert("��δ��MAC��ַ");
				return false;
			}
		}
		document.userForm.submit();
	}
	
		function showMacInput(){
		var macInput = $("#macInput");
		var mac = $("#macInput input");
		var isMac = $("#isMac").val();
		if(isMac=="0")
			macInput.show();
		else{
			mac.val("");
			macInput.hide();
		}
	}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="userForm" action="/user?method=modUser" method="post" focus="username">
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				height="99%">
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;��ǰλ�ã� ϵͳ���� &gt;����Ա���� &gt;�޸Ĺ���Ա</td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="70%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">����Ա��ţ�</font>
								</td>
								<td align="left" class="box3">
									<html:hidden property="usercode" />
									<bean:write name="userForm" property="usercode" />
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">����Ա���ƣ�</font>
								</td>
								<td align="left" class="box2">
									<html:text property="username" maxlength="20"></html:text>
								</td>
							</tr>
							<html:hidden property="passwd" />
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">�����û��飺</font>
								</td>
								<td align="left" class="box2">
									<logic:present name="deptList">
										<logic:equal value="00" name="roleno" >
											<html:select property="deptno" onchange="checkAdminDept()">
												<html:option value="">����ѡ��</html:option>
												<html:optionsCollection name="deptList" label="deptname" value="deptno"/>
											</html:select>
										</logic:equal>
										<logic:notEqual value="00" name="roleno">
											<html:select property="deptno">
												<html:option value="">����ѡ��</html:option>
												<html:optionsCollection name="deptList" label="deptname" value="deptno"/>
											</html:select>
										</logic:notEqual>
										
									</logic:present>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">������ɫ��</font>
								</td>
								<td align="left" class="box2">
									<logic:present name="roleList">
										<logic:equal value="00" name="roleno" >
											<html:select property="roleno">
												<html:option value="">����ѡ��</html:option>
												<html:optionsCollection name="roleList" label="rolename" value="roleno" />
											</html:select>
										</logic:equal>
										<logic:notEqual value="00" name="roleno">
											<html:select property="roleno" >
												<html:option value="">����ѡ��</html:option>
												<html:optionsCollection name="roleList" label="rolename" value="roleno" />
											</html:select>
										</logic:notEqual>
										
									</logic:present>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									ͨѶ��ַ��
								</td>
								<td align="left" class="box2">
									<html:textarea rows="2" cols="60" property="address"
										></html:textarea>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									�������룺
								</td>
								<td align="left" class="box2">
									<html:text property="postalcode" maxlength="6"></html:text>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									EMAIL��ַ��
								</td>
								<td align="left" class="box2">
									<html:text property="mail" maxlength="20"></html:text>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									���棺
								</td>
								<td align="left" class="box2">
									<html:text property="fax" maxlength="16"></html:text>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									��ϵ�绰��
								</td>
								<td align="left" class="box2">
									<html:text property="phone" maxlength="16"></html:text>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">����ʧЧ���ڣ�</font>
								</td>
								<td align="left" class="box2">
									<logic:equal name="userForm" property="passwddays" value="-1">
										������������
									</logic:equal>
									<logic:notEqual name="userForm" property="passwddays"
										value="-1">
										<bean:write name="userForm" property="passwddays" />&nbsp;
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									������Ч�ڣ�
								</td>
								<td align="left" class="box2">
									<input type="text" name="validdays" size="10"
										onfocus="javascript:showSelect(this,'select1')"
										value='<logic:equal value="-1"  property="validdays" name="userForm">��������</logic:equal><logic:notEqual value="-1"  property="validdays" name="userForm"><bean:write property="validdays" name="userForm"/></logic:notEqual>'>
									<select name="select1" id="select1" style="display: none"
										disabled>
										<option value="7">
											7
										</option>
										<option value="30">
											30
										</option>
										<option value="60">
											60
										</option>
										<option value="120">
											120
										</option>
										<option value="-1">
											��������
										</option>
									</select>
									<font color="red">[ע��:������Ч������Ϊ��λ!]</font>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									״̬��
								</td>
								<td align="left" class="box2">
									<html:select property="isactive" >
										<html:option value="0">��Ч</html:option>
										<html:option value="1">��Ч</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">�Ƿ��Mac��ַ��</td>
								<td  align="left" class="box2">
									<select id="isMac" onchange="showMacInput()">
										<option value="0" selected="selected">��</option>
										<option value="1">����</option>
									</select>
								</td>
							</tr>
							<tr id="macInput">
								<td width="110" align="right" class="box1">������Mac��ַ��</td>
								<td  align="left" class="box2" id="mac">
									<html:text property="mac"  maxlength="20"></html:text>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="70%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td height="23" align="center" class="box1">
									<input class="button" type="button" onClick="commit();"
										value="����">
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button" value="�ر�"
										onClick="history.go(-1)">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<html:hidden property="teamno" />
			<html:hidden property="teamname" />
			<html:hidden property="loginflag" />
			<html:hidden property="lastintime" />
		</html:form>
	</body>
</center>
</html:html>
