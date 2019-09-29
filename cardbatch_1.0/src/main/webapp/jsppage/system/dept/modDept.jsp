
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>用户组修改页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
</head>
<script language="javascript">
	window.history.forward(1);
	var flag = false;
	function commit(){
		var deptno = document.deptForm.deptno.value;
		var deptname = document.deptForm.deptname.value;
		var dept_level = document.deptForm.dept_level.value;
		var dept_upperno = document.deptForm.dept_upperno.value;
		
		if(trim(deptno) == ""){
			alert("请输入用户组编号！");
			document.deptForm.deptno.focus();
			return false;
		}
		if(trim(deptname) == ""){
			alert("请输入用户组名称！");
			document.deptForm.deptname.focus();
			return false;
		}
		if(trim(dept_level) == ""){
			alert("请输入用户组级别！");
			document.deptForm.dept_level.focus();
			return false;
		}
		if(!isnumberonly("deptForm","dept_level","用户组级别只能为数字")){
		document.deptForm.dept_level.focus();
			return false;
		}
		if(trim(dept_upperno) == ""){
			alert("请输入上级用户组名称！");
			document.deptForm.dept_upperno.focus();
			return false;
		}
		if(flag == false){
			alert("请先验证上级用户组号！");
			return false;
		}
		
		document.deptForm.submit();
	}
	function resetFlag() {
		flag = false;
	}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
		<html:form styleId="deptForm" action="/dept?method=modDept" method="post" focus="deptname">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：系统管理 &gt; 用户组管理&gt; 修改用户组 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<td width="20%" align="right" class="box1">
									<font color="red">用户组编号：</font>
								</td>
								<td width="50%" align="left" class="box3">
									<html:hidden property="deptno"/>
									<bean:write name="deptForm" property="deptno"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									<font color="red">用户组名称：</font>
								</td>
								<td width="50%" align="left" class="box2">
									<html:text property="deptname" maxlength="30"></html:text>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td width="20%" align="right" class="box1">
									<font color="red">用户组级别：</font>
								</td>
								<td width="50%" align="left" class="box2">
									<html:text property="dept_level" maxlength="1" onchange="resetFlag()"></html:text>
								</td>
								<td width="30%"><input type="button" value="验证上级用户组号"   style=" background:url(<%=path%>/image1/buntton1/8.gif); border:0px; width:116px; height:25px" onClick="getDeptByLevel()"/></td>
							</tr>
							<tr>
								<td width="20%" width="80" align="right" class="box1">
									<font color="red">上级用户组号：</font>
									
								</td>
								<td width="50%" align="left" class="box2">
									<html:text property="dept_upperno" maxlength="9" onchange="resetFlag()"></html:text>
								</td>
								<td width="30%">
									<span style="color: green;"  id="upperNameRight"></span>
									<span style="color: red;"  id="upperNameError"></span>
								</td>
							</tr>
							
							<tr>
								<td width="20%" align="right" class="box1">
									用户组描述：
								</td>
								<td width="50%" align="left" class="box2">
									<html:textarea rows="4" cols="60" property="deptdesc"></html:textarea>
								</td>
								<td width="30%"></td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<td height="23" align="center" class="box1">
									<input class="button" type="button" onClick="commit();" value="保存">
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button"  value="关闭" onClick="history.go(-1)">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</center>
</html:html>
