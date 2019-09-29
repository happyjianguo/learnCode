<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>模板管理</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	function saveClick()
	{
		document.forms[0].method.value="saveItem";
		return validateSysModuleForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	//删除后面空格
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,   "");   
  	} 
	</script>
</head>
<shiro:lacksPermission name="posp:sysModule:edit">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:sysModule:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				修改模板信息
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="sysModuleForm" />
				<html:errors />
				<html:form action="/sysModule">

					<html:hidden property="method" value="saveItem" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						
	
						<tr>
							<td class="table2_td_title">
								程序模板:
							</td>
							<td class="table2_td">
								<html:text property="moduleId" size="30" disabled="true"/><font color="red">*</font>
							    <html:hidden property="moduleId" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								模块描述:

							</td>
							<td class="table2_td">
								<html:text property="progDesc" size="30" maxlength="40" onfocus="this.value = this.value.RTrim()"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								程序名称:

							</td>
							<td class="table2_td">
								<html:text property="progName"  size="30" maxlength="30" onfocus="this.value = this.value.RTrim()"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title"> 
								程序参数: 
 
							</td>
							<td class="table2_td">
								<html:text property="progParam"  size="30"  maxlength="50"  onfocus="this.value = this.value.RTrim()"/>
							</td>
							</tr>
							<tr>
							<td class="table2_td_title"> 
								启动方式: 
 
							</td>
							<td class="table2_td">
								<html:select property="startMode"  style="width:195px;">
								<html:option value="0">不运行</html:option>
								<html:option value="1">激活时运行</html:option>
								<html:option value="2">运行（暂停时退出）</html:option>
								<html:option value="3">运行（不驻留）</html:option>
								<html:option value="4">运行（驻留）</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()" />
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()" />


							</td>
						</tr>
					</table>

					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>



</body>
</shiro:hasPermission>
</html:html>



