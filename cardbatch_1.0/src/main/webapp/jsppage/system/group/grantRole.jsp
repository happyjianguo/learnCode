<%@ page language="java" pageEncoding="gbk" import="com.pay.util.ParamUtils"%>
<%@ page import="com.pay.system.role.bean.RoleBean" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>
<%
	String sign = "1";
	String groupRolenos = ParamUtils.getStringAttribute(request,"groupRolenos");
	if(groupRolenos == null || groupRolenos.intern() == "".intern()){
		sign = "0";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>角色分配页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
</head>
<script language="javascript">
	function initRoleno(){
		var sign = <%=sign%>;
		var roleNo = document.getElementsByName("rolenos");
		if(sign == "1"){
			var rolenoArray = new Array();
			rolenoArray = "<%=groupRolenos%>".split("|");
			for(i = 0;i < rolenoArray.length; i++){
				for(j = 0; j < roleNo.length;j++){
					if(rolenoArray[i] == roleNo[j].value){
						roleNo[j].checked = true;
					}
				}
			}
		}
	}

	function selectAll(){
		var roleNo = document.getElementsByName("rolenos");
		for(i = 0; i < roleNo.length;i++){
			roleNo[i].checked = true;
		}
	}
	
	function selectOther(){
		var roleNo = document.getElementsByName("rolenos");
		for(i = 0; i < roleNo.length;i++){
			if(roleNo[i].checked == true){
				roleNo[i].checked = false;
			}
			else{
				roleNo[i].checked = true;
			}
		}
	}
	
	function conmmit(){
		document.groupFrom.submit();
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form styleId="groupFrom" action="/userGroup?method=grantRole" method="post">
	<table border="0" cellpadding="0" cellspacing="0" width="862" height="474" background="<%=path%>/images/background/welcome2.gif">
		<tr>
			<td align="center" valign="top">
				<table border="0" cellpadding="0" cellspacing="0" width="90%">
					<tr>
						<td height="20"></td>
					</tr>
					<tr>
						<td height="23" align="center">
							<font color="blue" class="big">角 色 分 配</font>
						</td>
					</tr>
					<tr>
						<td height="23" align="center">
							<input style="cursor:hand" class="button" type="button" onclick="selectAll();" value="全选">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" onclick="selectOther();" value="反选">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" onclick="conmmit();" value="保存">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" value="关闭" onClick="history.go(-1)">
						</td>
					</tr>
				</table>
				<div style="position:absolute; overflow:auto; overflow-x:hidden; width:90%; height:390px; border:1px none #000000; left: 50px;">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr align="center" style="position: relative; top: expression(this.offsetParent.scrollTop);">
							<td height="25" class=box1>
								角色编号
							</td>
							<td height="25" class=box1>
								角色名称
							</td>
							<td height="25" class=box1>
								角色描述
							</td>
							<td height="25" class=box1>
								是否分配
							</td>
						</tr>
						<logic:present name="allRoleList">
							<logic:iterate id="roleBean" name="allRoleList">
								<tr align="center">
									<td class=box2 height="28">
										<bean:write name="roleBean" property="roleno" />
									</td>
									<td class=box2 height="28">
										<bean:write name="roleBean" property="rolename" />
									</td>
									<td class=box2 height="28">
										<bean:write name="roleBean" property="roledesc" />&nbsp;
									</td>
									<td class=box2 height="28">
										<input type="checkbox" name="rolenos" value="<%=((RoleBean)pageContext.getAttribute("roleBean")).getRoleno()%>">
									</td>
								</tr>
								<input type="hidden" name="teamno" value="<%=ParamUtils.getStringAttribute(request,"teamno")%>"/>
							</logic:iterate>
						</logic:present>
						<logic:notPresent name="allRoleList">
							<tr align="center">
								<td class=box3 colspan="4">
									暂无角色信息
								</td>
							</tr>
						</logic:notPresent>
					</table>
					<script language="javascript">
						initRoleno();//初始化已有角色信息
					</script>
				</div>
			</td>
		</tr>
	</table>
	</html:form>
</body>
</html:html>
