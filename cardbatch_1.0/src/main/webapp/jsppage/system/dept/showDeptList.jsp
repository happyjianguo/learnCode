
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>显示用户组列表页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script language="javascript">
	function addConfirm(){
		window.location.href="<%=path %>/jsppage/system/dept/addDept.jsp";
	}
	function delConfirm(){
		var answer = window.confirm("您确定要删除吗？删除之后将不能恢复!");
		if(answer == true)
		{
			return true;
		}
		return false;
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%">
		<tr>
			<td align="center" valign="top"  height="87%">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：系统管理 &gt; 用户组管理 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
				<table border="0" cellpadding="0" cellspacing="0"  width="100%" class="serch" style="padding:7px;" >
				    
					
					<logic:match name="menu_level" value="1">
					<tr>
						<td height="25" align="left">
										<input type="button" class="button" onClick='return addConfirm()'     style="background-image: url(<%=path%>/image1/border/New_button.gif)">
										
									</td>
					</tr>
					</logic:match>
				</table>
			
				<div class="dlist"  style="top: 96px ;height:75%;" >
				<table  cellpadding="0" cellspacing="0" width="100%" border="0" >
					<tr  id="tl" height="35" align="center" style="top: expression(this.offsetParent.scrollTop);position: relative;border-style: none" >
						<td height="20">
							用户组编号
						</td>
						<td>
							用户组名称
						</td>
						
						<td >
							上级用户组号
						</td>
						
						
						<td>
							用户组级别
						</td>
						
						<logic:match name="menu_level" value="2">
						<td>
							修改
						</td>
						</logic:match>
						<logic:match name="menu_level" value="3">
						<td>
							删除
						</td>
						</logic:match>
					</tr>
						<tbody  class="list" >
					<logic:present name="deptList">
							<logic:iterate id="deptBean" name="deptList">
								<tr align="center" onMouseOver="changeClass(this,1)" onMouseOut="changeClass(this,2)">
									<td class=box2 height="28">
										<bean:write name="deptBean" property="deptno"/>
									</td>
									<td class=box2 height="28">
										<bean:write name="deptBean" property="deptname"/>
									</td>
								 
									<td class=box2 height="28">
										<bean:write name="deptBean" property="dept_upperno"/>&nbsp;
									</td>
									 
									
									<td class=box2 height="28">
										<bean:write name="deptBean" property="dept_level"/>&nbsp;
									</td>
									
									<logic:notEqual value="000000000" name="deptBean" property="deptno">
									<logic:equal value="-1" name="deptBean" property="dept_upperno">
										<logic:match name="menu_level" value="2">
											<td class=box2>--</td>
										</logic:match>
										<logic:match name="menu_level" value="3">
											<td class=box2>--</td>
										</logic:match>
									</logic:equal>
									<logic:equal value="410000000" name="deptBean" property="deptno">
										<logic:match name="menu_level" value="2">
											<td class=box2>--</td>
										</logic:match>
										<logic:match name="menu_level" value="3">
											<td class=box2>--</td>
										</logic:match>
									</logic:equal>
									
									<logic:notEqual value="-1" name="deptBean" property="dept_upperno">
										<logic:notEqual value="410000000" name="deptBean" property="deptno">
											<logic:match name="menu_level" value="2">
												<td class=box2 height="28">
													<html:link action="/dept?method=preModDept" paramId="deptno" paramName="deptBean" paramProperty="deptno"><img border="0" src="<%=path %>/image1/border/query.png" /></html:link>
												</td>
											</logic:match>
											<logic:match name="menu_level" value="3">
												<td class=box2 height="28" onClick="return delConfirm();">
													<html:link action="/dept?method=deleteDept" paramId="deptno" paramName="deptBean" paramProperty="deptno"><img border="0" src="<%=path%>/image1/border/del.png" /></html:link>
												</td>
											</logic:match>
										</logic:notEqual>
									</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="000000000" name="deptBean" property="deptno">
										<logic:match name="menu_level" value="2">
											<td class=box2>--</td>
										</logic:match>
										<logic:match name="menu_level" value="3">
											<td class=box2>--</td>
										</logic:match>
									</logic:equal>
								</tr>
								
							</logic:iterate>
						
					</logic:present>
					<logic:notPresent name="deptList">
						<%int i=5; %>
						<logic:match name="menu_level" value="2">
						<%i++; %>
						</logic:match>
						<logic:match name="menu_level" value="3">
						<%i++; %>
						</logic:match>
						<tr align="center"><td  class=box3 colspan="<%=i %>">暂无用户组信息</td></tr>
					</logic:notPresent>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" valign="top" height="30">				
				<table border="0" cellpadding="0" cellspacing="0" width="98%">
 						<tr align="right">
						<td height="25">
							<util:page uri="/dept.do?method=getDeptList" />
						</td>
 					    </tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
