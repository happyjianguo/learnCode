<%@ page language="java" pageEncoding="gbk" import="com.pay.util.ParamUtils"%>
<%@ page import="com.pay.system.menu.bean.MenuBean" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>
<%
	String sign = "1";
	String strItemno = ParamUtils.getStringAttribute(request,"itemnos");
	if(strItemno == null || strItemno.intern() == "".intern()){
		sign = "0";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>管理员权限分配页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
</head>
<script language="javascript">
	function initItemno(){
		var sign = "<%=sign%>";
		var itemNo = document.getElementsByName("itemnos");
		if(sign == "1"){
			var itemArray = new Array();
			itemArray = "<%=strItemno%>".split("|");
			for(i = 0;i < itemArray.length; i++){
				for(j = 0; j < itemNo.length;j++){
					if(itemArray[i] == itemNo[j].value){
						itemNo[j].checked = true;
					}
				}
			}
		}
	}

	function selectAll(){
		var itemNo = document.getElementsByName("itemnos");
		for(i = 0; i < itemNo.length;i++){
			itemNo[i].checked = true;
		}
	}
	
	function selectOther(){
		var itemNo = document.getElementsByName("itemnos");
		for(i = 0; i < itemNo.length;i++){
			if(itemNo[i].checked == true){
				itemNo[i].checked = false;
			}
			else{
				itemNo[i].checked = true;
			}
		}
	}
	
	function conmmit(){
		document.userForm.submit();
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form styleId="userForm" action="/user?method=grantItem" method="post">
	<table border="0" cellpadding="0" cellspacing="0" width="862" height="474" background="<%=path%>/images/background/welcome2.gif">
		<tr>
			<td align="center" valign="top">
				<table border="0" cellpadding="0" cellspacing="0" width="90%">
					<tr>
						<td height="20"></td>
					</tr>
					<tr>
						<td height="23" align="center">
							<font color="blue" class="big">用 户 权 限 分 配</font>
						</td>
					</tr>
					<tr>
						<td height="25" align="center">
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
				<div style="position:absolute; overflow:auto; overflow-x:hidden; width:90%; height:362px; border:1px none #000000; left: 50px;">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr align="center" style="position: relative; top: expression(this.offsetParent.scrollTop);">
							<td height="25" class=box1>
								权限编号
							</td>
							<td height="25" class=box1>
								权限名称
							</td>
							<td height="25" class=box1>
								权限描述
							</td>
							<td height="25" class=box1>
								是否分配
							</td>
						</tr>
						<logic:present name="allItemList">
							<logic:iterate id="itemBean" name="allItemList">
								<tr align="center">
									<td class=box2 height="28">
										<bean:write name="itemBean" property="itemno" />
									</td>
									<td class=box2 height="28">
										<bean:write name="itemBean" property="itemname" />
									</td>
									<td class=box2 height="28">
										<bean:write name="itemBean" property="itemdescrip" />
									</td>
									<td class=box2 height="28">
										<input type="checkbox" name="itemnos" value="<%=((MenuBean)pageContext.getAttribute("itemBean")).getMenuno()%>">
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<logic:notPresent name="allItemList">
							<tr align="center">
								<td class=box3 colspan="4">
									<font color="red">该管理员没有被分配到任何管理员组或该管理员所属管理员组还没有分配任何权限</font>
								</td>
							</tr>
						</logic:notPresent>
					</table>
					<script language="javascript">
						initItemno();//初始化已有权限信息
					</script>
				</div>
			</td>
		</tr>
	</table>
	<input type="hidden" name="usercode" value="<%=ParamUtils.getStringAttribute(request,"usercode")%>">
	</html:form>
</body>
</html:html>