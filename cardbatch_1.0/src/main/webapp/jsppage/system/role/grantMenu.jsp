<%@ page language="java" pageEncoding="gbk" import="com.pay.util.ParamUtils"%>
<%@ page import="com.pay.system.menu.bean.MenuBean" %>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>
<%
	String sign = "1";
	String strMenuno = ParamUtils.getStringAttribute(request,"menunos");
	HashMap levelMap = (HashMap)request.getAttribute("levelMap");
	if(strMenuno == null || strMenuno.intern() == "".intern()){
		sign = "0";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>角色权限分配页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
</head>
<script language="javascript">
	function initMenuno(){
		var sign = "<%=sign%>";
		var menuNo = document.getElementsByName("menunos");
		if(sign == "1"){
			var menuArray = new Array();
			menuArray = "<%=strMenuno%>".split("|");
			for(i = 0;i < menuArray.length; i++){
				for(j = 0; j < menuNo.length;j++){
					if(menuArray[i] == menuNo[j].value){
						menuNo[j].checked = true;
					}
				}
			}
		}
	}
	
	function selectAll(){
		var inputs = document.getElementsByTagName("input");
		for(i = 0; i < inputs.length;i++){
			if (inputs[i].type == 'checkbox') {
				inputs[i].checked = true;
			}
		}
	}
	
	function selectOther(){
		var inputs = document.getElementsByTagName("input");
		var name = '';
		for(i = 0; i < inputs.length;i++){
			if (inputs[i].type == 'checkbox') {
				name = inputs[i].name;
				if (inputs[i].checked == true) {
					inputs[i].checked = false;
				} else {
					if (name != 'menunos') checkLevel(name);
					inputs[i].checked = true;
				}
			}
		}
	}
	
	function commit(){
		document.roleForm.submit();
	}
	
	function checkRule(menuid,div){
		var checkbox = div.getElementsByTagName("input");
		if(checkbox.length > 0){
			if(menuid.checked){
				for(var i = 0; i < checkbox.length; i++){
					checkbox[i].disabled=false;
				}
			}else{
				for(var i = 0; i < checkbox.length; i++){
					checkbox[i].value="";
					checkbox[i].disabled=true;
				}
			}
		}
	}
	
	function checkMenu(menuid){
		var menuId =  document.getElementById(menuid);
		var menuNo = document.getElementsByName(menuid);
		if(menuId.checked == false){
			for(var i = 0; i < menuNo.length; i++){
				menuNo[i].checked = false;
			}
		}else{
			
		}
	}
	
	function checkLevel(menuid){
		var menuId =  document.getElementById(menuid);
		if(menuId.checked == false){
			menuId.checked = true;
		}else{
			
		}
	}
	
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form styleId="roleForm" action="/role?method=grantMenu" method="post">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
		<tr>
			<td align="center" valign="top" height="50">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：角色管理 &gt; 角色权限分配 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
				<table cellpadding="0" cellspacing="0" width="100%" class="serch"  style="padding:13px;"  align="left">
					<tr>
						<td height="25" align="center">
							<input style="cursor:hand" class="button" type="button" onClick="selectAll();" value="全选">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" onClick="selectOther();" value="反选">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" onClick="commit();" value="保存">
							&nbsp;&nbsp;&nbsp;
							<input style="cursor:hand" class="button" type="button" value="关闭" onClick="history.go(-1)">
						</td>
					</tr>
				</table>
				</td>
		</tr>
		<tr>
			<td align="center" valign="top" cellpadding="0" cellspacing="0">
			    <div class="dlist"  style="top: 107px ;height: 96%">				
					<table cellpadding="0" cellspacing="0" width="100%">
						
						<tr id="tl" height="35" align="center" style="top: expression(this.offsetParent.scrollTop);position: relative;border-style: none">
							<td height="20">
								权限编号
							</td>
							<td >
								权限名称
							</td>
							<td >
								权限描述
							</td>
							<td >
								是否分配
							</td>
							<td >
								新增权限
							</td>
							<td >
								修改权限
							</td>
							<td >
								删除权限
							</td>
							<td >
								启停权限
							</td>
							<td >
								撤销权限
							</td>
						</tr>
					<tbody  class="list" >
						<logic:present name="allMenuList">
							<logic:iterate id="menuBean" name="allMenuList">
								<tr align="center">
									<td class=box2 height="28">
										<bean:write name="menuBean" property="menuno" />
									</td>
									<td class=box2 height="28">
										<bean:write name="menuBean" property="menuname" />
									</td>
									<td class=box2 height="28">&nbsp;
										<bean:write name="menuBean" property="menudesc" />
									</td>
									<%
										String menuId = ((MenuBean)pageContext.getAttribute("menuBean")).getMenuno();
										String menuStr = ((MenuBean)pageContext.getAttribute("menuBean")).getMenu_level();
										String checkFlag = "";
										if(levelMap!=null && levelMap.get(menuId)!=null){
											checkFlag = levelMap.get(menuId).toString();
										}
									%>
									<td class=box2 height="28">
										<input id="<%=menuId%>"  type="checkbox" name="menunos" value="<%=((MenuBean)pageContext.getAttribute("menuBean")).getMenuno()%>" onClick="checkMenu(<%=menuId%>)">
									</td>
									<td class=box2 height="28">
										<%if(menuStr.indexOf("1")>-1){ %>
										<input  type="checkbox" name="<%=menuId %>" value="1" <%if(checkFlag.indexOf("1")>-1){%> checked="checked" <%} %> onClick="checkLevel(<%=menuId%>)">
										<%}else{ %>--<%} %>
									</td>
									<td class=box2 height="28">
										<%if(menuStr.indexOf("2")>-1){ %>
										<input  type="checkbox" name="<%=menuId %>" value="2" <%if(checkFlag.indexOf("2")>-1){%> checked="checked" <%} %> onClick="checkLevel(<%=menuId%>)">
										<%}else{ %>--<%} %>
									</td>
									<td class=box2 height="28">
										<%if(menuStr.indexOf("3")>-1){ %>
										<input  type="checkbox" name="<%=menuId %>" value="3" <%if(checkFlag.indexOf("3")>-1){%> checked="checked" <%} %> onClick="checkLevel(<%=menuId%>)">
										<%}else{ %>--<%} %>
									</td>
									<td class=box2 height="28">
										<%if(menuStr.indexOf("4")>-1){ %>
										<input  type="checkbox" name="<%=menuId %>" value="4" <%if(checkFlag.indexOf("4")>-1){%> checked="checked" <%} %> onClick="checkLevel(<%=menuId%>)">
										<%}else{ %>--<%} %>
									</td>
									<td class=box2 height="28">
										<%if(menuStr.indexOf("5")>-1){ %>
										<input  type="checkbox" name="<%=menuId %>" value="5" <%if(checkFlag.indexOf("5")>-1){%> checked="checked" <%} %> onClick="checkLevel(<%=menuId%>)">
										<%}else{ %>--<%} %>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<logic:notPresent name="allMenuList">
							<tr align="center">
								<td class=box3 colspan="4">
									<font color="red">暂无权限信息可供分配</font>
								</td>
							</tr>
						</logic:notPresent>
						</tbody>
					</table>
					<script language="javascript">
						initMenuno();<%//初始化已有权限信息%>
					</script>
				</div>
			</td>
		</tr>


	</table>
	<input type="hidden" name="roleno" value="<%=ParamUtils.getStringAttribute(request,"roleno")%>">
	</html:form>
</body>
</html:html>
